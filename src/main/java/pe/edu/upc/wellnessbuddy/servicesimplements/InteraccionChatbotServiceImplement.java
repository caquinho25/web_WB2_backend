package pe.edu.upc.wellnessbuddy.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wellnessbuddy.entities.InteraccionChatbot;
import pe.edu.upc.wellnessbuddy.repositories.IInteraccionChatbotRepository;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IInteraccionChatbotService;

import java.util.List;

@Service
public class InteraccionChatbotServiceImplement implements IInteraccionChatbotService {

    @Autowired
    private IInteraccionChatbotRepository iR;

    @Override
    public List<InteraccionChatbot> list() {
        return iR.findAll();
    }

    @Override
    public void insert(InteraccionChatbot i) {
        iR.save(i);
    }

    @Override
    public InteraccionChatbot listId(int id) {
        return iR.findById(id).orElse(null);
    }

    @Override
    public void update(InteraccionChatbot i) {
        iR.save(i);
    }

    @Override
    public void delete(int id) {
        iR.deleteById(id);
    }

    @Override
    public List<InteraccionChatbot> listarPorUsername(String username) {
        return iR.buscarPorUsername(username);
    }
}
