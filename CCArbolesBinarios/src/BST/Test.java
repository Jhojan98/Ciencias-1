/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BST;

/**
 *
 * @author Santiago
 */

//BINARY SEARCH TREE
public class Test {
    public static void main(String[] args) {
        Arbol arbolTest = new Arbol();
        arbolTest.insertar(43);
        arbolTest.insertar(10);
        arbolTest.insertar(8);
        arbolTest.insertar(54);
        arbolTest.insertar(15);
        arbolTest.insertar(50);
        arbolTest.insertar(53);
        
        System.out.println("INORDEN: ");
        arbolTest.dispararInorden();
        System.out.println("\nPOSTORDEN: ");
        arbolTest.dispararPostorden();
        System.out.println("\nPREORDEN: ");
        arbolTest.dispararPreorden();
    }
}
