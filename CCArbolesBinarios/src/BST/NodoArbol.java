/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BST;

/**
 *
 * @author Santiago
 */
public class NodoArbol {
    private int valor;
    private NodoArbol nodoIzq;
    private NodoArbol nodoDer;
    
    public NodoArbol(int valor) {
        this.valor = valor;
        this.nodoIzq = null;
        this.nodoDer = null;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public NodoArbol getNodoIzq() {
        return nodoIzq;
    }

    public NodoArbol getNodoDer() {
        return nodoDer;
    }
    
    public void insertar (int valor) {
        if(valor < this.valor){
            //Insertar en lado izquierdo
            if(this.nodoIzq == null) {
                this.nodoIzq = new NodoArbol(valor);
            } else {
                this.nodoIzq.insertar(valor);
            }
        } else {
            //Insertar en lado derecho
            if(this.nodoDer == null) {
                this.nodoDer = new NodoArbol(valor);
            } else {
                this.nodoDer.insertar(valor);
            }
        }
    }
}
