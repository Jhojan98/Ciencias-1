package avl;

import javax.swing.JTextArea;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class ArbolAVL {

    private Nodo raiz;

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
        if (balance > 1 && dato < nodo.izquierda.dato) {
            return rotarDerecha(nodo);
        }

        if (balance < -1 && dato > nodo.derecha.dato) {
            return rotarIzquierda(nodo);
        }

        if (balance > 1 && dato > nodo.izquierda.dato) {
            nodo.izquierda = rotarIzquierda(nodo.izquierda);
            return rotarDerecha(nodo);
        }

        if (balance < -1 && dato < nodo.derecha.dato) {
            nodo.derecha = rotarDerecha(nodo.derecha);
            return rotarIzquierda(nodo);
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

        if (balance > 1 && balance(nodo.izquierda) >= 0) {
            return rotarDerecha(nodo);
        }

        if (balance > 1 && balance(nodo.izquierda) < 0) {
            nodo.izquierda = rotarIzquierda(nodo.izquierda);
            return rotarDerecha(nodo);
        }

        if (balance < -1 && balance(nodo.derecha) <= 0) {
            return rotarIzquierda(nodo);
        }

        if (balance < -1 && balance(nodo.derecha) > 0) {
            nodo.derecha = rotarDerecha(nodo.derecha);
            return rotarIzquierda(nodo);
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

    // Mostrar el árbol en formato gráfico
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
            for (int i = 0; i < nivel; i++) {
                outputArea.append("    ");
            }
            outputArea.append(nodo.dato + "\n");
            mostrar(nodo.izquierda, nivel + 1, outputArea);
        }
    }

    public void crearGrafo(Graph<Integer, DefaultEdge> graph) {
        crearGrafoRecursivo(raiz, graph);
    }

    private void crearGrafoRecursivo(Nodo nodo, Graph<Integer, DefaultEdge> graph) {
        if (nodo != null) {
            graph.addVertex(nodo.dato);
            if (nodo.izquierda != null) {
                graph.addVertex(nodo.izquierda.dato);
                graph.addEdge(nodo.dato, nodo.izquierda.dato);
                crearGrafoRecursivo(nodo.izquierda, graph);
            }
            if (nodo.derecha != null) {
                graph.addVertex(nodo.derecha.dato);
                graph.addEdge(nodo.dato, nodo.derecha.dato);
                crearGrafoRecursivo(nodo.derecha, graph);
            }
        }
    }

}
