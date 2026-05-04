package pe.edu.upc.wellnessbuddy.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wellnessbuddy.dtos.RegistroBienestarDTO;
import pe.edu.upc.wellnessbuddy.entities.RegistroBienestar;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IRegistroBienestarService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bienestar")
public class RegistroBienestarController {

    @Autowired
    private IRegistroBienestarService service;

    @GetMapping
    public List<RegistroBienestarDTO> listar() {
        return service.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, RegistroBienestarDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void registrar(@RequestBody RegistroBienestarDTO dto) {
        ModelMapper m = new ModelMapper();
        RegistroBienestar r = m.map(dto, RegistroBienestar.class);
        service.insert(r);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroBienestarDTO> listarId(@PathVariable int id) {
        RegistroBienestar r = service.listId(id);
        if (r == null) return ResponseEntity.notFound().build();

        ModelMapper m = new ModelMapper();
        return ResponseEntity.ok(m.map(r, RegistroBienestarDTO.class));
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody RegistroBienestarDTO dto) {
        ModelMapper m = new ModelMapper();
        RegistroBienestar r = m.map(dto, RegistroBienestar.class);

        if (service.listId(r.getIdRegistro()) == null)
            return ResponseEntity.notFound().build();

        service.update(r);
        return ResponseEntity.ok("Registro actualizado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        if (service.listId(id) == null)
            return ResponseEntity.notFound().build();

        service.delete(id);
        return ResponseEntity.ok("Registro eliminado");
    }
}
