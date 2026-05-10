package pe.edu.upc.wellnessbuddy.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wellnessbuddy.dtos.UsuarioDTO;
import pe.edu.upc.wellnessbuddy.entities.Usuario;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService service;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UsuarioDTO> listar() {
        return service.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UsuarioDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void registrar(@RequestBody UsuarioDTO dto) {
        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);
        u.setStatusUsuario(true);
        service.insert(u);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<UsuarioDTO> listarId(@PathVariable("id") int id) {
        Usuario u = service.listId(id);
        if (u == null) {
            return ResponseEntity.notFound().build();
        }
        ModelMapper m = new ModelMapper();
        return ResponseEntity.ok(m.map(u, UsuarioDTO.class));
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<String> modificar(@RequestBody UsuarioDTO dto) {
        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);

        if (service.listId(u.getIdUsuario()) == null) {
            return ResponseEntity.notFound().build();
        }

        service.update(u);
        return ResponseEntity.ok("Usuario actualizado");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable("id") int id) {
        if (service.listId(id) == null) {
            return ResponseEntity.notFound().build();
        }

        service.delete(id);
        return ResponseEntity.ok("Usuario eliminado");
    }
}
