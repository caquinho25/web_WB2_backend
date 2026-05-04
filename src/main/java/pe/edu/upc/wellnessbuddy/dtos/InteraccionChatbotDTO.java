package pe.edu.upc.wellnessbuddy.dtos;

import pe.edu.upc.wellnessbuddy.entities.Usuario;

import java.time.LocalDate;

public class InteraccionChatbotDTO {

    private int idInteraccion;
    private Usuario usuario;
    private String mensajeUsuario;
    private String respuestaBot;
    private LocalDate fecha;

    public InteraccionChatbotDTO() {}

    public int getIdInteraccion() {
        return idInteraccion;
    }

    public void setIdInteraccion(int idInteraccion) {
        this.idInteraccion = idInteraccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensajeUsuario() {
        return mensajeUsuario;
    }

    public void setMensajeUsuario(String mensajeUsuario) {
        this.mensajeUsuario = mensajeUsuario;
    }

    public String getRespuestaBot() {
        return respuestaBot;
    }

    public void setRespuestaBot(String respuestaBot) {
        this.respuestaBot = respuestaBot;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
