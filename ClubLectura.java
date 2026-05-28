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
        if (l1 != null && l2 != null) {
            int indice1 = getIndice(l1);
            int indice2 = getIndice(l2);
            if (indice1 != -1 && indice2 != -1) {
                if (!red.existeArista(indice1, indice2)) {
                red.insertarArista(indice1, indice2);}
            conectado = true;}}
        // ToDo: Completar para anadir la arista l1 <-> l2 (si se puede)
        return conectado;
    }

    public void mostrar() {
        for (int i = 0; i < this.numLectores; i++)
            System.out.println(this.lectores[i]);
        red.mostrar();
    }

    public boolean[] inicializa_visitados() {
        boolean[] visitados = new boolean[red.getNumVertices()];
        // To-Do: Modificar. Tantos como vertices
        for (int i = 0; i < red.getNumVertices(); i++) {
            visitados[i] = false;}

        // ToDo: Inicializar visitados. Poner a false cada posicion.
        return visitados; // Array de visitados por defecto (ninguno visitado)
    }

    public List<Lector> getAmigos(Lector lector) { // Obtener lista de amigos directos
        List<Lector> amigos = new ArrayList<>();
        if (lector !=null && red.verticeEnRango(getIndice(lector))){
            for (int i=0; i<red.getNumVertices(); i++){
                if (red.existeArista(getIndice(lector), i)) {
                    amigos.add(lectores[i]);
                }
            }
        }
        // ToDo: Completar getAmigos.
        // Si el lector existe y esta en el grafo, devuelve una lista con los amigos
        // Si el lector no existe o no esta en el grafo, devuelve una lista vacia
        return amigos;
    }

    public List<Lector> getGrupo(Lector lector) { // Obtener lista de grupo de amigos del lector
        List<Lector> grupo = new ArrayList<>();
        if (lector != null){
            int indiceLector = getIndice(lector);
            if (red.verticeEnRango(indiceLector)) {
                boolean[] visitados = inicializa_visitados();
                red.recorridoEnProfundidad(indiceLector, visitados);
                for (int i = 0; i < visitados.length; i++) {
                    if (visitados[i]) {
                        grupo.add(lectores[i]);
                    }
                }
            }
        }
        // ToDo: Completar getGrupo. Apoyate en recorridoEnProfundidad de GrafoMA sin modificarlo.
        // Si el lector existe y esta en el grafo, devuelve una lista con los amigos
        // Si el lector no existe o no esta en el grafo, devuelve una lista vacia
        return grupo;
    }

    public List<Lector> mayorGrupo() {
        List<Lector> mayor = new ArrayList<>();
        if (red != null && red.getNumVertices() != 0) {
            boolean[] visitados_global = inicializa_visitados();
            for (int i = 0; i < red.getNumVertices(); i++) {
                if (!visitados_global[i]) {
                    boolean[] visitados_local = inicializa_visitados();
                    red.recorridoEnProfundidad(i, visitados_local);
                    List<Lector> grupoActual = new ArrayList<>();
                    for (int j = 0; j < visitados_local.length; j++) {
                        if (visitados_local[j]) {
                            grupoActual.add(lectores[j]);
                            visitados_global[j] = true;
                        }
                    }
                    if (grupoActual.size() > mayor.size()) {
                        mayor = grupoActual;
                    }
                }
        }}
        // ToDo: Completar mayorGrupo. Devuelve el grupo de amigos mas grande del club de lectura.
        // Apoyate en recorridoEnProfundidad de GrafoMA sin modificarlo.
        // Pista: puedes gestionar un array de visitados global y otro local para cada grupo
        return mayor;}

    public int contarGrupos() {
        int numGrupos = 0;
        boolean[] visitados_global = inicializa_visitados();

        for (int i = 0; i < red.getNumVertices(); i++) {
            if (!visitados_global[i]) {
                numGrupos++;
                red.recorridoEnProfundidad(i, visitados_global);
            }
        }
        // ToDo: Completar contarGrupos. Devuelve el numero de grupos de amigos distintos que hay en el club de lectura.
        // Apoyate en recorridoEnProfundidad de GrafoMA sin modificarlo.
        return numGrupos;
    }

    public String generoMasFrecuenteGrupo(Lector lector) {
        String generoMasFrecuente = "";
        if (lector != null && getIndice(lector) != -1) {
            List<Lector> grupo = getGrupo(lector);
            TreeMap<String, Integer> frecuencias = new TreeMap<>();
            for (int i = 0; i < grupo.size(); i++) {
                Lector l = grupo.get(i);
                String genero = l.getGeneroLibroFavorito();
                if (frecuencias.containsKey(genero)) {
                    frecuencias.put(genero, frecuencias.get(genero) + 1);
                } else {
                    frecuencias.put(genero, 1);
                }
            }
            int maxFrecuencia = 0;
            Iterator<Map.Entry<String, Integer>> iterador = frecuencias.entrySet().iterator();
            while (iterador.hasNext()) {
                Map.Entry<String, Integer> entrada = iterador.next();

                if (entrada.getValue() > maxFrecuencia) {
                    maxFrecuencia = entrada.getValue();
                    generoMasFrecuente = entrada.getKey();
                }
        }}
        // ToDo: Completar generoMasFrecuenteGrupo. Devuelve el genero mas frecuente entre los amigos del grupo de un lector.
        // Si no existe el lector o no esta en el grafo, devuelve cadena vacia
        // Si hay empate entre varios generos, devuelve cualquiera de ellos
        // Utiliza un TreeMap para contar la frecuencia de cada genero entre los amigos del grupo
    return generoMasFrecuente;}

}
