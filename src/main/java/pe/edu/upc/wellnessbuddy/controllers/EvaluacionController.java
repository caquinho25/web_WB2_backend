package pe.edu.upc.wellnessbuddy.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void registrar(@RequestBody EvaluacionDTO dto) {
        service.insert(new ModelMapper().map(dto, Evaluacion.class));
    }
}
