package pe.edu.upc.wellnessbuddy.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wellnessbuddy.dtos.ChatMensajeDTO;
import pe.edu.upc.wellnessbuddy.dtos.InteraccionChatbotDTO;
import pe.edu.upc.wellnessbuddy.entities.InteraccionChatbot;
import pe.edu.upc.wellnessbuddy.entities.Usuario;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IInteraccionChatbotService;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IUsuarioService;
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

    // ===== NUEVO: chat en vivo =====

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

        String respuestaBot = chatbotIAService.obtenerRespuesta(dto.getMensaje(), contexto);

        InteraccionChatbot nueva = new InteraccionChatbot();
        nueva.setUsuario(usuario);
        nueva.setMensajeUsuario(dto.getMensaje());
        nueva.setRespuestaBot(respuestaBot);
        nueva.setFecha(LocalDate.now());

        service.insert(nueva);

        ModelMapper m = new ModelMapper();
        return ResponseEntity.ok(m.map(nueva, InteraccionChatbotDTO.class));
    }
}
