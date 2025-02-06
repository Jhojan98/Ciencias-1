package avl;

public class Nodo {

    int dato, altura;
    Nodo izquierda, derecha;

    public Nodo(int dato) {
        this.dato = dato;
        altura = 1;
    }

    public int getDato() {
        return dato;
    }

    public String getInfo() {
        return String.format("%d\nAlt: %d\nFB: %d",
                dato,
                altura,
                (altura(izquierda) - altura(derecha)));
    }

    private int altura(Nodo n) {
        return n == null ? 0 : n.altura;
    }
}
