package pe.edu.upc.wellnessbuddy.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wellnessbuddy.dtos.AlertaDTO;
import pe.edu.upc.wellnessbuddy.entities.Alerta;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IAlertaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alertas")

public class AlertaController {

    @Autowired
    private IAlertaService service;

    @GetMapping
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
    public ResponseEntity<String> modificar(@RequestBody AlertaDTO dto) {
        Alerta a = new ModelMapper().map(dto, Alerta.class);

        if (service.listId(a.getIdAlerta()) == null)
            return ResponseEntity.notFound().build();

        service.update(a);
        return ResponseEntity.ok("Alerta actualizada");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        if (service.listId(id) == null)
            return ResponseEntity.notFound().build();

        service.delete(id);
        return ResponseEntity.ok("Alerta eliminada");
    }
}
