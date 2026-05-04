package pe.edu.upc.wellnessbuddy.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wellnessbuddy.entities.Empleado;
import pe.edu.upc.wellnessbuddy.repositories.IEmpleadoRepository;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IEmpleadoService;

import java.util.List;

@Service
public class EmpleadoServiceImplement implements IEmpleadoService {

    @Autowired
    private IEmpleadoRepository eR;

    @Override
    public List<Empleado> list() {
        return eR.findAll();
    }

    @Override
    public void insert(Empleado e) {
        eR.save(e);
    }

    @Override
    public Empleado listId(int id) {
        return eR.findById(id).orElse(null);
    }

    @Override
    public void update(Empleado e) {
        eR.save(e);
    }

    @Override
    public void delete(int id) {
        eR.deleteById(id);
    }
}
