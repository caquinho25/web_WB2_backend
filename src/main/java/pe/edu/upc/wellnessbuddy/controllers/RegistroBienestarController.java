package pe.edu.upc.wellnessbuddy.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wellnessbuddy.dtos.RegistroBienestarDTO;
import pe.edu.upc.wellnessbuddy.entities.Empleado;
import pe.edu.upc.wellnessbuddy.entities.RegistroBienestar;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IEmpleadoService;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IRegistroBienestarService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bienestar")
public class RegistroBienestarController {

    @Autowired
    private IRegistroBienestarService service;

    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<RegistroBienestarDTO> listar() {
        return service.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, RegistroBienestarDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public void registrar(@RequestBody RegistroBienestarDTO dto) {
        ModelMapper m = new ModelMapper();
        RegistroBienestar r = m.map(dto, RegistroBienestar.class);
        service.insert(r);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<RegistroBienestarDTO> listarId(@PathVariable int id) {
        RegistroBienestar r = service.listId(id);
        if (r == null) return ResponseEntity.notFound().build();

        ModelMapper m = new ModelMapper();
        return ResponseEntity.ok(m.map(r, RegistroBienestarDTO.class));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> modificar(@RequestBody RegistroBienestarDTO dto) {
        ModelMapper m = new ModelMapper();
        RegistroBienestar r = m.map(dto, RegistroBienestar.class);

        if (service.listId(r.getIdRegistro()) == null)
            return ResponseEntity.notFound().build();

        service.update(r);
        return ResponseEntity.ok("Registro actualizado");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        if (service.listId(id) == null)
            return ResponseEntity.notFound().build();

        service.delete(id);
        return ResponseEntity.ok("Registro eliminado");
    }

    @GetMapping("/mis-registros")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<RegistroBienestarDTO> misRegistros() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Empleado empleado = empleadoService.buscarPorUsername(username);
        if (empleado == null) return List.of();

        return service.listarPorEmpleado(empleado.getIdEmpleado()).stream()
                .map(x -> new ModelMapper().map(x, RegistroBienestarDTO.class))
                .collect(Collectors.toList());
    }
}
