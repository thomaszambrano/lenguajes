import java.util.Scanner;

public class Depredador extends Personaje {

    public Depredador(int vida, int fuerza, int fila, int columna, String tipo) {
        super(vida, fuerza, fila, columna, tipo);
    }

    public void mover(Personaje[][] tablero, Alien alien) {
        Scanner sc = new Scanner(System.in);
        boolean key = false;
        Personaje casillaDestino = null;

        if(fila == alien.fila && columna == alien.columna){
            tablero[fila][columna] = alien;
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
                if(tablero[arriba][columna] != null){
                    if(tablero[arriba][columna].tipo.equals("#")){
                        System.out.println("Hay un bloqueo en esta posicion");
                        tablero[fila][columna] = this;
                        return;
                    }
                }
                casillaDestino = tablero[arriba][columna];
                fila = arriba;
                key = true;
            }
            if (Accion == 2 && abajo >= 0 && abajo < tablero.length) {
                if(tablero[abajo][columna] != null){
                    if(tablero[abajo][columna].tipo.equals("#")){
                        System.out.println("Hay un bloqueo en esta posicion");
                        tablero[fila][columna] = this;
                        return;
                    }
                }
                casillaDestino = tablero[abajo][columna];
                fila = abajo;
                key = true;
            }
            if (Accion == 3 && derecha >= 0 && derecha < tablero[0].length) {
                if(tablero[fila][derecha] != null){
                    if(tablero[fila][derecha].tipo.equals("#")){
                        System.out.println("Hay un bloqueo en esta posicion");
                        tablero[fila][columna] = this;
                        return;
                    }
                }
                casillaDestino = tablero[fila][derecha];
                columna = derecha;
                key = true;
            }
            if (Accion == 4 && izquierda >= 0 && izquierda < tablero[0].length) {
                if(tablero[fila][izquierda] != null){
                    if(tablero[fila][izquierda].tipo.equals("#")){
                        System.out.println("Hay un bloqueo en esta posicion, elija otra");
                        tablero[fila][columna] = this;
                        return;
                    }
                }
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

            else if (casillaDestino.tipo.equals("#") || casillaDestino.tipo.equals("🦖")) {
                tablero[fila][columna] = casillaDestino;
            }
        } else {
            tablero[fila][columna] = this;
        }
    }

    public void moverNPC(Personaje[][] tablero, Alien alien) {

        Personaje casillaDestino = null;

        if(fila == alien.fila && columna == alien.columna){
            tablero[fila][columna] = alien;
        }else{
            tablero[fila][columna] = null;
        }



        int[] movimientos_posibles = {-1, -1, -1, -1};

        int arriba = fila - 1;
        int abajo = fila + 1;
        int derecha = columna + 1;
        int izquierda = columna - 1;

        if (arriba >= 0 && arriba < tablero.length) {
            if(tablero[arriba][columna] != null){
                if(!tablero[arriba][columna].tipo.equals("#")){
                    movimientos_posibles[0] = arriba;
                }
            }
        }
        if (abajo >= 0 && abajo < tablero.length) {
            if(tablero[abajo][columna] != null){
                if(!tablero[abajo][columna].tipo.equals("#")){
                    movimientos_posibles[1] = abajo;
                }
            }
        }
        if (derecha >= 0 && derecha < tablero[0].length) {
            if(tablero[fila][derecha] != null){
                if(!tablero[fila][derecha].tipo.equals("#")){
                    movimientos_posibles[2] = derecha;
                }
            }

        }
        if (izquierda >= 0 && izquierda < tablero[0].length) {
            if(tablero[fila][izquierda] != null){
                if(!tablero[fila][izquierda].tipo.equals("#")){
                    movimientos_posibles[3] = izquierda;
                }
            }
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
    public void habilidad(Personaje[][] tablero, Alien alien){
        int x = 0;
        int fila = this.fila;
        int columna = this.columna;

        //Diagonal arriba-izquierda
        while (fila >= 0 && columna >= 0) {
            if(tablero[fila][columna] == tablero[alien.fila][alien.columna]){
                alien.vida = alien.vida-50;
                System.out.println("El depredador golpeo al alien con su habilidad");
                x = 1;
            }
            fila--;
            columna--;
        }
        fila = this.fila;
        columna = this.columna;
        //Diagonal abajo-derecha
        while (fila < tablero.length && columna < tablero[0].length) {
            if(tablero[fila][columna] == tablero[alien.fila][alien.columna]){
                alien.vida = alien.vida-50;
                System.out.println("El depredador golpeo al alien con su habilidad");
                x = 1;
            }
            fila++;
            columna++;
        }

        if(x == 0){
            System.out.println("El ataque fallo");
        }

    }
}



