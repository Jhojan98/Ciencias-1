/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArbolBinarioBase;

/**
 *
 * @author Santiago
 */
public class Test {
    public static void main(String[] args) {
        // Crear nodos hoja
        NodoHoja hoja1 = new NodoHoja("Perro");
        NodoHoja hoja2 = new NodoHoja(234);
        NodoHoja hoja3 = new NodoHoja(true);
        NodoHoja hoja4 = new NodoHoja("Vaca");

        // Crear nodo compuesto y agregar hojas
        NodoCompuesto raiz = new NodoCompuesto(100000);
        raiz.agregarHijo(hoja1);

        // Crear otro nodo compuesto
        NodoCompuesto nodoIntermedio = new NodoCompuesto(Math.PI);
        nodoIntermedio.agregarHijo(hoja3);
        nodoIntermedio.agregarHijo(hoja4);

        // Agregar nodo compuesto como hijo de la raíz
        raiz.agregarHijo(nodoIntermedio);
        
        // Con hijos ya asignados
        raiz.agregarHijo(hoja2);

        // Imprimir el árbol
        raiz.imprimirContenido();
    }
}
