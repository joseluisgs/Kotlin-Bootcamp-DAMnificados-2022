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

        // arrays
        int[] array = new int[10]; // 0..9
        System.out.println(array[7]);
        int[][] ventas = new int[4][6];
        System.out.println(ventas[1][2]);
        String salida = (existe % 2 == 0) ? "es par" : "es impar";

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }

        int[] misEnteros = new int[10];
        for (int i = 0; i < misEnteros.length; i++) {
            misEnteros[i] = misEnteros[i] * 2;
            System.out.println(misEnteros[i]);
        }
    }
}
