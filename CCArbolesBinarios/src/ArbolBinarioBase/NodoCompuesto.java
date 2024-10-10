/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArbolBinarioBase;

/**
 *
 * @author Santiago
 */
public class NodoCompuesto implements NodoComponente {
    private NodoComponente hijoIzquierdo;
    private NodoComponente hijoDerecho;
    private Object contenido;
    
    public NodoCompuesto(Object contenido) {
        this.contenido = contenido;
    }

    @Override
    public void agregarHijo(NodoComponente nodoAnadir) {
        if(hijoIzquierdo == null) {
            hijoIzquierdo = nodoAnadir;
        } else if (hijoDerecho == null) {
            hijoDerecho = nodoAnadir;
        } else {
            System.out.println("--------------------------------------------------------------------");
            System.out.println("Nodo de contenido " + contenido.toString() + " con hijos ya asignados.");
            System.out.println("--------------------------------------------------------------------");
        }
    }

    @Override
    public void removerHijo(NodoComponente nodoRemover) {
        if(hijoIzquierdo == nodoRemover) {
            hijoIzquierdo = null;
        } else if (hijoDerecho == nodoRemover) {
            hijoDerecho = null;
        } else {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("Nodo no encontrado en los hijos de Nodo de contenido " + contenido.toString() + ".");
            System.out.println("--------------------------------------------------------------------------------");
        }
    }

    @Override
    public NodoComponente getHijoIzq() {
        return hijoIzquierdo;
    }

    @Override
    public NodoComponente getHijoDer() {
        return hijoDerecho;
    }

    @Override
    public void imprimirContenido() {
        System.out.println("Contenido Nodo Principal:\n" + contenido.toString());
        System.out.println("Contenido Hijo Izquierdo:");
        hijoIzquierdo.imprimirContenido();
        System.out.println("Contenido Hijo Derecho:");
        hijoDerecho.imprimirContenido();
    }
    
}
