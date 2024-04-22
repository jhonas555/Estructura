package logico;

import java.util.List;
import java.util.Scanner;


public class Main {
    private static Grafo grafo; // Grafo sobre el cual se realizan las operaciones
    private static AlgoritmosRuta algoritmosRuta; // Clase con los algoritmos de ruta

    public static void main(String[] args) {
        grafo = new Grafo();
        algoritmosRuta = new AlgoritmosRuta(grafo);
        Scanner scanner = new Scanner(System.in);

        boolean salir = false;
        while (!salir) {
            mostrarMenu(); // Muestra el menú de opciones
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    agregarUbicacion(scanner); // Llama al método para agregar una ubicación
                    break;
                case 2:
                	editarUbicacion(scanner); // Llama al método para editar una ubicación
                    break;
                case 3:
                	eliminarUbicacion(scanner); // Llama al método para eliminar una ubicación
                    break;
                case 4:
                	conectarUbicaciones(scanner); // Llama al método para conectar dos ubicaciones
                    break;
                case 5:
                	eliminarRuta(scanner); // Llama al método para eliminar una ruta (arista)
                    break;
                case 6:
                    editarRuta(scanner); // Llama al método para editar una ruta (arista)
                    break;
                case 7:
                    calcularRutaMasCorta(scanner); // Llama al método para calcular con Djikstra
                    break;
                case 8:
                    encontrarArbolExpansionMinimaPrim(); // Llama al método para calcular con Prim
                    break;
                case 9:
                    encontrarArbolExpansionMinimaKruskal(); // Llama al método para calcular con Kruskal
                    break;
                case 10:
                    calcularDistanciaMasCortaFloydWarshall(); // Llama al método para calcular con FloydWarshall
                    break;
                case 11:
                	planificarRuta(scanner); // Llama al método para planificar una ruta entre dos ubicaciones
                    break;
                case 12:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }

        System.out.println("¡Hasta luego!");
        scanner.close();
    }

 // Método para mostrar el menú de opciones
    private static void mostrarMenu() {
        System.out.println("------ Sistema de Gestión de Rutas ------");
        System.out.println("1. Agregar Ubicación");
        System.out.println("2. Editar Ubicación");
        System.out.println("3. Eliminar Ubicación");
        System.out.println("4. Conectar Ubicaciones");
        System.out.println("5. Eliminar Ruta");
        System.out.println("6. Editar Ruta");
        System.out.println("7. Calcular Ruta más Corta (Dijkstra)");
        System.out.println("8. Encontrar Árbol de Expansión Mínima (Prim)");
        System.out.println("9. Encontrar Árbol de Expansión Mínima (Kruskal)");
        System.out.println("10. Calcular Ruta más Corta (Floyd-Warshall)");
        System.out.println("11. Planificar Rutas");
        System.out.println("12. Salir");
        System.out.print("Ingrese una opción: ");
    }

 // Implementación para agregar una ubicación al grafo
    private static void agregarUbicacion(Scanner scanner) {
        System.out.print("Ingrese el ID de la nueva ubicación: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Ingrese el nombre de la nueva ubicación: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el peso de la nueva ubicación: ");
        double peso = scanner.nextDouble();
        scanner.nextLine(); 
        Ubicacion ubicacion = new Ubicacion(id, nombre, peso);
        grafo.agregarUbicacion(ubicacion);
        System.out.println("Ubicación agregada correctamente.");
    }
    
