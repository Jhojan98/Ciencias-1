package interfaz;

import avl.ArbolAVL;
import avl.Nodo;

//import avl.Nodo; // Import the Nodo class
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultDirectedGraph;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Interfaz extends JFrame {
    private ArbolAVL arbol;
    private JTextField inputField;
    private JTextArea outputArea;
    private JTextArea historyArea;
    private JPanel graphPanel;
    private String lastOperation;
    private int lastDato;
    private java.util.Map<String, Point> previousPositions = new java.util.HashMap<>();

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
                    lastOperation = "insert";
                    int dato = Integer.parseInt(inputField.getText());
                    arbol.insertar(dato);

                    actualizarHistorial("Inserción", dato);
                    arbol.historial.forEach(r -> historyArea.append("  ➔ " + r + "\n"));
                    arbol.historial.clear();

                    mostrarArbol();
                    graficarArbol();
                    lastDato = dato;
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
                    lastOperation = "delete";
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
                Object mxVertex = mxGraph.insertVertex(
                        parent,
                        null,
                        dato,
                        0,
                        0,
                        40,
                        40,
                        "shape=ellipse;fillColor=#FFFFFF;strokeColor=#000000;");
                vertexMap.put(vertexId, mxVertex);
            }

            // Añadir aristas
            for (DefaultEdge edge : graph.edgeSet()) {
                String sourceId = graph.getEdgeSource(edge);
                String targetId = graph.getEdgeTarget(edge);
                mxGraph.insertEdge(parent, null, "", vertexMap.get(sourceId), vertexMap.get(targetId));
            }

            // Animación para rotaciones
            List<String> rotationNodes = new ArrayList<>();
            for (String entry : arbol.historial) {
                if (entry.startsWith("Nodos involucrados:")) {
                    String[] nodes = entry.split(":")[1].split(",");
                    for (String node : nodes) {
                        rotationNodes.add(node.trim());
                    }
                }
            }

            // Aplicar layout primero para obtener posiciones finales
            mxHierarchicalLayout layout = new mxHierarchicalLayout(mxGraph, SwingConstants.NORTH);
            layout.setIntraCellSpacing(50); // Espacio horizontal
            layout.setInterRankCellSpacing(100); // Espacio vertical
            layout.execute(parent);

            // Animación de movimiento
            for (String vertexId : graph.vertexSet()) {
                Object cell = vertexMap.get(vertexId);
                mxGeometry geometry = mxGraph.getModel().getGeometry(cell);
                Point newPos = new Point((int) geometry.getX(), (int) geometry.getY());

                if (previousPositions.containsKey(vertexId)) {
                    Point oldPos = previousPositions.get(vertexId);
                    animateMovement(mxGraph, cell, oldPos, newPos);
                }

                // Almacenar posición actual para la próxima vez
                previousPositions.put(vertexId, newPos);
            }

            // Resaltar nodos de rotación

            for (String vertexId : vertexMap.keySet()) {
                String dato = vertexId.split("_")[0];
                if (rotationNodes.contains(dato)) {
                    Object cell = vertexMap.get(vertexId);
                    mxGraph.setCellStyle("fillColor=#FFA500;strokeColor=#000000;", new Object[] { cell });
                }
            }

            lastOperation = ""; // Resetear la última operación

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

    private void animateMovement(mxGraph mxGraph, Object cell, Point from, Point to) {
        Timer timer = new Timer(50, new ActionListener() {
            private int steps = 10;
            private int currentStep = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStep >= steps) {
                    ((Timer) e.getSource()).stop();
                    return;
                }

                double ratio = (double) currentStep / steps;
                int x = (int) (from.x + (to.x - from.x) * ratio);
                int y = (int) (from.y + (to.y - from.y) * ratio);

                mxGraph.getModel().setGeometry(cell, new mxGeometry(x, y, 40, 40));
                currentStep++;
            }
        });
        timer.start();
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
