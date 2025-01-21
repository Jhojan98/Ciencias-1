/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArbolBinarioEntrega;

import java.util.ArrayList;

/**
 * @author Estudiantes
 */
public class ArbolBinario<T extends Comparable> {
    NodoBinario<T> raiz = null;

    public int altura() {
        if (raiz != null) {
            return raiz.altura();
        }
        return 0;
    }

    // Método para obtener la raíz del árbol
    public NodoBinario<T> getRaiz() {
        return raiz;
    }

    // Método para verificar si el árbol está vacío
    public boolean esVacio() {
        return raiz == null;
    }
    public int dispararContarNodos() {
        return contarNodos(this.raiz);
    }

    public int contarNodos(NodoBinario<T> nodoActual) {
        if (nodoActual == null) {
            return 0;
        }
        return 1 + contarNodos(nodoActual.getHijoIzquierdo()) + contarNodos(nodoActual.getHijoDerecho());
    }


    public boolean dispararArbolEsLleno() {
        return arbolEsLleno(raiz);
    }

    public boolean arbolEsLleno(NodoBinario<T> nodoActual) {
        if (nodoActual == null) {
            return true;
        } else if (nodoActual.getHijoIzquierdo() == null && nodoActual.getHijoDerecho() == null) {
            return true;
        } else if (nodoActual.getHijoIzquierdo() != null && nodoActual.getHijoDerecho() != null) {
            return arbolEsLleno(nodoActual.getHijoIzquierdo()) && arbolEsLleno(nodoActual.getHijoDerecho());
        } else {
            return false;
        }
    }
    

    public boolean dispararArbolEsCompleto() {
        return arbolEsCompleto(raiz, 0);
    }

    public boolean arbolEsCompleto(NodoBinario<T> nodoActual, int indice) {
        if (nodoActual == null) {
            return true;
        } else if (indice >= dispararContarNodos()) {
            return false;
        } else {
            return arbolEsCompleto(nodoActual.getHijoIzquierdo(), 2 * indice + 1)
                    && arbolEsCompleto(nodoActual.getHijoDerecho(), 2 * indice + 2);
        }
    }


    public boolean dispararArbolEsPerfecto() {
        return arbolEsPerfecto(raiz, 0);
    }

    public boolean arbolEsPerfecto(NodoBinario<T> nodoActual, int nivelActual) {
        if (nodoActual == null) {
            return true;
        } else if (nodoActual.getHijoIzquierdo() == null && nodoActual.getHijoDerecho() == null) {
            return nivelActual + 1 == altura();
        } else if (nodoActual.getHijoIzquierdo() == null || nodoActual.getHijoDerecho() == null) {
            return false;
        } else {
            return arbolEsPerfecto(nodoActual.getHijoIzquierdo(), nivelActual + 1) &&
                    arbolEsPerfecto(nodoActual.getHijoDerecho(), nivelActual + 1);
        }
    }


    public void dispararAgregarNodo(NodoBinario<T> nodoAgregar) {
        if (this.raiz == null) {
            this.raiz = nodoAgregar;
        } else {
            this.agregarNodo(raiz, nodoAgregar);
        }
    }

    public void agregarNodo(NodoBinario<T> nodoActual, NodoBinario<T> nodoAgregar) {
        if (nodoAgregar.getDato().compareTo(nodoActual.getDato()) < 0) {
            if (nodoActual.getHijoIzquierdo() == null) {
                nodoActual.setHijoIzquierdo(nodoAgregar);
                nodoAgregar.setPadre(nodoActual);
            } else {
                nodoActual.getHijoIzquierdo().agregarNodo(nodoActual.getHijoIzquierdo(), nodoAgregar);
            }
        } else {
            if (nodoActual.getHijoDerecho() == null) {
                nodoActual.setHijoDerecho(nodoAgregar);
                nodoAgregar.setPadre(nodoActual);
            } else {
                nodoActual.getHijoDerecho().agregarNodo(nodoActual.getHijoDerecho(), nodoAgregar);
            }
        }
    }


