package moviegraph;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * MovieGraph class represents a bidirectional graph of vertices. Vertices are identified with strings.
 * @author Raisa Hossain
 */
public class MovieGraph {
    /* symbol table:- key: String vertex, value: set of neighboring vertices*/
    private SymTab<String, TSet<String>> symTab;
    private Scanner scanner;

    /* # of edges*/
    private int E;

    /*Initialize an empty graph */
    public MovieGraph() {
        symTab = new SymTab<String, TSet<String>>();
    }

    /**
     * This constructor initializes and loads a graph from a given file. Strings are separated by
     * white spaces in the file.
     * @param filename the name of the file
     * @throws FileNotFoundException if file is not found
     */
    public MovieGraph(String filename) throws FileNotFoundException {
        symTab = new SymTab<String, TSet<String>>();
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] names = line.split("\\s+");
            int i = 1;
            while (i < names.length) {
                addEdge(names[0], names[i] + " " + names[i+1]);
                i += 2;
            }

            //for (int i = 1; i < Math.floor(names.length/2) - 1; i++) {
            //    addEdge(names[0], (names[i] + " " + names[i+1]));
            //}
        }
    }

    /**
     * Returns the number of vertices in this graph.
     * @return # of vertices in this graph
     */
    public int V() {
        return symTab.size();
    }

    /**
     * Returns the number of edges in this graph.
     * @return # of edges.
     */
    public int E() {
        return E;
    }

    /* throw an exception if v is not a vertex */
    private void validateVertex(String v) {
        if (!hasVertex(v)) throw new IllegalArgumentException(v + " is not known in the provided data set.");
    }

    /**
     * Returns the degree of vertex v in this graph.
     * @param v the vertex
     * @return the degree of v in graph
     * @throws IllegalArgumentException if v is not a vertex in this graph
     */
    public int degree(String v) {
        validateVertex(v);
        return symTab.get(v).size();
    }

    /**
     *Adds the edge v-w to this graph(if it is not already in edge).
     * @param v1 vertix of the edge
     * @param v2 the other vertix of the edge
     */
    private void addEdge(String v1, String v2) {
        if (!hasVertex(v1)){
            addVertex(v1);
        }
        if (!hasVertex(v2)) {
            addVertex(v2);
        }
        if (!hasEdge(v1, v2)) {
            E++;
        }
        symTab.get(v1).add(v2);
        symTab.get(v2).add(v1);
    }

    /**
     * Adds vertex v to this graph
     * @param v vertex
     */
    public void addVertex(String v) {
        if (!hasVertex(v)) symTab.put(v, new TSet<String>());
    }

    /**
     * Returns the vertices in graph
     * @return the set of vertices in this graph
     */
    public Iterable<String> vertices() {
        return symTab.keys();
    }

    /**
     * Returns the set of vertices adjacent to v in this graph.
     * @param v vertex
     * @return set of vertices adjacent to vertex in this graph
     * @throws IllegalArgumentException if v is not a vertex of this graph
     */
    public Iterable<String> adjacentTo(String v) {
        validateVertex(v);
        return symTab.get(v);
    }

    /**
     * Checks if v is a vertex of this graph
     * @param v vertex
     * @return true if v is a vertex in this graph; false otherwise
     */
    public boolean hasVertex(String v) {
        return symTab.contains(v);
    }

    /**
     * Returns true id v-w is an edge in this graph.
     * @param v1 vertix of the edge
     * @param v2 the other vertex of the edge
     * @return true if v1 and v2 are vertices in this graph; false otherwise.
     * @throws IllegalArgumentException if either v1 or v2 are not vertices in this graph.
     */
    public boolean hasEdge(String v1, String v2) {
        validateVertex(v1);
        validateVertex(v2);
        return symTab.get(v1).contains(v2);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String v1 : symTab.keys()) {
            stringBuilder.append(v1).append(": ");
            for (String v2 : symTab.get(v1)) {
                stringBuilder.append(v2).append(" ");
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}




