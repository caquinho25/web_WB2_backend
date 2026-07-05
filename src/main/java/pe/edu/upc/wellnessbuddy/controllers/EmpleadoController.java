package pe.edu.upc.wellnessbuddy.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wellnessbuddy.dtos.CompletarPerfilDTO;
import pe.edu.upc.wellnessbuddy.dtos.EmpleadoDTO;
import pe.edu.upc.wellnessbuddy.entities.Empleado;
import pe.edu.upc.wellnessbuddy.entities.Empresa;
import pe.edu.upc.wellnessbuddy.entities.Usuario;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IEmpleadoService;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService service;

    @Autowired
    private IUsuarioService usuarioService;

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

    @GetMapping("/mi-perfil")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<EmpleadoDTO> miPerfil() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Empleado e = service.buscarPorUsername(username);
        if (e == null) return ResponseEntity.notFound().build();

        ModelMapper m = new ModelMapper();
        return ResponseEntity.ok(m.map(e, EmpleadoDTO.class));
    }

    @PostMapping("/mi-perfil")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<EmpleadoDTO> completarPerfil(@RequestBody CompletarPerfilDTO dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (service.buscarPorUsername(username) != null) {
            return ResponseEntity.badRequest().build();
        }

        Usuario usuario = usuarioService.buscarPorUsername(username);
        if (usuario == null) return ResponseEntity.notFound().build();

        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(dto.getIdEmpresa());

        Empleado nuevo = new Empleado();
        nuevo.setUsuario(usuario);
        nuevo.setEmpresa(empresa);
        nuevo.setCargo(dto.getCargo());
        nuevo.setEstado(true);

        service.insert(nuevo);

        ModelMapper m = new ModelMapper();
        return ResponseEntity.ok(m.map(nuevo, EmpleadoDTO.class));
    }
}
