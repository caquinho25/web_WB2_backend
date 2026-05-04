package pe.edu.upc.wellnessbuddy.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "empresas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpresa;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 100)
    private String rubro;

    @Column(nullable = false)
    private Boolean estado;

    public Empresa() {}

    public Empresa(int idEmpresa, String nombre, String rubro, Boolean estado) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.rubro = rubro;
        this.estado = estado;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
