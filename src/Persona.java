public final class Persona {
    private String nombre;
    private String apellido;

    public Persona(String nombre, String apellidd, EstadoCivil.EnumEstadoCivil estadoCivil) {
        this.nombre = nombre;
        this.apellido = apellidd;
        this.estadoCivil = estadoCivil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidd() {
        return apellido;
    }

    public void setApellidd(String apellidd) {
        this.apellido = apellidd;
    }

    public EstadoCivil.EnumEstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil.EnumEstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    private EstadoCivil.EnumEstadoCivil estadoCivil;

    public Persona cambiarEstadoCivil(EstadoCivil.EnumEstadoCivil nuevoEstadoCivil) {
        return new Persona(nombre, apellido, nuevoEstadoCivil);
    }
}
