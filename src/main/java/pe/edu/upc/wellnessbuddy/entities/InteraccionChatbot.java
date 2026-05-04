package pe.edu.upc.wellnessbuddy.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "interacciones_chatbot")
public class InteraccionChatbot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInteraccion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @Column(columnDefinition = "TEXT")
    private String mensajeUsuario;

    @Column(columnDefinition = "TEXT")
    private String respuestaBot;

    @Column(nullable = false)
    private LocalDate fecha;

    public InteraccionChatbot() {}

    public InteraccionChatbot(int idInteraccion, Usuario usuario, String mensajeUsuario, String respuestaBot, LocalDate fecha) {
        this.idInteraccion = idInteraccion;
        this.usuario = usuario;
        this.mensajeUsuario = mensajeUsuario;
        this.respuestaBot = respuestaBot;
        this.fecha = fecha;
    }

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
