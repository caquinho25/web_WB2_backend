package pe.edu.upc.wellnessbuddy.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wellnessbuddy.dtos.AlertaDTO;
import pe.edu.upc.wellnessbuddy.entities.Alerta;
import pe.edu.upc.wellnessbuddy.entities.Empleado;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IAlertaService;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IEmpleadoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

    @Autowired
    private IAlertaService service;

    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<AlertaDTO> listar() {
        return service.list().stream().map(x -> new ModelMapper().map(x, AlertaDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void registrar(@RequestBody AlertaDTO dto) {
        service.insert(new ModelMapper().map(dto, Alerta.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaDTO> listarId(@PathVariable int id) {
        Alerta a = service.listId(id);
        if (a == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new ModelMapper().map(a, AlertaDTO.class));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> modificar(@RequestBody AlertaDTO dto) {
        Alerta a = new ModelMapper().map(dto, Alerta.class);

        if (service.listId(a.getIdAlerta()) == null)
            return ResponseEntity.notFound().build();

        service.update(a);
        return ResponseEntity.ok("Alerta actualizada");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        if (service.listId(id) == null)
            return ResponseEntity.notFound().build();

        service.delete(id);
        return ResponseEntity.ok("Alerta eliminada");
    }

    @GetMapping("/mis-alertas")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<AlertaDTO> misAlertas() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Empleado empleado = empleadoService.buscarPorUsername(username);
        if (empleado == null) return List.of();

        return service.listarPorEmpleado(empleado.getIdEmpleado()).stream()
                .map(x -> new ModelMapper().map(x, AlertaDTO.class))
                .collect(Collectors.toList());
    }
}
