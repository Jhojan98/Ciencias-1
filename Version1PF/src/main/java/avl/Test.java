package avl;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArbolAVL arbol = new ArbolAVL();

        while (true) {
            System.out.println("Seleccione una opción: 1. Insertar 2. Eliminar 3. Mostrar Árbol 4. Buscar Nodo 5. Salir");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese un número para insertar: ");
                    int datoInsertar = scanner.nextInt();
                    arbol.insertar(datoInsertar);
                    // arbol.mostrarArbol(); // Comentado porque requiere JTextArea
                    break;
                case 2:
                    System.out.print("Ingrese un número para eliminar: ");
                    int datoEliminar = scanner.nextInt();
                    arbol.eliminar(datoEliminar);
                    // arbol.mostrarArbol(); // Comentado porque requiere JTextArea
                    break;
                case 3:
                    // arbol.mostrarArbol(); // Comentado porque requiere JTextArea
                    break;
                case 4:
                    System.out.print("Ingrese un número para buscar: ");
                    int datoBuscar = scanner.nextInt();
                    // arbol.buscarNodo(datoBuscar); // Comentado porque requiere JTextArea
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
