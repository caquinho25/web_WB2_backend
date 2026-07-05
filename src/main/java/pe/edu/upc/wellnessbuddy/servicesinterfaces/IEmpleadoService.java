package pe.edu.upc.wellnessbuddy.servicesinterfaces;

import pe.edu.upc.wellnessbuddy.entities.Empleado;

import java.util.List;

public interface IEmpleadoService {
    public List<Empleado> list();
    public void insert(Empleado e);
    public Empleado listId(int id);
    public void update(Empleado e);
    public void delete(int id);
    public Empleado buscarPorUsername(String username);
}
