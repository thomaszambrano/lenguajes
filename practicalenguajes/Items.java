import java.util.Random;

public class Items extends Personaje {

    public Items(int vida, int fuerza, int fila, int columna, String tipo) {
        super(vida, fuerza, fila, columna, tipo);
    }

    public static void generarItems(Personaje[][] tablero, int x, int y) {
        int Items = (x * y) / 2;
        int itemsGenerados = 0;
        Random random = new Random();

        while (itemsGenerados < Items) {
            int filaAleatoria = random.nextInt(x);
            int columnaAleatoria = random.nextInt(y);

            if (tablero[filaAleatoria][columnaAleatoria] == null) {
                Personaje item = new Personaje(0, 0, filaAleatoria, columnaAleatoria, "o");
                tablero[filaAleatoria][columnaAleatoria] = item;

                itemsGenerados++;
            }
        }
    }

    public static String obtenerVentajaAleatoria() {
        Random random = new Random();
        int ventajaAleatoria = random.nextInt(4);

        switch (ventajaAleatoria) {
            case 0:
                return "fuerza";
            case 1:
                return "recuperacion";
            case 2:
                return "Trampa de oso";
            case 3:
                return "planta venenosa";
            default:
                return "";
        }
    }
}
