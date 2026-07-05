package pe.edu.upc.wellnessbuddy.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wellnessbuddy.dtos.ChatMensajeDTO;
import pe.edu.upc.wellnessbuddy.dtos.InteraccionChatbotDTO;
import pe.edu.upc.wellnessbuddy.entities.*;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.*;
import pe.edu.upc.wellnessbuddy.util.ChatbotIAService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chatbot")
public class InteraccionChatbotController {

    @Autowired
    private IInteraccionChatbotService service;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IEmpleadoService empleadoService;

    @Autowired
    private IRegistroBienestarService registroService;

    @Autowired
    private IAlertaService alertaService;

    @Autowired
    private IRecomendacionService recomendacionService;

    @Autowired
    private IEvaluacionService evaluacionService;

    @Autowired
    private ChatbotIAService chatbotIAService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<InteraccionChatbotDTO> listar() {
        return service.list().stream()
                .map(x -> new ModelMapper().map(x, InteraccionChatbotDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public void registrar(@RequestBody InteraccionChatbotDTO dto) {
        service.insert(new ModelMapper().map(dto, InteraccionChatbot.class));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<InteraccionChatbotDTO> listarId(@PathVariable int id) {
        InteraccionChatbot i = service.listId(id);
        if (i == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new ModelMapper().map(i, InteraccionChatbotDTO.class));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> modificar(@RequestBody InteraccionChatbotDTO dto) {
        InteraccionChatbot i = new ModelMapper().map(dto, InteraccionChatbot.class);
        if (service.listId(i.getIdInteraccion()) == null)
            return ResponseEntity.notFound().build();
        service.update(i);
        return ResponseEntity.ok("Interaccion actualizada");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        if (service.listId(id) == null)
            return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.ok("Interaccion eliminada");
    }

    // ===== chat en vivo =====

    @GetMapping("/mis-conversaciones")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<InteraccionChatbotDTO> misConversaciones() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<InteraccionChatbot> historial = service.listarPorUsername(username);

        ModelMapper m = new ModelMapper();
        return historial.stream()
                .map(i -> m.map(i, InteraccionChatbotDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/enviar")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<InteraccionChatbotDTO> enviarMensaje(@RequestBody ChatMensajeDTO dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.buscarPorUsername(username);

        if (usuario == null) return ResponseEntity.notFound().build();

        List<InteraccionChatbot> historialPrevio = service.listarPorUsername(username);
        List<String[]> contexto = new ArrayList<>();
        int desde = Math.max(0, historialPrevio.size() - 5);
        for (int i = desde; i < historialPrevio.size(); i++) {
            InteraccionChatbot h = historialPrevio.get(i);
            contexto.add(new String[]{h.getMensajeUsuario(), h.getRespuestaBot()});
        }

        String contextoUsuario = construirContextoBienestar(username);

        String respuestaBot = chatbotIAService.obtenerRespuesta(dto.getMensaje(), contexto, contextoUsuario);

        InteraccionChatbot nueva = new InteraccionChatbot();
        nueva.setUsuario(usuario);
        nueva.setMensajeUsuario(dto.getMensaje());
        nueva.setRespuestaBot(respuestaBot);
        nueva.setFecha(LocalDate.now());

        service.insert(nueva);

        ModelMapper m = new ModelMapper();
        return ResponseEntity.ok(m.map(nueva, InteraccionChatbotDTO.class));
    }

    private String construirContextoBienestar(String username) {
        Empleado empleado = empleadoService.buscarPorUsername(username);
        if (empleado == null) {
            return "El usuario aun no ha completado su perfil laboral, por lo que no tiene registros de bienestar.";
        }

        StringBuilder sb = new StringBuilder();

        List<RegistroBienestar> registros = registroService.listarPorEmpleado(empleado.getIdEmpleado());
        if (!registros.isEmpty()) {
            RegistroBienestar ultimo = registros.get(registros.size() - 1);
            sb.append("- Ultimo registro de bienestar (").append(ultimo.getFecha()).append("): ")
                    .append("nivel de estres ").append(ultimo.getNivelEstres()).append("/10, ")
                    .append("estado de animo: ").append(ultimo.getEstadoAnimo()).append(".\n");
        } else {
            sb.append("- El usuario todavia no tiene registros de bienestar.\n");
        }

        List<Alerta> alertas = alertaService.listarPorEmpleado(empleado.getIdEmpleado());
        if (!alertas.isEmpty()) {
            Alerta ultimaAlerta = alertas.get(alertas.size() - 1);
            sb.append("- Tiene ").append(alertas.size()).append(" alerta(s) generada(s). ")
                    .append("La mas reciente: \"").append(ultimaAlerta.getMensaje()).append("\".\n");
        }

        List<Recomendacion> recomendaciones = recomendacionService.listarPorEmpleado(empleado.getIdEmpleado());
        if (!recomendaciones.isEmpty()) {
            Recomendacion ultimaRecomendacion = recomendaciones.get(recomendaciones.size() - 1);
            sb.append("- Ultima recomendacion recibida: \"").append(ultimaRecomendacion.getDescripcion()).append("\".\n");
        }

        List<Evaluacion> evaluaciones = evaluacionService.listarPorEmpleado(empleado.getIdEmpleado());
        if (!evaluaciones.isEmpty()) {
            Evaluacion ultimaEvaluacion = evaluaciones.get(evaluaciones.size() - 1);
            sb.append("- Ultima autoevaluacion: pregunta \"").append(ultimaEvaluacion.getPregunta())
                    .append("\", respuesta \"").append(ultimaEvaluacion.getRespuesta()).append("\".\n");
        }

        sb.append("- Cargo: ").append(empleado.getCargo()).append(", empresa: ")
                .append(empleado.getEmpresa().getNombre()).append(".");

        return sb.toString();
    }
}
