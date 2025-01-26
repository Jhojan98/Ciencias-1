package interfaz;

import avl.ArbolAVL;
//import avl.Nodo; // Import the Nodo class
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultDirectedGraph;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Interfaz extends JFrame {
    private ArbolAVL arbol;
    private JTextField inputField;
    private JTextArea outputArea;
    private JTextArea historyArea;
    private JPanel graphPanel;

    public Interfaz() {
        // Inicializar los componentes primero
        arbol = new ArbolAVL();
        inputField = new JTextField();
        outputArea = new JTextArea();
        graphPanel = new JPanel();

        // Configurar la ventana
        setTitle("Árbol AVL Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Tamaño inicial de la ventana
        setLocationRelativeTo(null); // Centrar la ventana

        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        // Panel de controles mejorado
        JPanel controlPanel = new JPanel(new BorderLayout(5, 5));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Campo de entrada
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputField.setPreferredSize(new Dimension(200, 30));

        JTabbedPane tabbedPane = new JTabbedPane();

        // Nueva pestaña de historial
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        JScrollPane historyScroll = new JScrollPane(historyArea);
        tabbedPane.addTab("Historial", historyScroll);

        // Panel de botones con íconos
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        // Create and add buttons here
        JButton insertButton = crearBoton("Insertar", new Color(46, 204, 113));
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int dato = Integer.parseInt(inputField.getText());
                    arbol.insertar(dato);

                    actualizarHistorial("Inserción", dato);
                    arbol.historial.forEach(r -> historyArea.append("  ➔ " + r + "\n"));
                    arbol.historial.clear();

                    mostrarArbol();
                    graficarArbol();
                } catch (NumberFormatException ex) {
                    outputArea.setText("Entrada no válida. Por favor, ingrese un número entero.\n");
                }
            }
        });
        buttonPanel.add(insertButton);

        JButton deleteButton = crearBoton("Eliminar", new Color(231, 76, 60));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int dato = Integer.parseInt(inputField.getText());
                    arbol.eliminar(dato);
                    actualizarHistorial("Eliminación", dato);
                    arbol.historial.forEach(r -> historyArea.append("  ➔ " + r + "\n"));
                    arbol.historial.clear();
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

        JButton showButton = crearBoton("Mostrar", new Color(52, 152, 219));
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarArbol();
                graficarArbol();
            }
        });
        buttonPanel.add(showButton);

        JButton searchButton = crearBoton("Buscar", new Color(155, 89, 182));
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

        controlPanel.add(inputField, BorderLayout.NORTH);
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Panel de texto
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        tabbedPane.addTab("Información", new JScrollPane(outputArea));

        // Panel del gráfico
        graphPanel.setBackground(Color.WHITE);
        tabbedPane.addTab("Vista Gráfica", graphPanel);

        add(controlPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void actualizarHistorial(String operacion, int dato) {
        String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        historyArea.append("[" + timestamp + "] " + operacion + ": " + dato + "\n");
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

        Graph<String, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        arbol.crearGrafo(graph);

        mxGraph mxGraph = new mxGraph();
        Object parent = mxGraph.getDefaultParent();
        java.util.Map<String, Object> vertexMap = new java.util.HashMap<>();

        mxGraph.getModel().beginUpdate();
        try {
            // Añadir vértices
            for (String vertexId : graph.vertexSet()) {
                String dato = vertexId.split("_")[0];
                Object mxVertex = mxGraph.insertVertex(parent, null, dato, 0, 0, 40, 40);
                vertexMap.put(vertexId, mxVertex);
            }

            // Añadir aristas
            for (DefaultEdge edge : graph.edgeSet()) {
                String sourceId = graph.getEdgeSource(edge);
                String targetId = graph.getEdgeTarget(edge);
                mxGraph.insertEdge(parent, null, "", vertexMap.get(sourceId), vertexMap.get(targetId));
            }

            // Orientación vertical (raíz en la parte superior)
            mxHierarchicalLayout layout = new mxHierarchicalLayout(mxGraph, SwingConstants.NORTH);
            layout.setIntraCellSpacing(50); // Espacio horizontal
            layout.setInterRankCellSpacing(100); // Espacio vertical
            layout.execute(parent);

        } finally {
            mxGraph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(mxGraph);
        graphComponent.getViewport().setBackground(Color.WHITE);
        graphPanel.setLayout(new BorderLayout());
        graphPanel.add(graphComponent, BorderLayout.CENTER);
        graphPanel.revalidate();
        graphPanel.repaint();
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setPreferredSize(new Dimension(100, 30));
        return boton;
    }
}