    public ArrayList<NodoBinario<T>> dispararInorden() {
        ArrayList<NodoBinario<T>> arbolInorden = new ArrayList<NodoBinario<T>>();
        inorden(this.raiz, arbolInorden);
        return arbolInorden;
    }

    public void inorden(NodoBinario<T> nodoActual, ArrayList<NodoBinario<T>> ordenActual) {
        if (nodoActual == null) {
            return;
        } else {
            inorden(nodoActual.getHijoIzquierdo(), ordenActual);
            ordenActual.add(nodoActual);
            inorden(nodoActual.getHijoDerecho(), ordenActual);
        }
    }


    public void dispararEliminarNodo(NodoBinario<T> nodoEliminar) {
        if (this.raiz == null) {
            // Árbol vacío, nada que eliminar
            return;
        }
        eliminarNodo(nodoEliminar);
    }

    private void eliminarNodoSinHijos(NodoBinario<T> nodo) {
        if (nodo == raiz) {
            raiz = null;
        } else {
            NodoBinario<T> padre = nodo.getPadre();
            if (padre.getHijoIzquierdo() == nodo) {
                padre.setHijoIzquierdo(null);
            } else {
                padre.setHijoDerecho(null);
            }
        }
    }

    private void eliminarNodoConUnHijo(NodoBinario<T> nodo, NodoBinario<T> hijo) {
        if (nodo == raiz) {
            raiz = hijo;
            hijo.setPadre(null);
        } else {
            NodoBinario<T> padre = nodo.getPadre();
            if (padre.getHijoIzquierdo() == nodo) {
                padre.setHijoIzquierdo(hijo);
            } else {
                padre.setHijoDerecho(hijo);
            }
            hijo.setPadre(padre);
        }
    }

    private NodoBinario<T> encontrarMinimo(NodoBinario<T> nodo) {
        while (nodo.getHijoIzquierdo() != null) {
            nodo = nodo.getHijoIzquierdo();
        }
        return nodo;
    }

    private void eliminarNodo(NodoBinario<T> nodoEliminar) {
        if (nodoEliminar == null) {
            return;
        }
        // Caso 1: Nodo sin hijos (hoja)
        if (nodoEliminar.getHijoIzquierdo() == null && nodoEliminar.getHijoDerecho() == null) {
            eliminarNodoSinHijos(nodoEliminar);
            // Caso 2: Nodo con un solo hijo (izquierdo)
        } else if (nodoEliminar.getHijoIzquierdo() != null && nodoEliminar.getHijoDerecho() == null) {
            eliminarNodoConUnHijo(nodoEliminar, nodoEliminar.getHijoIzquierdo());
            // Caso 2: Nodo con un solo hijo (derecho)
        } else if (nodoEliminar.getHijoIzquierdo() == null && nodoEliminar.getHijoDerecho() != null) {
            eliminarNodoConUnHijo(nodoEliminar, nodoEliminar.getHijoDerecho());
            // Caso 3: Nodo con dos hijos
        } else {
            NodoBinario<T> sucesor = encontrarMinimo(nodoEliminar.getHijoDerecho());
            nodoEliminar.setDato(sucesor.getDato());
            eliminarNodo(sucesor);
        }
    }


    public NodoBinario<T> dispararObtenerNodo(T datoBuscable) {
        if (this.raiz == null) {
            return null; //Arbol vacío, nada que buscar
        }
        return obtenerNodo(raiz, datoBuscable);
    }

    public NodoBinario<T> obtenerNodo(NodoBinario<T> nodoActual, T datoBuscable) {
        if (datoBuscable.compareTo(nodoActual.getDato()) < 0 && nodoActual.getHijoIzquierdo() != null) {
            return obtenerNodo(nodoActual.getHijoIzquierdo(), datoBuscable);
        } else if (datoBuscable.compareTo(nodoActual.getDato()) > 0 && nodoActual.getHijoDerecho() != null) {
            return obtenerNodo(nodoActual.getHijoDerecho(), datoBuscable);
        } else if (datoBuscable.compareTo(nodoActual.getDato()) == 0) {
            return nodoActual;
        } else {
            return null; //Nodo no encontrado
        }
    }


    public int dispararObtenerNivel(NodoBinario<T> nodoBuscable) {
        return obtenerNivel(this.raiz, nodoBuscable, 0);
    }

