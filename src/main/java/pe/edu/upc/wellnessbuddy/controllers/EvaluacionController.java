package pe.edu.upc.wellnessbuddy.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wellnessbuddy.dtos.EvaluacionDTO;
import pe.edu.upc.wellnessbuddy.entities.Evaluacion;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IEvaluacionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/evaluaciones")

public class EvaluacionController {

    @Autowired
    private IEvaluacionService service;

    @GetMapping
    public List<EvaluacionDTO> listar() {
        return service.list().stream()
                .map(x -> new ModelMapper().map(x, EvaluacionDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public void registrar(@RequestBody EvaluacionDTO dto) {
        service.insert(new ModelMapper().map(dto, Evaluacion.class));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<EvaluacionDTO> listarId(@PathVariable int id) {
        Evaluacion e = service.listId(id);
        if (e == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new ModelMapper().map(e, EvaluacionDTO.class));
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<String> modificar(@RequestBody EvaluacionDTO dto) {
        Evaluacion e = new ModelMapper().map(dto, Evaluacion.class);
        if (service.listId(e.getIdEvaluacion()) == null)
            return ResponseEntity.notFound().build();
        service.update(e);
        return ResponseEntity.ok("Evaluacion actualizada");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        if (service.listId(id) == null)
            return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.ok("Evaluacion eliminada");
    }
}
