package pe.edu.upc.wellnessbuddy.dtos;

import pe.edu.upc.wellnessbuddy.entities.Empleado;

import java.time.LocalDate;

public class RegistroBienestarDTO {

    private int idRegistro;
    private Empleado empleado;
    private Integer nivelEstres;
    private String estadoAnimo;
    private LocalDate fecha;

    public RegistroBienestarDTO() {}

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Integer getNivelEstres() {
        return nivelEstres;
    }

    public void setNivelEstres(Integer nivelEstres) {
        this.nivelEstres = nivelEstres;
    }

    public String getEstadoAnimo() {
        return estadoAnimo;
    }

    public void setEstadoAnimo(String estadoAnimo) {
        this.estadoAnimo = estadoAnimo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
