package pe.edu.upc.wellnessbuddy.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "alertas")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAlerta;

    @ManyToOne
    @JoinColumn(name = "idRegistro")
    private RegistroBienestar registro;

    @Column(length = 50)
    private String tipo;

    @Column(columnDefinition = "TEXT")
    private String mensaje;

    @Column(nullable = false)
    private LocalDate fecha;

    public Alerta() {}

    public Alerta(int idAlerta, RegistroBienestar registro, String tipo, String mensaje, LocalDate fecha) {
        this.idAlerta = idAlerta;
        this.registro = registro;
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public int getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(int idAlerta) {
        this.idAlerta = idAlerta;
    }

    public RegistroBienestar getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroBienestar registro) {
        this.registro = registro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
