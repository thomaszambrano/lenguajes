import java.util.Scanner;

public class Alien extends Personaje {

    public Alien(int vida, int fuerza, int fila, int columna, String tipo) {
        super(vida, fuerza, fila, columna, tipo);
    }

    public void mover(Personaje[][] tablero, Depredador depredador) {
        Scanner sc = new Scanner(System.in);
        boolean key = false;
        Personaje casillaDestino = null;

        if(fila == depredador.fila && columna == depredador.columna){
            tablero[fila][columna] = this;
        }else if(tablero[fila][columna].tipo.equals("#")){
            tablero[fila][columna] = tablero[fila][columna];
        }else{
            tablero[fila][columna] = null;
        }

        while (!key) {
            System.out.println("¿A dónde desea moverse?");
            System.out.println("1.Arriba");
            System.out.println("2.Abajo");
            System.out.println("3.Derecha");
            System.out.println("4.Izquierda");

            int Accion = sc.nextInt();
            int arriba = fila - 1;
            int abajo = fila + 1;
            int derecha = columna + 1;
            int izquierda = columna - 1;



            if (Accion == 1 && arriba >= 0 && arriba < tablero.length) {
                casillaDestino = tablero[arriba][columna];
                fila = arriba;
                key = true;
            }
            if (Accion == 2 && abajo >= 0 && abajo < tablero.length) {
                casillaDestino = tablero[abajo][columna];
                fila = abajo;
                key = true;
            }
            if (Accion == 3 && derecha >= 0 && derecha < tablero[0].length) {
                casillaDestino = tablero[fila][derecha];
                columna = derecha;
                key = true;
            }
            if (Accion == 4 && izquierda >= 0 && izquierda < tablero[0].length) {
                casillaDestino = tablero[fila][izquierda];
                columna = izquierda;
                key = true;
            }
        }

        // Verifica si la casilla de destino contiene un objeto
        if (casillaDestino != null) {
            if (casillaDestino.tipo.equals("o")) {
                String x = Items.obtenerVentajaAleatoria();
                Personaje.AplicarVentaja(x, this);
                casillaDestino = null;
                tablero[fila][columna] = this;
            }
            // Si la casilla de destino contiene otro personaje diferente de item, simplemente lo deja
            else if (casillaDestino.tipo.equals("#") || casillaDestino.tipo.equals("🦖")) {
                tablero[fila][columna] = casillaDestino;
            }
        } else {
            tablero[fila][columna] = this;
        }
    }

    public void moverNPC(Personaje[][] tablero, Alien alien) {

        Personaje casillaDestino = null;

        if (!tablero[fila][columna].tipo.equals("👽")) {
            tablero[fila][columna] = tablero[fila][columna];
        } else {
            tablero[fila][columna] = null;
        }

        int[] movimientos_posibles = {-1, -1, -1, -1};

        int arriba = fila - 1;
        int abajo = fila + 1;
        int derecha = columna + 1;
        int izquierda = columna - 1;

        if (arriba >= 0 && arriba < tablero.length) {
            movimientos_posibles[0] = arriba;
        }
        if (abajo >= 0 && abajo < tablero.length) {
            movimientos_posibles[1] = abajo;
        }
        if (derecha >= 0 && derecha < tablero[0].length) {
            movimientos_posibles[2] = derecha;
        }
        if (izquierda >= 0 && izquierda < tablero[0].length) {
            movimientos_posibles[3] = izquierda;
        }

        boolean key = false;
        while (key == false) {
            int random = (int) (Math.random() * movimientos_posibles.length);

            int movimientoSeleccionado = movimientos_posibles[random];

            if (movimientoSeleccionado != -1) {
                key = true;

                if (movimientoSeleccionado == arriba) {
                    arriba = fila - 1;
                    casillaDestino = tablero[arriba][columna];
                    fila = arriba;
                } else if (movimientoSeleccionado == abajo) {
                    abajo = fila + 1;
                    casillaDestino = tablero[abajo][columna];
                    fila = abajo;
                } else if (movimientoSeleccionado == derecha) {
                    derecha = columna + 1;
                    casillaDestino = tablero[fila][derecha];
                    columna = derecha;
                } else if (movimientoSeleccionado == izquierda) {
                    izquierda = columna - 1;
                    casillaDestino = tablero[fila][izquierda];
                    columna = izquierda;
                }
            }
        }
        if (casillaDestino != null) {
            if (casillaDestino.tipo.equals("o")) {
                String x = Items.obtenerVentajaAleatoria();
                Personaje.AplicarVentaja(x, this);
                casillaDestino = null;
                tablero[fila][columna] = this;
            }
            // Si la casilla de destino contiene otro personaje diferente de item, simplemente lo deja
            else if (casillaDestino.tipo.equals("#") || casillaDestino.tipo.equals("🦖")) {
                tablero[fila][columna] = casillaDestino;
            }
        } else {
            tablero[fila][columna] = this;
        }

    }

    public void bloqueo(Personaje[][] tablero) {
        Personaje bloqueo = new Personaje(0, 0, 0, 0, "#");
        System.out.println("¿a donde desea bloquear?");
        System.out.println("1.Arriba");
        System.out.println("2.Abajo");
        System.out.println("3.Derecha");
        System.out.println("4.Izquierda");

        Scanner sc = new Scanner(System.in);
        int Accion = sc.nextInt();
        int arriba = fila - 1;
        int abajo = fila + 1;
        int derecha = columna + 1;
        int izquierda = columna - 1;

        int filaAnt = fila;
        int columnaAnt = columna;


        if (Accion == 1 && arriba >= 0 && arriba < tablero.length) {
            fila = arriba;
        }
        if (Accion == 2 && abajo >= 0 && abajo < tablero.length) {
            fila = abajo;
        }
        if (Accion == 3 && derecha >= 0 && derecha < tablero[0].length) {
            columna = derecha;
        }
        if (Accion == 4 && izquierda >= 0 && izquierda < tablero[0].length) {
            columna = izquierda;
        }
        if(tablero[fila][columna] != null) {
            if (tablero[fila][columna].tipo.equals("🦖")) {
                System.out.println("No se puede poner un bloqueo aqui");
                fila = filaAnt;
                columna = columnaAnt;
                return;
            }else  if (!tablero[fila][columna].tipo.equals("🦖")) {
                System.out.println("No se puede poner un bloqueo aqui, primero debe moverse");
                fila = filaAnt;
                columna = columnaAnt;
                return;
            }
        }else{
            tablero[fila][columna] = bloqueo;
        }
    }
}
