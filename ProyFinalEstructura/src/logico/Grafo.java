package logico;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private List<Ubicacion> ubicaciones; // Lista de ubicaciones en el grafo
    private List<Arista> aristas; // Lista de aristas (conexiones) entre ubicaciones

    // Constructor de la clase Grafo
    public Grafo() {
        this.ubicaciones = new ArrayList<>(); // Inicializa la lista de ubicaciones
        this.aristas = new ArrayList<>(); // Inicializa la lista de aristas
    }

    // M�todo para agregar una ubicaci�n al grafo
    public void agregarUbicacion(Ubicacion ubicacion) {
        ubicaciones.add(ubicacion); // Agrega la ubicaci�n a la lista de ubicaciones
    }

    // M�todo para eliminar una ubicaci�n del grafo
    public void eliminarUbicacion(int id) {
        ubicaciones.removeIf(u -> u.getId() == id); // Elimina la ubicaci�n con el ID especificado
        aristas.removeIf(a -> a.getOrigen().getId() == id || a.getDestino().getId() == id);
        // Elimina las aristas que tengan como origen o destino la ubicaci�n eliminada
    }

    // M�todo para editar una ubicaci�n del grafo
    public void editarUbicacion(int id, Ubicacion nuevaUbicacion) {
        eliminarUbicacion(id); // Elimina la ubicaci�n con el ID especificado
        agregarUbicacion(nuevaUbicacion); // Agrega la nueva ubicaci�n al grafo
    }

    // M�todo para conectar dos ubicaciones con una arista (conexi�n)
    public void conectarUbicaciones(int idOrigen, int idDestino, double peso) {
        Ubicacion origen = null;
        Ubicacion destino = null;

        // Busca las ubicaciones con los IDs especificados
        for (Ubicacion u : ubicaciones) {
            if (u.getId() == idOrigen) {
                origen = u;
            }
            if (u.getId() == idDestino) {
                destino = u;
            }
        }

        // Si se encontraron ambas ubicaciones, se crea una nueva arista
        if (origen != null && destino != null) {
            Arista arista = new Arista(origen, destino, peso);
            aristas.add(arista); // Se agrega la nueva arista a la lista de aristas
        } else {
            System.out.println("Una o ambas ubicaciones no existen.");
        }
    }

    // M�todo para eliminar una arista entre dos ubicaciones
    public void eliminarArista(int idOrigen, int idDestino) {
        aristas.removeIf(a -> a.getOrigen().getId() == idOrigen && a.getDestino().getId() == idDestino);
        // Elimina la arista que tenga como origen y destino los IDs especificados
    }

    // M�todo para editar el peso de una arista entre dos ubicaciones
    public void editarArista(int idOrigen, int idDestino, double nuevoPeso) {
        aristas.stream()
                .filter(a -> a.getOrigen().getId() == idOrigen && a.getDestino().getId() == idDestino)
                .findFirst()
                .ifPresent(a -> a.setPeso(nuevoPeso)); // Busca la arista y actualiza su peso
    }

    // Getter para obtener la lista de ubicaciones
    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    // Getter para obtener la lista de aristas
    public List<Arista> getAristas() {
        return aristas;
    }

    // M�todo para obtener una ubicaci�n por su ID
    public Ubicacion getUbicacionPorId(int id) {
        return ubicaciones.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null); // Retorna la ubicaci�n con el ID especificado, o null si no se encuentra
    }

    // M�todo para obtener una arista por los IDs de sus ubicaciones de origen y destino
    public Arista getAristaPorUbicaciones(int idOrigen, int idDestino) {
        return aristas.stream()
                .filter(a -> a.getOrigen().getId() == idOrigen && a.getDestino().getId() == idDestino)
                .findFirst()
                .orElse(null); // Retorna la arista con los IDs de origen y destino especificados, o null si no se encuentra
    }

    // M�todo toString para representar el grafo como una cadena de texto
    @Override
    public String toString() {
        return "Grafo{" +
                "ubicaciones=" + ubicaciones +
                ", aristas=" + aristas +
                '}';
    }
}
