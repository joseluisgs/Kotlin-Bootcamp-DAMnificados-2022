package models;

public class ProgramadorJ extends PersonaJ {
    private int experiencia;

    public ProgramadorJ(String nombre, int edad) {
        super(nombre, edad);
        this.experiencia = 0;
    }

    public ProgramadorJ(int experiencia) {
        super();
        this.experiencia = experiencia;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    @Override
    public String toString() {
        return "ProgramadorJ{" +
                "experiencia=" + experiencia +
                '}';
    }
}
