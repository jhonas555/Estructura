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
            mostrarMenu(); // Muestra el men� de opciones
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    agregarUbicacion(scanner); // Llama al m�todo para agregar una ubicaci�n
                    break;
                case 2:
                	editarUbicacion(scanner); // Llama al m�todo para editar una ubicaci�n
                    break;
                case 3:
                	eliminarUbicacion(scanner); // Llama al m�todo para eliminar una ubicaci�n
                    break;
                case 4:
                	conectarUbicaciones(scanner); // Llama al m�todo para conectar dos ubicaciones
                    break;
                case 5:
                	eliminarRuta(scanner); // Llama al m�todo para eliminar una ruta (arista)
                    break;
                case 6:
                    editarRuta(scanner); // Llama al m�todo para editar una ruta (arista)
                    break;
                case 7:
                    calcularRutaMasCorta(scanner); // Llama al m�todo para calcular con Djikstra
                    break;
                case 8:
                    encontrarArbolExpansionMinimaPrim(); // Llama al m�todo para calcular con Prim
                    break;
                case 9:
                    encontrarArbolExpansionMinimaKruskal(); // Llama al m�todo para calcular con Kruskal
                    break;
                case 10:
                    calcularDistanciaMasCortaFloydWarshall(); // Llama al m�todo para calcular con FloydWarshall
                    break;
                case 11:
                	planificarRuta(scanner); // Llama al m�todo para planificar una ruta entre dos ubicaciones
                    break;
                case 12:
                    salir = true;
                    break;
                default:
                    System.out.println("Opci�n no v�lida. Intente de nuevo.");
                    break;
            }
        }

        System.out.println("�Hasta luego!");
        scanner.close();
    }

 // M�todo para mostrar el men� de opciones
    private static void mostrarMenu() {
        System.out.println("------ Sistema de Gesti�n de Rutas ------");
        System.out.println("1. Agregar Ubicaci�n");
        System.out.println("2. Editar Ubicaci�n");
        System.out.println("3. Eliminar Ubicaci�n");
        System.out.println("4. Conectar Ubicaciones");
        System.out.println("5. Eliminar Ruta");
        System.out.println("6. Editar Ruta");
        System.out.println("7. Calcular Ruta m�s Corta (Dijkstra)");
        System.out.println("8. Encontrar �rbol de Expansi�n M�nima (Prim)");
        System.out.println("9. Encontrar �rbol de Expansi�n M�nima (Kruskal)");
        System.out.println("10. Calcular Ruta m�s Corta (Floyd-Warshall)");
        System.out.println("11. Planificar Rutas");
        System.out.println("12. Salir");
        System.out.print("Ingrese una opci�n: ");
    }

 // Implementaci�n para agregar una ubicaci�n al grafo
    private static void agregarUbicacion(Scanner scanner) {
        System.out.print("Ingrese el ID de la nueva ubicaci�n: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Ingrese el nombre de la nueva ubicaci�n: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el peso de la nueva ubicaci�n: ");
        double peso = scanner.nextDouble();
        scanner.nextLine(); 
        Ubicacion ubicacion = new Ubicacion(id, nombre, peso);
        grafo.agregarUbicacion(ubicacion);
        System.out.println("Ubicaci�n agregada correctamente.");
    }
    
 // Implementaci�n para editar una ubicaci�n en el grafo
    private static void editarUbicacion(Scanner scanner) {
        System.out.print("Ingrese el ID de la ubicaci�n a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        Ubicacion ubicacionExistente = grafo.getUbicacionPorId(id);
        if (ubicacionExistente == null) {
            System.out.println("La ubicaci�n con ID " + id + " no existe.");
            return;
        }

        System.out.print("Ingrese el nuevo nombre de la ubicaci�n: ");
        String nuevoNombre = scanner.nextLine();
        System.out.print("Ingrese el nuevo peso de la ubicaci�n: ");
        double nuevoPeso = scanner.nextDouble();
        scanner.nextLine(); 

        Ubicacion nuevaUbicacion = new Ubicacion(id, nuevoNombre, nuevoPeso);
        grafo.editarUbicacion(id, nuevaUbicacion);
        System.out.println("Ubicaci�n editada correctamente.");
    }

 // Implementaci�n para eliminar una ubicaci�n del grafo
    private static void eliminarUbicacion(Scanner scanner) {
        System.out.print("Ingrese el ID de la ubicaci�n a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        grafo.eliminarUbicacion(id);
        System.out.println("Ubicaci�n eliminada correctamente.");
    }

 // Implementaci�n para conectar dos ubicaciones en el grafo
    private static void conectarUbicaciones(Scanner scanner) {
        System.out.print("Ingrese el ID de la ubicaci�n de origen: ");
        int idOrigen = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Ingrese el ID de la ubicaci�n de destino: ");
        int idDestino = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Ingrese la distancia de la ruta: ");
        double distancia = scanner.nextDouble();
        scanner.nextLine(); 
        grafo.conectarUbicaciones(idOrigen, idDestino, distancia);
        System.out.println("Conexi�n entre ubicaciones agregada correctamente.");
    }

 // Implementaci�n para eliminar una ruta entre dos ubicaciones en el grafo
    private static void eliminarRuta(Scanner scanner) {
        System.out.print("Ingrese el ID de la ubicaci�n de origen de la ruta a eliminar: ");
        int idOrigen = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Ingrese el ID de la ubicaci�n de destino de la ruta a eliminar: ");
        int idDestino = scanner.nextInt();
        scanner.nextLine(); 
        grafo.eliminarArista(idOrigen, idDestino);
        System.out.println("Ruta eliminada correctamente.");
    }

 // Implementaci�n para editar una ruta entre dos ubicaciones en el grafo
    private static void editarRuta(Scanner scanner) {
        System.out.print("Ingrese el ID de la ubicaci�n de origen de la ruta a editar: ");
        int idOrigen = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el ID de la ubicaci�n de destino de la ruta a editar: ");
        int idDestino = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nuevo peso de la ruta: ");
        double nuevoPeso = scanner.nextDouble();
        scanner.nextLine();
        grafo.editarArista(idOrigen, idDestino, nuevoPeso);
        System.out.println("Ruta editada correctamente.");
    }

 // Implementaci�n para calcular la ruta m�s corta con Dijkstra
    private static void calcularRutaMasCorta(Scanner scanner) {
        if (grafo.getUbicaciones().size() < 2 || grafo.getAristas().isEmpty()) {
            System.out.println("Debe haber al menos dos ubicaciones y una conexi�n para "
            		+ "calcular la ruta m�s corta.");
            return;
        }

        System.out.print("Ingrese el ID de la ubicaci�n de origen: ");
        int idOrigen = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el ID de la ubicaci�n de destino: ");
        int idDestino = scanner.nextInt();
        scanner.nextLine();
        List<Arista> ruta = algoritmosRuta.dijkstra(idOrigen, idDestino);
        mostrarRuta(ruta);
    }

 // Implementaci�n para encontrar el �rbol de expansi�n m�nima con el algoritmo de Prim
    private static void encontrarArbolExpansionMinimaPrim() {
        if (grafo.getUbicaciones().size() < 2 || grafo.getAristas().isEmpty()) {
            System.out.println("Debe haber al menos dos ubicaciones y una conexi�n para "
            		+ "encontrar el �rbol de Expansi�n M�nima (Prim).");
            return;
        }

        List<Arista> arbol = algoritmosRuta.prim();
        mostrarRuta(arbol);
    }

 // Implementaci�n para encontrar el �rbol de expansi�n m�nima con el algoritmo de Kruskal
    private static void encontrarArbolExpansionMinimaKruskal() {
        if (grafo.getUbicaciones().size() < 2 || grafo.getAristas().isEmpty()) {
            System.out.println("Debe haber al menos dos ubicaciones y una conexi�n para "
            		+ "encontrar el �rbol de Expansi�n M�nima (Kruskal).");
            return;
        }

        List<Arista> arbol = algoritmosRuta.kruskal();
        mostrarRuta(arbol);
    }
    
 // Implementaci�n para calcular la distancia m�s corta con el algoritmo de Floyd-Warshall
    private static void calcularDistanciaMasCortaFloydWarshall() {
        if (grafo.getUbicaciones().size() < 2 || grafo.getAristas().isEmpty()) {
            System.out.println("Debe haber al menos dos ubicaciones y una conexi�n para "
            		+ "calcular la distancia m�s corta (Floyd-Warshall).");
            return;
        }

        double[][] distancias = algoritmosRuta.floydWarshall();
        mostrarMatrizDistancias(distancias);
    }

    private static void mostrarMatrizDistancias(double[][] distancias) {
        System.out.println("Matriz de distancias m�s cortas:");
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

 // Implementaci�n para planificar una ruta entre dos ubicaciones en el grafo
    private static void planificarRuta(Scanner scanner) {
        if (grafo.getUbicaciones().isEmpty()) {
            System.out.println("No hay ubicaciones disponibles.");
            return;
        }

        System.out.print("Ingrese el ID de la ubicaci�n de origen: ");
        int idOrigen = scanner.nextInt();
        scanner.nextLine();

        if (grafo.getUbicacionPorId(idOrigen) == null) {
            System.out.println("La ubicaci�n de origen no existe.");
            return;
        }

        System.out.print("Ingrese el ID de la ubicaci�n de destino: ");
        int idDestino = scanner.nextInt();
        scanner.nextLine();

        if (grafo.getUbicacionPorId(idDestino) == null) {
            System.out.println("La ubicaci�n de destino no existe.");
            return;
        }

        System.out.print("�Prefiere la mejor ruta para tiempo o la mejor para distancia? (t/d): ");
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
                System.out.println("Opci�n no v�lida. Por favor, ingrese 'tiempo' o 'distancia'.");
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



