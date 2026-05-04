package pe.edu.upc.wellnessbuddy.dtos;

import pe.edu.upc.wellnessbuddy.entities.RegistroBienestar;

import java.time.LocalDate;

public class AlertaDTO {

    private int idAlerta;
    private RegistroBienestar registro;
    private String tipo;
    private String mensaje;
    private LocalDate fecha;

    public AlertaDTO() {}

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
