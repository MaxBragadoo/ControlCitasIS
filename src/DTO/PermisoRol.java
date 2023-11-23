package DTO;

public class PermisoRol {

    private int idPermisoRol;
    private Permiso permiso;
    private Rol rol;

    public PermisoRol() {
    }

    public int getIdPermisoRol() {
        return idPermisoRol;
    }

    public void setIdPermisoRol(int idPermisoRol) {
        this.idPermisoRol = idPermisoRol;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
