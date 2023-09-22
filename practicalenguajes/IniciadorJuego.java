import java.util.Scanner;
import java.util.Random;

public class IniciadorJuego {

    public static void iniciarJuego() {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿De cuántas filas quiere su matrix?");
        int x = sc.nextInt();

        System.out.println("¿De cuántas columnas quiere su matrix?");
        int y = sc.nextInt();

        Personaje[][] tablero = new Personaje[x][y];

        System.out.println("¿Qué personaje desea escoger?");
        System.out.println("1. Alien");
        System.out.println("2. Depredador");

        int eleccion = sc.nextInt();

        Depredador depredador = new Depredador(100, 50, 100, 100, "🦖");
        Alien alien = new Alien(100, 75, 100, 100, "👽");

        if (eleccion == 1) {
            alien.fila = 0;
            alien.columna = 0;
            depredador.fila = x - 1;
            depredador.columna = y - 1;
        } else if (eleccion == 2) {
            alien.fila = x - 1;
            alien.columna = y - 1;
            depredador.fila = 0;
            depredador.columna = 0;
        }

        boolean win = false;

        tablero[depredador.fila][depredador.columna] = depredador;
        tablero[alien.fila][alien.columna] = alien;
        Items.generarItems(tablero, x, y);

        while (!win) {
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (tablero[i][j] != null) {
                        System.out.print("[" + tablero[i][j].tipo + "]");
                    } else {
                        System.out.print("[ ]");
                    }
                }
                System.out.println();
            }

            System.out.println("La vida del alien es: " + alien.vida);
            System.out.println("La fuerza del alien es: " + alien.fuerza);
            System.out.println("La vida del depredador es: " + depredador.vida);
            System.out.println("La fuerza del depredador es: " + depredador.fuerza);

            System.out.println("¿Qué desea hacer?");
            System.out.println("1. Habilidad Especial");
            System.out.println("2. Moverse");

            int accion = sc.nextInt();

            if (eleccion == 1) { // Juego con el alien
                Random random = new Random();
                int accionNPC = random.nextInt(2);
                if (accion == 1) {
                    alien.bloqueo(tablero);
                } else if (accion == 2) {
                    alien.mover(tablero, depredador);
                }

                if (accionNPC == 0) {
                    depredador.moverNPC(tablero, alien);
                } else if (accionNPC == 1) {
                    System.out.println("El depredador atacó");
                    depredador.habilidad(tablero, alien);
                }
            }

            if (eleccion == 2) { // Juego con el depredador
                Random random = new Random();
                int accionNPC = random.nextInt(2);
                if (accion == 1) {
                    depredador.habilidad(tablero, alien);
                } else if (accion == 2) {
                    depredador.mover(tablero, alien);
                }
                if (accionNPC == 0) {
                    alien.moverNPC(tablero, alien);
                } else if (accionNPC == 1) {
                    System.out.println("El alien bloqueó");
                    alien.bloqueo(tablero);
                }
            }

            if (depredador.columna == alien.columna && depredador.fila == alien.fila) {
                if (alien.fuerza > depredador.fuerza) {
                    depredador.vida = depredador.vida - alien.fuerza;
                    System.out.println("El alien ganó el enfrentamiento");
                    System.out.println("La vida del depredador es: " + depredador.vida);
                } else if (alien.fuerza < depredador.fuerza) {
                    alien.vida = alien.vida - depredador.fuerza;
                    System.out.println("El depredador ganó el combate");
                    System.out.println("La vida del alien es: " + alien.vida);
                } else {
                    System.out.println("Hubo un empate");
                }
            }

            if (depredador.vida <= 0) {
                win = true;
                System.out.println("Gana el alien");
            } else if (alien.vida <= 0) {
                win = true;
                System.out.println("Gana el depredador");
            }
        }
    }
}
