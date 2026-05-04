package pe.edu.upc.wellnessbuddy.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "evaluaciones")
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvaluacion;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @Column(length = 255)
    private String pregunta;

    @Column(length = 255)
    private String respuesta;

    @Column(nullable = false)
    private LocalDate fecha;

    public Evaluacion() {}

    public Evaluacion(int idEvaluacion, Empleado empleado, String pregunta, String respuesta, LocalDate fecha) {
        this.idEvaluacion = idEvaluacion;
        this.empleado = empleado;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.fecha = fecha;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
