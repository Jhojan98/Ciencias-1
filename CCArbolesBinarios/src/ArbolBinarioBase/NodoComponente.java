/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ArbolBinarioBase;

/**
 *
 * @author Santiago
 */
public interface NodoComponente {
    public void agregarHijo(NodoComponente nodoAnadir);
    public void removerHijo(NodoComponente nodoRemover);
    public NodoComponente getHijoIzq();
    public NodoComponente getHijoDer();
    public void imprimirContenido();
}
