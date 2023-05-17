import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;

public class Graph<T> {

    private final Map<Vertex<T>, LinkedList<Vertex<T>>> adjList;

    private Graph() {
        this.adjList = new HashMap<>();
    }

    void addVertexList(Vertex<T> vertex, LinkedList<Vertex<T>> list) {
        adjList.put(vertex, list);
    }

    LinkedList<Vertex<T>> getVertexList(Vertex<T> vertex) {
        return adjList.get(vertex);
    }
}