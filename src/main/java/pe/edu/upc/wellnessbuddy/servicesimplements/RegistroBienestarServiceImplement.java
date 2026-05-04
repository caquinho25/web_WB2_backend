package pe.edu.upc.wellnessbuddy.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wellnessbuddy.entities.RegistroBienestar;
import pe.edu.upc.wellnessbuddy.repositories.IRegistroBienestarRepository;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IRegistroBienestarService;

import java.util.List;

@Service
public class RegistroBienestarServiceImplement implements IRegistroBienestarService {

    @Autowired
    private IRegistroBienestarRepository rR;

    @Override
    public List<RegistroBienestar> list() {
        return rR.findAll();
    }

    @Override
    public void insert(RegistroBienestar r) {
        rR.save(r);
    }

    @Override
    public RegistroBienestar listId(int id) {
        return rR.findById(id).orElse(null);
    }

    @Override
    public void update(RegistroBienestar r) {
        rR.save(r);
    }

    @Override
    public void delete(int id) {
        rR.deleteById(id);
    }
}
