package interfaz;

import avl.ArbolAVL;
//import avl.Nodo; // Import the Nodo class
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultDirectedGraph;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz extends JFrame {
    private ArbolAVL arbol;
    private JTextField inputField;
    private JTextArea outputArea;
    private JPanel graphPanel;

    public Interfaz() {
        arbol = new ArbolAVL();
        setTitle("Interfaz de Árbol AVL");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        inputField = new JTextField();
        panel.add(inputField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        JButton insertButton = new JButton("Insertar");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int dato = Integer.parseInt(inputField.getText());
                    arbol.insertar(dato);
                    mostrarArbol();
                    graficarArbol();
                } catch (NumberFormatException ex) {
                    outputArea.setText("Entrada no válida. Por favor, ingrese un número entero.\n");
                }
            }
        });
        buttonPanel.add(insertButton);

        JButton deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int dato = Integer.parseInt(inputField.getText());
                    arbol.eliminar(dato);
                    mostrarArbol();
                    graficarArbol();
                } catch (NumberFormatException ex) {
                    outputArea.setText("Entrada no válida. Por favor, ingrese un número entero.\n");
                } catch (IllegalArgumentException ex) {
                    outputArea.setText("Nodo no encontrado.\n");
                }
            }
        });
        buttonPanel.add(deleteButton);

        JButton showButton = new JButton("Mostrar");
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarArbol();
                graficarArbol();
            }
        });
        buttonPanel.add(showButton);

        JButton searchButton = new JButton("Buscar");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int dato = Integer.parseInt(inputField.getText());
                    buscarNodo(dato);
                } catch (NumberFormatException ex) {
                    outputArea.setText("Entrada no válida. Por favor, ingrese un número entero.\n");
                }
            }
        });
        buttonPanel.add(searchButton);

        panel.add(buttonPanel);
        add(panel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        graphPanel = new JPanel();
        add(graphPanel, BorderLayout.SOUTH);
    }

    private void mostrarArbol() {
        outputArea.setText("");
        arbol.mostrarArbol(outputArea);
    }

    private void buscarNodo(int dato) {
        outputArea.setText("");
        arbol.buscarNodo(dato, outputArea);
    }

    private void graficarArbol() {
        graphPanel.removeAll();

        // Crear el grafo con JGraphT
        Graph<Integer, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        arbol.crearGrafo(graph);

        // Convertir a mxGraph
        mxGraph mxGraph = new mxGraph();
        Object parent = mxGraph.getDefaultParent();

        // Mapa para guardar los vértices creados (Integer -> mxCell)
        java.util.Map<Integer, Object> vertexMap = new java.util.HashMap<>();

        mxGraph.getModel().beginUpdate();
        try {
            // Añadir vértices
            for (Integer vertex : graph.vertexSet()) {
                Object mxVertex = mxGraph.insertVertex(parent, null, vertex, 0, 0, 80, 30);
                vertexMap.put(vertex, mxVertex); // Guardar referencia
            }

            // Añadir aristas usando los vértices de mxGraph
            for (DefaultEdge edge : graph.edgeSet()) {
                Integer source = graph.getEdgeSource(edge);
                Integer target = graph.getEdgeTarget(edge);
                mxGraph.insertEdge(parent, null, "", vertexMap.get(source), vertexMap.get(target));
            }
        } finally {
            mxGraph.getModel().endUpdate();
        }

        // Configurar el layout
        mxGraphComponent graphComponent = new mxGraphComponent(mxGraph);
        graphPanel.setLayout(new BorderLayout());
        graphPanel.add(graphComponent, BorderLayout.CENTER);
        graphPanel.revalidate();
        graphPanel.repaint();
    }
}
