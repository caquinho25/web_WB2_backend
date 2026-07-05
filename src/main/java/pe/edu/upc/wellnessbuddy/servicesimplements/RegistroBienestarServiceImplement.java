package pe.edu.upc.wellnessbuddy.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wellnessbuddy.entities.Alerta;
import pe.edu.upc.wellnessbuddy.entities.RegistroBienestar;
import pe.edu.upc.wellnessbuddy.repositories.IAlertaRepository;
import pe.edu.upc.wellnessbuddy.repositories.IRegistroBienestarRepository;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IRegistroBienestarService;

import java.util.List;

@Service
public class RegistroBienestarServiceImplement implements IRegistroBienestarService {

    @Autowired
    private IRegistroBienestarRepository rR;

    @Autowired
    private IAlertaRepository aR;

    @Override
    public List<RegistroBienestar> list() {
        return rR.findAll();
    }

    @Override
    public void insert(RegistroBienestar r) {
        rR.save(r);
        generarAlertaSiCorresponde(r);
    }

    @Override
    public RegistroBienestar listId(int id) {
        return rR.findById(id).orElse(null);
    }

    @Override
    public void update(RegistroBienestar r) {
        rR.save(r);
        generarAlertaSiCorresponde(r);
    }

    @Override
    public void delete(int id) {
        rR.deleteById(id);
    }

    @Override
    public List<RegistroBienestar> listarPorEmpleado(int idEmpleado) {
        return rR.buscarPorEmpleado(idEmpleado);
    }

    private void generarAlertaSiCorresponde(RegistroBienestar r) {
        if (r.getNivelEstres() != null && r.getNivelEstres() > 7) {
            Alerta alerta = new Alerta();
            alerta.setRegistro(r);
            alerta.setTipo("Estrés alto");
            alerta.setMensaje("Se detectó un nivel de estrés alto (" + r.getNivelEstres() + "/10) en el registro del " + r.getFecha() + ".");
            alerta.setFecha(r.getFecha());
            aR.save(alerta);
        }
    }
}
