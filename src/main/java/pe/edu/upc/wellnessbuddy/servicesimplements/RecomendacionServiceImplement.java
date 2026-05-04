package pe.edu.upc.wellnessbuddy.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wellnessbuddy.entities.Recomendacion;
import pe.edu.upc.wellnessbuddy.repositories.IRecomendacionRepository;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IRecomendacionService;

import java.util.List;

@Service
public class RecomendacionServiceImplement implements IRecomendacionService {

    @Autowired
    private IRecomendacionRepository rR;

    @Override
    public List<Recomendacion> list() {
        return rR.findAll();
    }

    @Override
    public void insert(Recomendacion r) {
        rR.save(r);
    }

    @Override
    public Recomendacion listId(int id) {
        return rR.findById(id).orElse(null);
    }

    @Override
    public void update(Recomendacion r) {
        rR.save(r);
    }

    @Override
    public void delete(int id) {
        rR.deleteById(id);
    }
}
