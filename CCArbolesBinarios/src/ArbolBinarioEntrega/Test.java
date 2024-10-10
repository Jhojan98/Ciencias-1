/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArbolBinarioEntrega;
import java.util.ArrayList;

/**
 *
 * @author Estudiantes
 */
public class Test {
    public static void main(String[] args) {
        ArbolBinario<Integer> arbolPrueba = new ArbolBinario<>();
        NodoBinario<Integer> nodoPrueba1 = new NodoBinario<>(10);
        NodoBinario<Integer> nodoPrueba2 = new NodoBinario<>(5);
        NodoBinario<Integer> nodoPrueba3 = new NodoBinario<>(12);
        NodoBinario<Integer> nodoPrueba4 = new NodoBinario<>(4);
        NodoBinario<Integer> nodoPrueba5 = new NodoBinario<>(6);
        NodoBinario<Integer> nodoPrueba6 = new NodoBinario<>(11);
        NodoBinario<Integer> nodoPrueba7 = new NodoBinario<>(13);
        
        arbolPrueba.dispararAgregarNodo(nodoPrueba1);
        arbolPrueba.dispararAgregarNodo(nodoPrueba2);
        arbolPrueba.dispararAgregarNodo(nodoPrueba3);
        arbolPrueba.dispararAgregarNodo(nodoPrueba4);
        arbolPrueba.dispararAgregarNodo(nodoPrueba5);
        arbolPrueba.dispararAgregarNodo(nodoPrueba6);
        arbolPrueba.dispararAgregarNodo(nodoPrueba7);
        
        ArrayList<NodoBinario<Integer>> inorder;
        
        inorder = arbolPrueba.dispararInorden();
        for(NodoBinario<Integer> nodo : inorder) {
            System.out.println(nodo.getDato());
        }
        
        System.out.println("Altura de arbolPrueba: " + arbolPrueba.altura());
        System.out.println("Cantidad de nodos: " + arbolPrueba.dispararContarNodos());
        System.out.println("arbolPrueba lleno? " + arbolPrueba.dispararArbolEsLleno());
        System.out.println("arbolPrueba completo? " + arbolPrueba.dispararArbolEsCompleto());
        System.out.println("arbolPrueba perfecto? " + arbolPrueba.dispararArbolEsPerfecto());
        System.out.println("Nivel de 13: " + arbolPrueba.dispararObtenerNivel(nodoPrueba7));
        System.out.print(nodoPrueba5 + "    VS    ");
        System.out.println(arbolPrueba.dispararObtenerNodo(6));
        System.out.println("Padre de 4: " + nodoPrueba4.getPadre());
        System.out.println("Padre de 6: " + nodoPrueba5.getPadre());
        
        arbolPrueba.dispararEliminarNodo(nodoPrueba1);
        arbolPrueba.dispararEliminarNodo(nodoPrueba7);
        
        inorder = arbolPrueba.dispararInorden();
        for(NodoBinario<Integer> nodo : inorder) {
            System.out.println(nodo.getDato());
        }
    }
}
