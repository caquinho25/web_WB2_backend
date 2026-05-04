package pe.edu.upc.wellnessbuddy.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ComentarioDTO> listar() {
        return service.list().stream()
                .map(x -> new ModelMapper().map(x, ComentarioDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void registrar(@RequestBody ComentarioDTO dto) {
        service.insert(new ModelMapper().map(dto, Comentario.class));
    }
}
