package pe.edu.upc.wellnessbuddy.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "registros_bienestar")
public class RegistroBienestar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRegistro;

    @ManyToOne
    @JoinColumn(name = "idEmpleado")
    private Empleado empleado;

    @Column(nullable = false)
    private Integer nivelEstres;

    @Column(length = 50)
    private String estadoAnimo;

    @Column(nullable = false)
    private LocalDate fecha;

    public RegistroBienestar() {}

    public RegistroBienestar(int idRegistro, Empleado empleado, Integer nivelEstres, String estadoAnimo, LocalDate fecha) {
        this.idRegistro = idRegistro;
        this.empleado = empleado;
        this.nivelEstres = nivelEstres;
        this.estadoAnimo = estadoAnimo;
        this.fecha = fecha;
    }

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
