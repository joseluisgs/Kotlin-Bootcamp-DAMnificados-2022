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
        return edad * 2;
    }

    public void setEdad(int edad) {
        if (edad > 0) {
            this.edad = edad;
        } else {
            // throw new IllegalArgumentException("La edad debe ser mayor que 0");
            this.edad = 0;
        }
    }

    /*public LocalDateTime getCreatedAt() {
        return createdAt;
    }*/

    public String show() {
        return "Nombre: " + nombre + " Edad: " + edad + " CreatedAt: " + createdAt;
    }
}
