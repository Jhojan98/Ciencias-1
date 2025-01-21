import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class Test {

    private static ArbolBinario<Integer> arbol = new ArbolBinario<>();
    private static Graph graph;

    public static void main(String[] args) {
        // Configuración de la visualización en Swing
        System.setProperty("org.graphstream.ui", "swing");

        // Crear la interfaz gráfica
        JFrame frame = new JFrame("Árbol Binario con GraphStream");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Crear el panel de botones
        JPanel panelBotones = new JPanel();
        JButton botonInsertar = new JButton("Insertar Nodo");
        JButton botonEliminar = new JButton("Eliminar Nodo");
        JButton botonRecorrer = new JButton("Recorrer In-Orden");

        // Agregar botones al panel
        panelBotones.add(botonInsertar);
        panelBotones.add(botonEliminar);
        panelBotones.add(botonRecorrer);

        // Añadir el panel de botones a la ventana principal
        frame.add(panelBotones, BorderLayout.NORTH);

        // Crear el grafo de GraphStream
        graph = new SingleGraph("Árbol Binario");

        // Mostrar el grafo en un panel
        JPanel graphPanel = new JPanel(new BorderLayout());
        frame.add(graphPanel, BorderLayout.CENTER);
        graphPanel.add(graph.display().getDefaultView(), BorderLayout.CENTER);

        // Acción del botón "Insertar Nodo"
        botonInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Ingrese el valor del nodo:");
                try {
                    int valor = Integer.parseInt(input);
                    arbol.insertar(valor);
                    actualizarGrafo();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Valor no válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción del botón "Eliminar Nodo"
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Ingrese el valor del nodo a eliminar:");
                try {
                    int valor = Integer.parseInt(input);
                    arbol.eliminar(valor);
                    actualizarGrafo();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Valor no válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción del botón "Recorrer In-Orden"
        botonRecorrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Recorrido In-Orden: " + arbol.recorridoInOrden());
            }
        });

        // Mostrar la ventana
        frame.setVisible(true);
    }

    // Método para actualizar la visualización del grafo
    private static void actualizarGrafo() {
        graph.clear(); // Limpiar el grafo actual
        if (!arbol.esVacio()) {
            agregarNodoAlGrafo(graph, arbol.getRaiz(), null);
        }
    }

    private static void agregarNodoAlGrafo(Graph graph, NodoBinario<T> nodo, String nodoPadre) {
        if (nodo == null) return;

        // Crear el nodo en el grafo con el dato como etiqueta
        String nodoId = String.valueOf(nodo.getDato());
        graph.addNode(nodoId).setAttribute("ui.label", nodoId);

        // Crear la arista entre el nodo actual y su nodo padre
        if (nodoPadre != null) {
            graph.addEdge(nodoPadre + "-" + nodoId, nodoPadre, nodoId);
        }

        // Llamadas recursivas para los hijos izquierdo y derecho
        agregarNodoAlGrafo(graph, nodo.getHijoIzquierdo(), nodoId);
        agregarNodoAlGrafo(graph, nodo.getHijoDerecho(), nodoId);
    }
}
