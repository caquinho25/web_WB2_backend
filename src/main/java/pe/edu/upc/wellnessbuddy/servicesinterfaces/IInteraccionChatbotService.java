package pe.edu.upc.wellnessbuddy.servicesinterfaces;

import pe.edu.upc.wellnessbuddy.entities.InteraccionChatbot;

import java.util.List;

public interface IInteraccionChatbotService {
    public List<InteraccionChatbot> list();
    public void insert(InteraccionChatbot i);
    public InteraccionChatbot listId(int id);
    public void update(InteraccionChatbot i);
    public void delete(int id);
}
