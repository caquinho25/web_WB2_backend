package pe.edu.upc.wellnessbuddy.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComentario;

    @ManyToOne
    @JoinColumn(name = "idEmpleado")
    private Empleado empleado;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    @Column(nullable = false)
    private LocalDate fecha;

    public Comentario() {}

    public Comentario(int idComentario, Empleado empleado, String contenido, LocalDate fecha) {
        this.idComentario = idComentario;
        this.empleado = empleado;
        this.contenido = contenido;
        this.fecha = fecha;
    }

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
