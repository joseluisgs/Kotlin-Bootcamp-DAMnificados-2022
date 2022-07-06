package models;

import java.time.LocalDateTime;

public class PersonaJ {
    private final LocalDateTime createdAt = LocalDateTime.now();
    private String nombre = "Pepe";
    private int edad = 18;

    public PersonaJ() {
        inicializar();
    }

    public PersonaJ(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        inicializar();
    }

    private void inicializar() {
        System.out.println("PersonaJ creada");
    }

    //@NotNull
    public String getNombre() {
        return nombre;
    }

    //@Nullable
    public String getNombreNull() {
        return null;
    }

    //@NotNull
    public String getNombreNotNull() {
        return "Pepe";
    }

    public int getEdad() {
        return edad * 2;
    }


    public void setEdad(int edad) {
        if (edad > 0) {
            this.edad = edad;
        } else {
            throw new IllegalArgumentException("La edad debe ser mayor que 0");
        }
    }

    public boolean isMayorDeEdad() {
        return edad > 18;
    }

    /*public LocalDateTime getCreatedAt() {
        return createdAt;
    }*/

    public String show() {
        return "Nombre: " + nombre + " Edad: " + edad + " CreatedAt: " + createdAt;
    }
}
