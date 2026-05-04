package pe.edu.upc.wellnessbuddy.servicesinterfaces;

import pe.edu.upc.wellnessbuddy.entities.RegistroBienestar;

import java.util.List;

public interface IRegistroBienestarService {
    public List<RegistroBienestar> list();
    public void insert(RegistroBienestar r);
    public RegistroBienestar listId(int id);
    public void update(RegistroBienestar r);
    public void delete(int id);
}
