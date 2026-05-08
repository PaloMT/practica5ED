package app;
import grafo.GrafoMA;
import javax.swing.*;
import java.util.*;

public class ClubLectura {
    /*
    Nombre: Sara Paloma Martínez-Tizón García
    Grupo de practicas: ISWM12
    */

    // Incluir atributos
    // Grafo NO DIRIGIDO que contiene los lectores del club de lectura
    // Lista de vertices del grafo

    private final GrafoMA red;
    private final Lector[] lectores;
    private final Map<Lector, Integer> indiceLectores; // Mapa para obtener el indice de un lector en el grafo
    private final int numLectores;

    // Constructor
    public ClubLectura(Lector[] lectores) {
        this.lectores = lectores;
        this.numLectores = this.lectores.length;

        this.indiceLectores = new HashMap<>();
        for (int i = 0; i < this.numLectores; i++) {
            this.indiceLectores.put(this.lectores[i], i);
        }
        this.red  = new GrafoMA(this.numLectores,false);

        // ToDo: Completar el constructor de ClubLectura creando el grafo con el numero de lectores y no dirigido
    }

    public GrafoMA getRed() {
        return red;
    }

    public Lector[] getLectores() {
        return lectores;
    }

    public int getNumLectores() {
        return numLectores;
    }

    public int getIndice(Lector l) {
        // Devuelve el indice del lector en el grafo, o -1 si no existe
        return this.indiceLectores.getOrDefault(l, -1);
    }

    public boolean conectarLectores(Lector l1, Lector l2) {
        boolean conectado = false;
        if( red.existeArista(l1.getId(),l2.getId())){
            conectado = true;
        }
        else red.insertarArista(l1.getId(),l2.getId());
        // ToDo: Completar para anadir la arista l1 <-> l2 (si se puede)
        return conectado;
    }

    public void mostrar() {
        for (int i = 0; i < this.numLectores; i++)
            System.out.println(this.lectores[i]);
        red.mostrar();
    }

    public boolean[] inicializa_visitados() {
        boolean[] visitados = new boolean[1]; // To-Do: Modificar. Tantos como vertices
        // ToDo: Inicializar visitados. Poner a false cada posicion.
        return visitados; // Array de visitados por defecto (ninguno visitado)
    }

    public List<Lector> getAmigos(Lector lector) { // Obtener lista de amigos directos
        List<Lector> amigos = new ArrayList<>();
        // ToDo: Completar getAmigos.
        // Si el lector existe y esta en el grafo, devuelve una lista con los amigos
        // Si el lector no existe o no esta en el grafo, devuelve una lista vacia
        return amigos;
    }

    public List<Lector> getGrupo(Lector lector) { // Obtener lista de grupo de amigos del lector
        List<Lector> grupo = new ArrayList<>();
        // ToDo: Completar getGrupo. Apoyate en recorridoEnProfundidad de GrafoMA sin modificarlo.
        // Si el lector existe y esta en el grafo, devuelve una lista con los amigos
        // Si el lector no existe o no esta en el grafo, devuelve una lista vacia
        return grupo;
    }

    public List<Lector> mayorGrupo() {
        List<Lector> mayor = new ArrayList<>();
        boolean[] visitados_global = inicializa_visitados();
        // ToDo: Completar mayorGrupo. Devuelve el grupo de amigos mas grande del club de lectura.
        // Apoyate en recorridoEnProfundidad de GrafoMA sin modificarlo.
        // Pista: puedes gestionar un array de visitados global y otro local para cada grupo
        return mayor;
    }

    public int contarGrupos() {
        int numGrupos = 0;
        // ToDo: Completar contarGrupos. Devuelve el numero de grupos de amigos distintos que hay en el club de lectura.
        // Apoyate en recorridoEnProfundidad de GrafoMA sin modificarlo.
        return numGrupos;
    }

    public String generoMasFrecuenteGrupo(Lector lector) {
        // ToDo: Completar generoMasFrecuenteGrupo. Devuelve el genero mas frecuente entre los amigos del grupo de un lector.
        // Si no existe el lector o no esta en el grafo, devuelve cadena vacia
        // Si hay empate entre varios generos, devuelve cualquiera de ellos
        // Utiliza un TreeMap para contar la frecuencia de cada genero entre los amigos del grupo
    return null;}

}
