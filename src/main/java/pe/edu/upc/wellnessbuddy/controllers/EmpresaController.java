package pe.edu.upc.wellnessbuddy.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wellnessbuddy.dtos.EmpresaDTO;
import pe.edu.upc.wellnessbuddy.entities.Empresa;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IEmpresaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private IEmpresaService service;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<EmpresaDTO> listar() {
        return service.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, EmpresaDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void registrar(@RequestBody EmpresaDTO dto) {
        ModelMapper m = new ModelMapper();
        Empresa e = m.map(dto, Empresa.class);
        service.insert(e);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> listarId(@PathVariable int id) {
        Empresa e = service.listId(id);
        if (e == null) return ResponseEntity.notFound().build();

        ModelMapper m = new ModelMapper();
        return ResponseEntity.ok(m.map(e, EmpresaDTO.class));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> modificar(@RequestBody EmpresaDTO dto) {
        ModelMapper m = new ModelMapper();
        Empresa e = m.map(dto, Empresa.class);

        if (service.listId(e.getIdEmpresa()) == null)
            return ResponseEntity.notFound().build();

        service.update(e);
        return ResponseEntity.ok("Empresa actualizada");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        if (service.listId(id) == null)
            return ResponseEntity.notFound().build();

        service.delete(id);
        return ResponseEntity.ok("Empresa eliminada");
    }
}
