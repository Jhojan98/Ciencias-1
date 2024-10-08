/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArbolBinarioBase;

/**
 *
 * @author Santiago
 */
public class NodoHoja implements NodoComponente {
    private Object contenido;

    public NodoHoja(Object contenido) {
        this.contenido = contenido;
    }
    
    @Override
    public void agregarHijo(NodoComponente nodoAnadir) {}

    @Override
    public void removerHijo(NodoComponente nodoRemover) {}

    @Override
    public NodoComponente getHijoIzq() {
        return null;
    }

    @Override
    public NodoComponente getHijoDer() {
        return null;
    }

    @Override
    public void imprimirContenido() {
        System.out.println(contenido.toString());
    }
    
}
