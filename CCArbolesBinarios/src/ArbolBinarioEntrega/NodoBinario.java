/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArbolBinarioEntrega;

/**
 *
 * @author Estudiantes
 */
public class NodoBinario <T extends Comparable> extends ArbolBinario { 
//Lo comparable hace lo ordenable 
//Deberíamos guardar una referencia hacia el padre en los nodos 
    private T dato; 
    private NodoBinario<T> padre = null;
    private NodoBinario<T> hijoIzquierdo = null; 
    private NodoBinario<T> hijoDerecho = null; 
    private int nivel;

    public NodoBinario(T dato) {
        this.dato = dato;
    }
    
    public NodoBinario(NodoBinario<T> nodoBinario) {
        this.dato = nodoBinario.getDato();
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoBinario<T> getPadre() {
        return padre;
    }

    public void setPadre(NodoBinario<T> padre) {
        this.padre = padre;
    }
    

    public NodoBinario<T> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoBinario<T> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodoBinario<T> getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(NodoBinario<T> hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

@Override  
public int altura() { 
    int alturaSubIzquierdo = getHijoIzquierdo() != null ? getHijoIzquierdo().altura() : 0; 
    int alturaSubDerecho = getHijoDerecho() != null ? getHijoDerecho().altura() : 0; 
    return Math.max(alturaSubIzquierdo, alturaSubDerecho) + 1; 

} 

} 