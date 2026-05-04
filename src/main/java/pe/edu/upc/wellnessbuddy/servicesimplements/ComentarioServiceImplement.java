package pe.edu.upc.wellnessbuddy.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wellnessbuddy.entities.Comentario;
import pe.edu.upc.wellnessbuddy.repositories.IComentarioRepository;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IComentarioService;

import java.util.List;

@Service
public class ComentarioServiceImplement implements IComentarioService {

    @Autowired
    private IComentarioRepository cR;

    @Override
    public List<Comentario> list() {
        return cR.findAll();
    }

    @Override
    public void insert(Comentario c) {
        cR.save(c);
    }

    @Override
    public Comentario listId(int id) {
        return cR.findById(id).orElse(null);
    }

    @Override
    public void update(Comentario c) {
        cR.save(c);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }
}
