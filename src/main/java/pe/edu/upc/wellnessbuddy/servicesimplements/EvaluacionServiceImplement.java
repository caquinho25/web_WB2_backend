package pe.edu.upc.wellnessbuddy.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wellnessbuddy.entities.Evaluacion;
import pe.edu.upc.wellnessbuddy.repositories.IEvaluacionRepository;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IEvaluacionService;

import java.util.List;

@Service
public class EvaluacionServiceImplement implements IEvaluacionService {

    @Autowired
    private IEvaluacionRepository eR;

    @Override
    public List<Evaluacion> list() {
        return eR.findAll();
    }

    @Override
    public void insert(Evaluacion e) {
        eR.save(e);
    }

    @Override
    public Evaluacion listId(int id) {
        return eR.findById(id).orElse(null);
    }

    @Override
    public void update(Evaluacion e) {
        eR.save(e);
    }

    @Override
    public void delete(int id) {
        eR.deleteById(id);
    }
}
