package avl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class ArbolAVL {

    private Nodo raiz;
    public List<String> historial = new ArrayList<>();

    // Obtener altura de un nodo
    private int altura(Nodo n) {
        return n == null ? 0 : n.altura;
    }

    // Obtener el factor de balanceo
    private int balance(Nodo n) {
        return n == null ? 0 : altura(n.izquierda) - altura(n.derecha);
    }

    // Rotación simple a la derecha
    private Nodo rotarDerecha(Nodo y) {
        if (y == null || y.izquierda == null) { // ← Validar null
            return y; // No se puede rotar
        }
        historial.add("Rotación derecha en nodo: " + y.dato);
        Nodo x = y.izquierda;
        Nodo T2 = x.derecha;

        x.derecha = y;
        y.izquierda = T2;

        y.altura = Math.max(altura(y.izquierda), altura(y.derecha)) + 1;
        x.altura = Math.max(altura(x.izquierda), altura(x.derecha)) + 1;

        return x;
    }

    // Rotación simple a la izquierda
    private Nodo rotarIzquierda(Nodo x) {
        if (x == null || x.derecha == null) { // ← Validar null
            return x; // No se puede rotar
        }
        historial.add("Rotación izquierda en nodo: " + x.dato);
        Nodo y = x.derecha;
        Nodo T2 = y.izquierda;

        y.izquierda = x;
        x.derecha = T2;

        x.altura = Math.max(altura(x.izquierda), altura(x.derecha)) + 1;
        y.altura = Math.max(altura(y.izquierda), altura(y.derecha)) + 1;

        return y;
    }

    // Insertar un nodo (permitir duplicados a la derecha)
    public void insertar(int dato) {
        raiz = insertarNodo(raiz, dato);
    }

    private Nodo insertarNodo(Nodo nodo, int dato) {
        if (nodo == null) {
            return new Nodo(dato);
        }

        if (dato < nodo.dato) {
            nodo.izquierda = insertarNodo(nodo.izquierda, dato);
        } else {
            nodo.derecha = insertarNodo(nodo.derecha, dato);
        }

        nodo.altura = 1 + Math.max(altura(nodo.izquierda), altura(nodo.derecha));

        int balance = balance(nodo);

        // Rotaciones para balancear el árbol
        if (balance > 1) {
            if (nodo.izquierda != null && dato < nodo.izquierda.dato) { // Left-Left
                return rotarDerecha(nodo);
            } else { // Left-Right
                nodo.izquierda = rotarIzquierda(nodo.izquierda);
                return rotarDerecha(nodo);
            }
        }
        if (balance < -1) {
            if (nodo.derecha != null && dato > nodo.derecha.dato) { // Right-Right
                return rotarIzquierda(nodo);
            } else { // Right-Left
                nodo.derecha = rotarDerecha(nodo.derecha);
                return rotarIzquierda(nodo);
            }
        }
        return nodo;
    }

    // Eliminar un nodo
    public void eliminar(int dato) {
        raiz = eliminarNodo(raiz, dato);
    }

    private Nodo eliminarNodo(Nodo nodo, int dato) {
        if (nodo == null) {
            throw new IllegalArgumentException("Nodo no encontrado.");
        }

        if (dato < nodo.dato) {
            nodo.izquierda = eliminarNodo(nodo.izquierda, dato);
        } else if (dato > nodo.dato) {
            nodo.derecha = eliminarNodo(nodo.derecha, dato);
        } else {
            if ((nodo.izquierda == null) || (nodo.derecha == null)) {
                Nodo temp = (nodo.izquierda != null) ? nodo.izquierda : nodo.derecha;
                if (temp == null) {
                    temp = nodo;
                    nodo = null;
                } else {
                    nodo = temp;
                }
            } else {
                Nodo temp = nodoConValorMinimo(nodo.derecha);
                nodo.dato = temp.dato;
                nodo.derecha = eliminarNodo(nodo.derecha, temp.dato);
            }
        }

        if (nodo == null) {
            return nodo;
        }

        nodo.altura = 1 + Math.max(altura(nodo.izquierda), altura(nodo.derecha));

        int balance = balance(nodo);

        if (balance > 1) {
            if (nodo.izquierda != null && balance(nodo.izquierda) >= 0) { // Left-Left
                return rotarDerecha(nodo);
            } else { // Left-Right
                nodo.izquierda = rotarIzquierda(nodo.izquierda);
                return rotarDerecha(nodo);
            }
        }
        if (nodo.derecha != null && balance < -1) {
            if (balance(nodo.derecha) <= 0) { // Right-Right
                return rotarIzquierda(nodo);
            } else { // Right-Left
                nodo.derecha = rotarDerecha(nodo.derecha);
                return rotarIzquierda(nodo);
            }
        }

        return nodo;
    }

    private Nodo nodoConValorMinimo(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.izquierda != null) {
            actual = actual.izquierda;
        }
        return actual;
    }

    // Buscar un nodo y mostrar información de duplicados
    public void buscarNodo(int dato, JTextArea outputArea) {
        if (!buscarDuplicados(raiz, dato, outputArea)) {
            outputArea.append("Nodo no encontrado.\n");
        }
    }

    private boolean buscarDuplicados(Nodo nodo, int dato, JTextArea outputArea) {
        if (nodo == null) {
            return false;
        }

        boolean encontradoIzquierda = buscarDuplicados(nodo.izquierda, dato, outputArea);

        if (nodo.dato == dato) {
            outputArea.append("Dato: " + nodo.dato + "\n");
            outputArea.append("Altura: " + nodo.altura + "\n");
            outputArea.append("Factor de balanceo: " + balance(nodo) + "\n");
            return true;
        }

        boolean encontradoDerecha = buscarDuplicados(nodo.derecha, dato, outputArea);

        return encontradoIzquierda || encontradoDerecha;
    }

    public void mostrarArbol(JTextArea outputArea) {
        if (raiz == null) {
            outputArea.append("Árbol vacío.\n");
            return;
        }
        mostrar(raiz, 0, outputArea);
    }

    private void mostrar(Nodo nodo, int nivel, JTextArea outputArea) {
        if (nodo != null) {
            mostrar(nodo.derecha, nivel + 1, outputArea);

            // Add indentation
            for (int i = 0; i < nivel; i++) {
                outputArea.append("    ");
            }
            // Print the node value
            outputArea.append(nodo.dato + "\n");

            mostrar(nodo.izquierda, nivel + 1, outputArea);
        }
    }

    /*
     * // esto es para mostrar de arriba a abajo, pero no se puede ver el arbol
     * public void mostrarArbol(JTextArea outputArea) {
     * if (raiz == null) {
     * outputArea.append("Árbol vacío.\n");
     * return;
     * }
     * mostrarNiveles(outputArea);
     * }
     * 
     * private void mostrarNiveles(JTextArea outputArea) {
     * java.util.Queue<Nodo> cola = new java.util.LinkedList<>();
     * cola.add(raiz);
     * 
     * while (!cola.isEmpty()) {
     * int nivelSize = cola.size();
     * StringBuilder nivelStr = new StringBuilder();
     * 
     * for (int i = 0; i < nivelSize; i++) {
     * Nodo actual = cola.poll();
     * nivelStr.append(actual.dato).append(" ");
     * 
     * if (actual.izquierda != null)
     * cola.add(actual.izquierda);
     * if (actual.derecha != null)
     * cola.add(actual.derecha);
     * }
     * outputArea.append(nivelStr.toString() + "\n");
     * }
     * }
     */
    public void crearGrafo(Graph<String, DefaultEdge> graph) {
        agregarVertices(raiz, graph);
        agregarAristas(raiz, graph);
    }

    private void agregarVertices(Nodo nodo, Graph<String, DefaultEdge> graph) {
        if (nodo != null) {
            String idUnico = nodo.dato + "_" + nodo.hashCode(); // ID único
            graph.addVertex(idUnico);
            agregarVertices(nodo.izquierda, graph);
            agregarVertices(nodo.derecha, graph);
        }
    }

    private void agregarAristas(Nodo nodo, Graph<String, DefaultEdge> graph) {
        if (nodo != null) {
            String idPadre = nodo.dato + "_" + nodo.hashCode();
            if (nodo.izquierda != null) {
                String idIzq = nodo.izquierda.dato + "_" + nodo.izquierda.hashCode();
                graph.addEdge(idPadre, idIzq);
            }
            if (nodo.derecha != null) {
                String idDer = nodo.derecha.dato + "_" + nodo.derecha.hashCode();
                graph.addEdge(idPadre, idDer);
            }
            agregarAristas(nodo.izquierda, graph);
            agregarAristas(nodo.derecha, graph);
        }
    }

}
