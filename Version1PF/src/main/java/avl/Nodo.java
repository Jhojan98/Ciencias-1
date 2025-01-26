package avl;

public class Nodo {

    int dato, altura;
    Nodo izquierda, derecha;

    Nodo(int dato) {
        this.dato = dato;
        altura = 1;
    }

    public int getDato() {
        return dato;
    }
}
