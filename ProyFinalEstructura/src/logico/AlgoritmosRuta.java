package logico;

import java.util.*;

public class AlgoritmosRuta {
    private Grafo grafo; // Grafo sobre el cual se aplican los algoritmos

    public AlgoritmosRuta(Grafo grafo) {
        this.grafo = grafo; // Inicializa el grafo
    }

 // Método para encontrar la ruta más corta entre dos ubicaciones utilizando el algoritmo de Dijkstra
    public List<Arista> dijkstra(int idOrigen, int idDestino) {
        Map<Ubicacion, Double> distancias = new HashMap<>();
        Map<Ubicacion, Ubicacion> padres = new HashMap<>();
        Set<Ubicacion> visitados = new HashSet<>();
        PriorityQueue<Ubicacion> colaPrioridad = new PriorityQueue<>(Comparator.comparingDouble(distancias::get));

        Ubicacion origen = grafo.getUbicacionPorId(idOrigen);
        Ubicacion destino = grafo.getUbicacionPorId(idDestino);

        // Inicializar distancias
        for (Ubicacion u : grafo.getUbicaciones()) {
            distancias.put(u, Double.POSITIVE_INFINITY);
        }
        distancias.put(origen, 0.0);
        colaPrioridad.add(origen);

        while (!colaPrioridad.isEmpty()) {
            Ubicacion actual = colaPrioridad.poll();
            visitados.add(actual);

            for (Arista arista : grafo.getAristas()) {
                if (arista.getOrigen().equals(actual) && !visitados.contains(arista.getDestino())) {
                    double nuevaDistancia = distancias.get(actual) + arista.getPeso();
                    if (nuevaDistancia < distancias.get(arista.getDestino())) {
                        distancias.put(arista.getDestino(), nuevaDistancia);
                        padres.put(arista.getDestino(), actual);
                        colaPrioridad.add(arista.getDestino());
                    }
                }
            }
        }

        // Reconstruir ruta
        List<Arista> ruta = new ArrayList<>();
        Ubicacion u = destino;
        while (padres.containsKey(u)) {
            Ubicacion padre = padres.get(u);
            Arista arista = grafo.getAristaPorUbicaciones(padre.getId(), u.getId());
            ruta.add(arista);
            u = padre;
        }
        Collections.reverse(ruta);
        return ruta; // Retorna la lista de aristas que representan la ruta más corta
    }

 // Método para encontrar el árbol de expansión mínima utilizando el algoritmo de Prim
    public List<Arista> prim() {
        Set<Ubicacion> visitados = new HashSet<>();
        PriorityQueue<Arista> aristasDisponibles = new PriorityQueue<>(Comparator.comparingDouble(Arista::getPeso));
        List<Arista> arbolExpansionMinima = new ArrayList<>();

        visitados.add(grafo.getUbicaciones().get(0)); // Empezar desde una ubicación arbitraria

        while (visitados.size() < grafo.getUbicaciones().size()) {
            for (Arista arista : grafo.getAristas()) {
                if (visitados.contains(arista.getOrigen()) && !visitados.contains(arista.getDestino())) {
                    aristasDisponibles.add(arista);
                }
            }

            Arista aristaMinima = aristasDisponibles.poll();
            visitados.add(aristaMinima.getDestino());
            arbolExpansionMinima.add(aristaMinima);
        }

        return arbolExpansionMinima; // Retorna la lista de aristas del árbol de expansión mínima
    }

 // Método para encontrar el árbol de expansión mínima utilizando el algoritmo de Kruskal
    public List<Arista> kruskal() {
        List<Arista> aristasOrdenadas = new ArrayList<>(grafo.getAristas());
        aristasOrdenadas.sort(Comparator.comparingDouble(Arista::getPeso));
        List<Arista> arbolExpansionMinima = new ArrayList<>();
        
        int[] padre = new int[grafo.getUbicaciones().size()];
        int[] rango = new int[grafo.getUbicaciones().size()];
        for (int i = 0; i < grafo.getUbicaciones().size(); i++) {
            padre[i] = i;
            rango[i] = 0;
        }

        for (Arista arista : aristasOrdenadas) {
            int uIndex = grafo.getUbicaciones().indexOf(arista.getOrigen());
            int vIndex = grafo.getUbicaciones().indexOf(arista.getDestino());

            int uPadre = encontrarPadre(uIndex, padre);
            int vPadre = encontrarPadre(vIndex, padre);

            if (uPadre != vPadre) {
                arbolExpansionMinima.add(arista);
                unir(uPadre, vPadre, rango, padre);
            }
        }

        return arbolExpansionMinima; // Retorna la lista de aristas del árbol de expansión mínima
    }

 // Método auxiliar para encontrar el padre de un nodo en el algoritmo de Kruskal
    private int encontrarPadre(int nodo, int[] padre) {
        if (padre[nodo] != nodo) {
            padre[nodo] = encontrarPadre(padre[nodo], padre);
        }
        return padre[nodo];
    }

 // Método auxiliar para unir dos subconjuntos en el algoritmo de Kruskal
    private void unir(int x, int y, int[] rango, int[] padre) {
        if (rango[x] < rango[y]) {
            padre[x] = y;
        } else if (rango[x] > rango[y]) {
            padre[y] = x;
        } else {
            padre[y] = x;
            rango[x]++;
        }
    }


 /* Método para encontrar todas las distancias más cortas entre 
    todas las parejas de ubicaciones utilizando el algoritmo de Floyd-Warshall*/
    public double[][] floydWarshall() {
        int n = grafo.getUbicaciones().size();
        double[][] distancias = new double[n][n];

        // Inicializar distancias con infinito para todas las ubicaciones
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distancias[i][j] = Double.POSITIVE_INFINITY;
            }
        }

        // Asignar distancias conocidas
        for (Arista arista : grafo.getAristas()) {
            int i = grafo.getUbicaciones().indexOf(arista.getOrigen());
            int j = grafo.getUbicaciones().indexOf(arista.getDestino());
            distancias[i][j] = arista.getPeso();
        }

        // Algoritmo de Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                    }
                }
            }
        }

        return distancias;
    }
}

