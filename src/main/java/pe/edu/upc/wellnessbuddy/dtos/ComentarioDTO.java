package pe.edu.upc.wellnessbuddy.dtos;

import pe.edu.upc.wellnessbuddy.entities.Empleado;

import java.time.LocalDate;

public class ComentarioDTO {

    private int idComentario;
    private Empleado empleado;
    private String contenido;
    private LocalDate fecha;

    public ComentarioDTO() {}

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
