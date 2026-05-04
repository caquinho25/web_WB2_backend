package pe.edu.upc.wellnessbuddy.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wellnessbuddy.dtos.InteraccionChatbotDTO;
import pe.edu.upc.wellnessbuddy.entities.InteraccionChatbot;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IInteraccionChatbotService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chatbot")
public class InteraccionChatbotController {

    @Autowired
    private IInteraccionChatbotService service;

    @GetMapping
    public List<InteraccionChatbotDTO> listar() {
        return service.list().stream()
                .map(x -> new ModelMapper().map(x, InteraccionChatbotDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void registrar(@RequestBody InteraccionChatbotDTO dto) {
        service.insert(new ModelMapper().map(dto, InteraccionChatbot.class));
    }

}
