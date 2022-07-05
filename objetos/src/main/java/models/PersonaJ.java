package models;

import java.time.LocalDateTime;

public class PersonaJ {
    private final String nombre = "Pepe";
    private final LocalDateTime createdAt = LocalDateTime.now();
    private int edad = 18;

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    /*public LocalDateTime getCreatedAt() {
        return createdAt;
    }*/

    public String show() {
        return "Nombre: " + nombre + " Edad: " + edad + " CreatedAt: " + createdAt;
    }
}
