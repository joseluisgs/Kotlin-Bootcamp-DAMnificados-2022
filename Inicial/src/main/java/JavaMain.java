public class JavaMain {
    public static void main(String[] args) {
        System.out.println("Hola Java Main");

        Integer num = null;
        int num2 = 0;

        float num3 = num2;

        int num4 = (int) num3;

        String casa = "Esto es una casa";
        String color = "roja";

        System.out.println(casa + " " + color + " con longitud: " + casa.length());

        String json = "{\n" +
                "    \"arrayColores\":[{\n" +
                "            \"rojo\":\"+casa+\",\n" +
                "            \"verde\":\"#0f0\",\n" +
                "            \"azul\":\"#00f\",\n" +
                "            \"cyan\":\"#0ff\",\n" +
                "            \"magenta\":\"#f0f\",\n" +
                "            \"amarillo\":\"#ff0\",\n" +
                "            \"negro\":\"#000\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        int existe = 3;
        if (existe >= 1 && existe <= 10) {
            System.out.println("Existe : " + existe);
        }
    }
}