 // Implementación para editar una ubicación en el grafo
    private static void editarUbicacion(Scanner scanner) {
        System.out.print("Ingrese el ID de la ubicación a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        Ubicacion ubicacionExistente = grafo.getUbicacionPorId(id);
        if (ubicacionExistente == null) {
            System.out.println("La ubicación con ID " + id + " no existe.");
            return;
        }

        System.out.print("Ingrese el nuevo nombre de la ubicación: ");
        String nuevoNombre = scanner.nextLine();
        System.out.print("Ingrese el nuevo peso de la ubicación: ");
        double nuevoPeso = scanner.nextDouble();
        scanner.nextLine(); 

        Ubicacion nuevaUbicacion = new Ubicacion(id, nuevoNombre, nuevoPeso);
        grafo.editarUbicacion(id, nuevaUbicacion);
        System.out.println("Ubicación editada correctamente.");
    }

 // Implementación para eliminar una ubicación del grafo
    private static void eliminarUbicacion(Scanner scanner) {
        System.out.print("Ingrese el ID de la ubicación a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        grafo.eliminarUbicacion(id);
        System.out.println("Ubicación eliminada correctamente.");
    }

 // Implementación para conectar dos ubicaciones en el grafo
    private static void conectarUbicaciones(Scanner scanner) {
        System.out.print("Ingrese el ID de la ubicación de origen: ");
        int idOrigen = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Ingrese el ID de la ubicación de destino: ");
        int idDestino = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Ingrese la distancia de la ruta: ");
        double distancia = scanner.nextDouble();
        scanner.nextLine(); 
        grafo.conectarUbicaciones(idOrigen, idDestino, distancia);
        System.out.println("Conexión entre ubicaciones agregada correctamente.");
    }

 // Implementación para eliminar una ruta entre dos ubicaciones en el grafo
    private static void eliminarRuta(Scanner scanner) {
        System.out.print("Ingrese el ID de la ubicación de origen de la ruta a eliminar: ");
        int idOrigen = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Ingrese el ID de la ubicación de destino de la ruta a eliminar: ");
        int idDestino = scanner.nextInt();
        scanner.nextLine(); 
        grafo.eliminarArista(idOrigen, idDestino);
        System.out.println("Ruta eliminada correctamente.");
    }

 // Implementación para editar una ruta entre dos ubicaciones en el grafo
    private static void editarRuta(Scanner scanner) {
        System.out.print("Ingrese el ID de la ubicación de origen de la ruta a editar: ");
        int idOrigen = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el ID de la ubicación de destino de la ruta a editar: ");
        int idDestino = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nuevo peso de la ruta: ");
        double nuevoPeso = scanner.nextDouble();
        scanner.nextLine();
        grafo.editarArista(idOrigen, idDestino, nuevoPeso);
        System.out.println("Ruta editada correctamente.");
    }

 // Implementación para calcular la ruta más corta con Dijkstra
    private static void calcularRutaMasCorta(Scanner scanner) {
        if (grafo.getUbicaciones().size() < 2 || grafo.getAristas().isEmpty()) {
            System.out.println("Debe haber al menos dos ubicaciones y una conexión para "
            		+ "calcular la ruta más corta.");
            return;
        }

        System.out.print("Ingrese el ID de la ubicación de origen: ");
        int idOrigen = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el ID de la ubicación de destino: ");
        int idDestino = scanner.nextInt();
        scanner.nextLine();
        List<Arista> ruta = algoritmosRuta.dijkstra(idOrigen, idDestino);
        mostrarRuta(ruta);
    }

 // Implementación para encontrar el árbol de expansión mínima con el algoritmo de Prim
    private static void encontrarArbolExpansionMinimaPrim() {
        if (grafo.getUbicaciones().size() < 2 || grafo.getAristas().isEmpty()) {
            System.out.println("Debe haber al menos dos ubicaciones y una conexión para "
            		+ "encontrar el Árbol de Expansión Mínima (Prim).");
            return;
        }

        List<Arista> arbol = algoritmosRuta.prim();
        mostrarRuta(arbol);
    }

 // Implementación para encontrar el árbol de expansión mínima con el algoritmo de Kruskal
    private static void encontrarArbolExpansionMinimaKruskal() {
        if (grafo.getUbicaciones().size() < 2 || grafo.getAristas().isEmpty()) {
            System.out.println("Debe haber al menos dos ubicaciones y una conexión para "
            		+ "encontrar el Árbol de Expansión Mínima (Kruskal).");
            return;
        }

        List<Arista> arbol = algoritmosRuta.kruskal();
        mostrarRuta(arbol);
    }
    
 // Implementación para calcular la distancia más corta con el algoritmo de Floyd-Warshall
    private static void calcularDistanciaMasCortaFloydWarshall() {
        if (grafo.getUbicaciones().size() < 2 || grafo.getAristas().isEmpty()) {
            System.out.println("Debe haber al menos dos ubicaciones y una conexión para "
            		+ "calcular la distancia más corta (Floyd-Warshall).");
            return;
        }

        double[][] distancias = algoritmosRuta.floydWarshall();
        mostrarMatrizDistancias(distancias);
    }

    private static void mostrarMatrizDistancias(double[][] distancias) {
        System.out.println("Matriz de distancias más cortas:");
        for (int i = 0; i < distancias.length; i++) {
            for (int j = 0; j < distancias[i].length; j++) {
                if (distancias[i][j] == Double.POSITIVE_INFINITY) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(distancias[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

 // Implementación para planificar una ruta entre dos ubicaciones en el grafo
    private static void planificarRuta(Scanner scanner) {
        if (grafo.getUbicaciones().isEmpty()) {
            System.out.println("No hay ubicaciones disponibles.");
            return;
        }

        System.out.print("Ingrese el ID de la ubicación de origen: ");
        int idOrigen = scanner.nextInt();
        scanner.nextLine();

        if (grafo.getUbicacionPorId(idOrigen) == null) {
            System.out.println("La ubicación de origen no existe.");
            return;
        }

        System.out.print("Ingrese el ID de la ubicación de destino: ");
        int idDestino = scanner.nextInt();
        scanner.nextLine();

        if (grafo.getUbicacionPorId(idDestino) == null) {
            System.out.println("La ubicación de destino no existe.");
            return;
        }

        System.out.print("¿Prefiere la mejor ruta para tiempo o la mejor para distancia? (t/d): ");
        String preferencia = scanner.nextLine();

        switch (preferencia) {
            case "t":
                // Algoritmo para la mejor ruta en tiempo
                List<Arista> rutaTiempo = algoritmosRuta.dijkstra(idOrigen, idDestino);
                mostrarRuta(rutaTiempo);
                break;
            case "d":
                // Algoritmo para la mejor ruta en distancia
                List<Arista> rutaDistancia = algoritmosRuta.kruskal(); // o prim()
                mostrarRuta(rutaDistancia);
                break;
            default:
                System.out.println("Opción no válida. Por favor, ingrese 'tiempo' o 'distancia'.");
                break;
        }
    }

    private static void mostrarRuta(List<Arista> ruta) {
        if (ruta.isEmpty()) {
            System.out.println("No hay ruta disponible entre las ubicaciones seleccionadas.");
            return;
        }

        System.out.println("Ruta:");
        for (Arista arista : ruta) {
            System.out.println(arista.getOrigen().getNombre() + " -> " + arista.getDestino().getNombre() +
                    " (" + arista.getPeso() + " unidades)");
        }
    }

}



