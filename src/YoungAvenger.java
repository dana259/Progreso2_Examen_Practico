public class YoungAvenger {
    private int codigo;
    private String nombre;
    private String poderEspecial;
    private int nivelHabilidad;
    private String misionActiva;

    public YoungAvenger(int codigo, String nombre, String poderEspecial, int nivelHabilidad, String misionActiva) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.poderEspecial = poderEspecial;
        this.nivelHabilidad = nivelHabilidad;
        this.misionActiva = misionActiva;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPoderEspecial() {
        return poderEspecial;
    }

    public int getNivelHabilidad() {
        return nivelHabilidad;
    }

    public String getMisionActiva() {
        return misionActiva;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Nombre: " + nombre + ", Poder: " + poderEspecial +
                ", Habilidad: " + nivelHabilidad + ", Misión: " + misionActiva;
    }
}