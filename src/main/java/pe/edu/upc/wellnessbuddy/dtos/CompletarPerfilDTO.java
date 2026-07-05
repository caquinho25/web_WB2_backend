package pe.edu.upc.wellnessbuddy.dtos;

public class CompletarPerfilDTO {

    private int idEmpresa;
    private String cargo;

    public CompletarPerfilDTO() {}

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
