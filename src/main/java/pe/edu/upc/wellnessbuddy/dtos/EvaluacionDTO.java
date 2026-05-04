package pe.edu.upc.wellnessbuddy.dtos;

import pe.edu.upc.wellnessbuddy.entities.Empleado;

import java.time.LocalDate;

public class EvaluacionDTO {

    private int idEvaluacion;
    private Empleado empleado;
    private String pregunta;
    private String respuesta;
    private LocalDate fecha;

    public EvaluacionDTO() {}

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
