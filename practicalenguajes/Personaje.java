public class Personaje {

    int vida;
    int fuerza;
    int fila;
    int columna;
    String tipo;

    public  Personaje(int vida, int fuerza, int fila,int columna, String tipo) {
        this.vida = vida;
        this.fuerza = fuerza;
        this.fila = fila;
        this.columna = columna;
        this.tipo =  tipo;
    }
    public static void AplicarVentaja(String ventaja, Personaje personaje){

        if(ventaja == "fuerza"){
            personaje.fuerza = personaje.fuerza + 5;
            System.out.println("Tu fuerza aumenta 5 puntos" + personaje.tipo);
        }
        if(ventaja == "recuperacion"){
            personaje.vida = personaje.vida + 5;
            System.out.println("Tu vida aumenta 5 puntos"+ personaje.tipo);
        }
        if(ventaja == "Trampa de oso"){
            personaje.vida = personaje.vida - 5;
            System.out.println("Tu vida disminuye 5 puntos" + personaje.tipo);
        }
        if(ventaja == "planta venenosa"){
            personaje.fuerza = personaje.fuerza - 5;
            System.out.println("Tu fuerza disminuye 5 puntos" + personaje.tipo);
        }
    }
}

