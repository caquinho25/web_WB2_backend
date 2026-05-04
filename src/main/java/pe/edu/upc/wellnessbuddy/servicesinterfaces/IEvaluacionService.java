package pe.edu.upc.wellnessbuddy.servicesinterfaces;

import pe.edu.upc.wellnessbuddy.entities.Evaluacion;

import java.util.List;

public interface IEvaluacionService {
    public List<Evaluacion> list();
    public void insert(Evaluacion e);
    public Evaluacion listId(int id);
    public void update(Evaluacion e);
    public void delete(int id);
}
