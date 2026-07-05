package pe.edu.upc.wellnessbuddy.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wellnessbuddy.entities.Alerta;
import pe.edu.upc.wellnessbuddy.repositories.IAlertaRepository;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IAlertaService;

import java.util.List;

@Service
public class AlertaServiceImplement implements IAlertaService {

    @Autowired
    private IAlertaRepository aR;

    @Override
    public List<Alerta> list() {
        return aR.findAll();
    }

    @Override
    public void insert(Alerta a) {
        aR.save(a);
    }

    @Override
    public Alerta listId(int id) {
        return aR.findById(id).orElse(null);
    }

    @Override
    public void update(Alerta a) {
        aR.save(a);
    }

    @Override
    public void delete(int id) {
        aR.deleteById(id);
    }

    @Override
    public List<Alerta> listarPorEmpleado(int idEmpleado) {
        return aR.buscarPorEmpleado(idEmpleado);
    }
}
