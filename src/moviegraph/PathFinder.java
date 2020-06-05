package moviegraph;


/**
 * Runs BFS algorithm from source file src on a graph G.
 * After pre-processing the graph, PathFinder can process the shortest path queries
 * from source file src to any vertex v.
 * @author Raisa Hossain
 */
public class PathFinder {
    /*prev[v]: previous vertex on shortest path from src to v */
    private SymTab<String, String> prev = new SymTab<String, String>();

    /*dist[v] = distance of shortest path from src to v*/
    private SymTab<String, Integer> dist = new SymTab<String, Integer>();

    /**
     * run BFS in graph G from given source vertex src.
     * @param G MovieGraph
     * @param src source vertex
     */
    public PathFinder(MovieGraph G, String src) {
        Queue<String> queue = new Queue<String>();
        queue.enqueue(src);
        dist.put(src, 0);

        /*
        In for loop, remove next vertex v from queue and insert
        all of its neighbors, provided they have not been visited yet.
        */
        while(!queue.isEmpty()) {
            String v1 = queue.dequeue();
            for(String v2 : G.adjacentTo(v1)) {
                if(!dist.contains(v2)) {
                    queue.enqueue(v2);
                    dist.put(v2, 1 + dist.get(v1));
                    prev.put(v2, v1);
                }
            }
        }
    }

    /**
     * Check if vertex v is reachable from the source vector src
     * @param v vertex
     * @return true if v is reachable; false otherwise
     */
    public boolean hasPathTo(String v) {
        return dist.contains(v);
    }

    /**
     * Returns the length of the shortest path from v to src
     * @param v vertex
     * @return distance
     */
    public int distanceTo(String v) {
        if(!hasPathTo(v)) {
            return Integer.MAX_VALUE;
        }
        return dist.get(v)/2;
    }

    /**
     * Returns the shortest path from v to src(two vectors) as an Iterable
     * @param v vertex
     * @return shortest path
     */
    public Iterable<String> pathTo(String v) {
        Stack<String> path = new Stack<String>();
        while (v != null && dist.contains(v)) {
            path.push(v);
            v = prev.get(v);
        }
        return path;
    }
}
