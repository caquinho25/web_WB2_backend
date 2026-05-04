package pe.edu.upc.wellnessbuddy.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wellnessbuddy.entities.Empresa;
import pe.edu.upc.wellnessbuddy.repositories.IEmpresaRepository;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IEmpresaService;

import java.util.List;

@Service
public class EmpresaServiceImplement implements IEmpresaService {

    @Autowired
    private IEmpresaRepository eR;

    @Override
    public List<Empresa> list() {
        return eR.findAll();

    }

    @Override
    public void insert(Empresa e) {
        eR.save(e);
    }

    @Override
    public Empresa listId(int id) {
        return eR.findById(id).orElse(null);
    }

    @Override
    public void update(Empresa e) {
        eR.save(e);
    }

    @Override
    public void delete(int id) {
        eR.deleteById(id);
    }
}
