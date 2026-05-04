package pe.edu.upc.wellnessbuddy.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "recomendaciones")
public class Recomendacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecomendacion;

    @ManyToOne
    @JoinColumn(name = "idRegistro")
    private RegistroBienestar registro;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fecha;

    public Recomendacion() {}

    public Recomendacion(int idRecomendacion, RegistroBienestar registro, String descripcion, LocalDate fecha) {
        this.idRecomendacion = idRecomendacion;
        this.registro = registro;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

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
