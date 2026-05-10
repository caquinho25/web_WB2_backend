package pe.edu.upc.wellnessbuddy.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wellnessbuddy.dtos.EmpleadoDTO;
import pe.edu.upc.wellnessbuddy.entities.Empleado;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IEmpleadoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService service;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<EmpleadoDTO> listar() {
        return service.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, EmpleadoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public void registrar(@RequestBody EmpleadoDTO dto) {
        ModelMapper m = new ModelMapper();
        Empleado e = m.map(dto, Empleado.class);
        service.insert(e);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<EmpleadoDTO> listarId(@PathVariable int id) {
        Empleado e = service.listId(id);
        if (e == null) return ResponseEntity.notFound().build();

        ModelMapper m = new ModelMapper();
        return ResponseEntity.ok(m.map(e, EmpleadoDTO.class));
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<String> modificar(@RequestBody EmpleadoDTO dto) {
        ModelMapper m = new ModelMapper();
        Empleado e = m.map(dto, Empleado.class);

        if (service.listId(e.getIdEmpleado()) == null)
            return ResponseEntity.notFound().build();

        service.update(e);
        return ResponseEntity.ok("Empleado actualizado");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        if (service.listId(id) == null)
            return ResponseEntity.notFound().build();

        service.delete(id);
        return ResponseEntity.ok("Empleado eliminado");
    }
}
