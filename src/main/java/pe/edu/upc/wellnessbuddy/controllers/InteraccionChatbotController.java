package pe.edu.upc.wellnessbuddy.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<InteraccionChatbotDTO> listar() {
        return service.list().stream()
                .map(x -> new ModelMapper().map(x, InteraccionChatbotDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public void registrar(@RequestBody InteraccionChatbotDTO dto) {
        service.insert(new ModelMapper().map(dto, InteraccionChatbot.class));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<InteraccionChatbotDTO> listarId(@PathVariable int id) {
        InteraccionChatbot i = service.listId(id);
        if (i == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new ModelMapper().map(i, InteraccionChatbotDTO.class));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> modificar(@RequestBody InteraccionChatbotDTO dto) {
        InteraccionChatbot i = new ModelMapper().map(dto, InteraccionChatbot.class);
        if (service.listId(i.getIdInteraccion()) == null)
            return ResponseEntity.notFound().build();
        service.update(i);
        return ResponseEntity.ok("Interaccion actualizada");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        if (service.listId(id) == null)
            return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.ok("Interaccion eliminada");
    }

}
