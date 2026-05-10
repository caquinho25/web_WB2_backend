package pe.edu.upc.wellnessbuddy.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wellnessbuddy.dtos.ComentarioDTO;
import pe.edu.upc.wellnessbuddy.entities.Comentario;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IComentarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private IComentarioService service;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<ComentarioDTO> listar() {
        return service.list().stream()
                .map(x -> new ModelMapper().map(x, ComentarioDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public void registrar(@RequestBody ComentarioDTO dto) {
        service.insert(new ModelMapper().map(dto, Comentario.class));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<ComentarioDTO> listarId(@PathVariable int id) {
        Comentario c = service.listId(id);
        if (c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new ModelMapper().map(c, ComentarioDTO.class));
    }

    // Solo ADMIN modifica comentarios de otros
    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<String> modificar(@RequestBody ComentarioDTO dto) {
        Comentario c = new ModelMapper().map(dto, Comentario.class);
        if (service.listId(c.getIdComentario()) == null)
            return ResponseEntity.notFound().build();
        service.update(c);
        return ResponseEntity.ok("Comentario actualizado");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        if (service.listId(id) == null)
            return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.ok("Comentario eliminado");
    }
}
