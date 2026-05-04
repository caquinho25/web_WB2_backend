package pe.edu.upc.wellnessbuddy.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wellnessbuddy.dtos.RecomendacionDTO;
import pe.edu.upc.wellnessbuddy.entities.Recomendacion;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IRecomendacionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recomendaciones")
public class RecomendacionController {

    @Autowired
    private IRecomendacionService service;

    @GetMapping
    public List<RecomendacionDTO> listar() {
        return service.list().stream().map(x -> new ModelMapper().map(x, RecomendacionDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void registrar(@RequestBody RecomendacionDTO dto) {
        ModelMapper m = new ModelMapper();
        Recomendacion r = m.map(dto, Recomendacion.class);
        service.insert(r);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecomendacionDTO> listarId(@PathVariable int id) {
        Recomendacion r = service.listId(id);
        if (r == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new ModelMapper().map(r, RecomendacionDTO.class));
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody RecomendacionDTO dto) {
        Recomendacion r = new ModelMapper().map(dto, Recomendacion.class);

        if (service.listId(r.getIdRecomendacion()) == null)
            return ResponseEntity.notFound().build();

        service.update(r);
        return ResponseEntity.ok("Recomendación actualizada");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        if (service.listId(id) == null)
            return ResponseEntity.notFound().build();

        service.delete(id);
        return ResponseEntity.ok("Recomendación eliminada");
    }
}
