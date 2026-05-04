package pe.edu.upc.wellnessbuddy.dtos;

import pe.edu.upc.wellnessbuddy.entities.RegistroBienestar;

import java.time.LocalDate;

public class RecomendacionDTO {

    private int idRecomendacion;
    private RegistroBienestar registro;
    private String descripcion;
    private LocalDate fecha;

    public RecomendacionDTO() {}

    public int getIdRecomendacion() {
        return idRecomendacion;
    }

    public void setIdRecomendacion(int idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
    }

    public RegistroBienestar getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroBienestar registro) {
        this.registro = registro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