    public int obtenerNivel(NodoBinario<T> nodoActual, NodoBinario<T> nodoObjetivo, int nivelActual) {
        if (nodoActual == null) {
            return -1;  // El nodo no existe en el árbol
        }
        if (nodoActual == nodoObjetivo) {
            return nivelActual;
        }
        int nivelIzquierdo = obtenerNivel(nodoActual.getHijoIzquierdo(), nodoObjetivo, nivelActual + 1);
        int nivelDerecho = obtenerNivel(nodoActual.getHijoDerecho(), nodoObjetivo, nivelActual + 1);
        if (nivelIzquierdo != -1) {
            return nivelIzquierdo;
        } else {
            return nivelDerecho;
        }
    }
    
    
    public NodoBinario<T> buscar(T valor){
        return buscar(raiz,valor);
        
    }
    private NodoBinario<T> buscar(NodoBinario<T> nodo, T valor) {
    if (nodo == null || nodo.getDato().equals(valor)) {
        return nodo;
    }
    if (valor.compareTo(nodo.getDato()) < 0) {
        return buscar(nodo.getHijoIzquierdo(), valor);
    } else {
        return buscar(nodo.getHijoDerecho(), valor);
    }
}
    
    public void imprimirInOrder() {
    imprimirInOrder(this.raiz);
    System.out.println();
    
}

    private void imprimirInOrder(NodoBinario nodo) {
        if (nodo != null) {
            imprimirInOrder(nodo.getHijoIzquierdo());
            System.out.print(nodo.getDato() + " ");
            imprimirInOrder(nodo.getHijoDerecho());
        }
    }
    
    
    

    public NodoBinario<T> rotacionIzquierda(NodoBinario<T> nodo) {
        // Verificar que el nodo tenga un hijo derecho para rotar
        if (nodo.getHijoDerecho() == null) {
            System.out.println("Rotación a la izquierda no es posible: el nodo no tiene hijo derecho.");
            return nodo; // Retorna el mismo nodo sin cambios
        }

        NodoBinario<T> nuevoPadre = nodo.getHijoDerecho();
        nodo.setHijoDerecho(nuevoPadre.getHijoIzquierdo());
        if (nuevoPadre.getHijoIzquierdo() != null) {
            nuevoPadre.getHijoIzquierdo().setPadre(nodo);
        }
        nuevoPadre.setPadre(nodo.getPadre());

        if (nodo.getPadre() == null) {
            raiz = nuevoPadre;
        } else if (nodo == nodo.getPadre().getHijoIzquierdo()) {
            nodo.getPadre().setHijoIzquierdo(nuevoPadre);
        } else {
            nodo.getPadre().setHijoDerecho(nuevoPadre);
        }

        nuevoPadre.setHijoIzquierdo(nodo);
        nodo.setPadre(nuevoPadre);
        return nuevoPadre;
    }


    public NodoBinario<T> rotacionDerecha(NodoBinario<T> nodo) {
        // Verificar que el nodo tenga un hijo izquierdo para rotar
        if (nodo.getHijoIzquierdo() == null) {
            System.out.println("Rotación a la derecha no es posible: el nodo no tiene hijo izquierdo.");
            return nodo; // Retorna el mismo nodo sin cambios
        }

        NodoBinario<T> nuevoPadre = nodo.getHijoIzquierdo();
        nodo.setHijoIzquierdo(nuevoPadre.getHijoDerecho());
        if (nuevoPadre.getHijoDerecho() != null) {
            nuevoPadre.getHijoDerecho().setPadre(nodo);
        }
        nuevoPadre.setPadre(nodo.getPadre());

        if (nodo.getPadre() == null) {
            raiz = nuevoPadre;
        } else if (nodo == nodo.getPadre().getHijoIzquierdo()) {
            nodo.getPadre().setHijoIzquierdo(nuevoPadre);
        } else {
            nodo.getPadre().setHijoDerecho(nuevoPadre);
        }

        nuevoPadre.setHijoDerecho(nodo);
        nodo.setPadre(nuevoPadre);
        return nuevoPadre;
    }
    
    


} 
