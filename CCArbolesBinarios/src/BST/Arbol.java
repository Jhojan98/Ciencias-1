/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BST;

/**
 *
 * @author Santiago
 */
public class Arbol {
    NodoArbol nodoIni;

    public Arbol() {
        this.nodoIni = null;
    }
    
    public void insertar(int valor) {
        if(this.nodoIni == null) {
            nodoIni = new NodoArbol(valor);
        } else {
            this.nodoIni.insertar(valor);
        }
    }
    
    public void dispararPreorden(){
       this.preorden(this.nodoIni);
    }
    
    public void preorden(NodoArbol nodo){
        if(nodo == null) {
            return; //Detener recursividad caso base
        } else {
            System.out.print(nodo.getValor() + ",");
            preorden(nodo.getNodoIzq());
            preorden(nodo.getNodoDer());
        }
    }
    
    public void dispararInorden() {
        this.inorden(this.nodoIni);
    }
    
    public void inorden(NodoArbol nodo) {
        if(nodo == null) {
            return; //Detener recursividad caso base
        } else {
            inorden(nodo.getNodoIzq());
            System.out.print(nodo.getValor() + ",");
            inorden(nodo.getNodoDer());
        }
    }
    
    public void dispararPostorden() {
        this.postorden(this.nodoIni);
    }
    
    public void postorden(NodoArbol nodo) {
        if(nodo == null) {
            return; //Detener recurisivdad caso base
        } else {
            postorden(nodo.getNodoIzq());
            postorden(nodo.getNodoDer());
            System.out.print(nodo.getValor() + ",");
        }
    }
}
