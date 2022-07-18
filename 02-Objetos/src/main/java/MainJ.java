import models.Persona;
import models.PersonaJ;

public class MainJ {
    public static void main(String[] args) {
        PersonaJ persona = new PersonaJ("Pepe", 18);
        System.out.println(persona.show());

        Persona persona2 = new Persona("Juan", 20);
        persona2.getEdadDoble();
    }
}
