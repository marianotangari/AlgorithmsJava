import java.util.LinkedList;
import java.util.ArrayDeque; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class DagTopologicalSort {

    public static void main(String[] args) {

        Vertex<String> a = new Vertex<>(Vertex.Color.WHITE, "a");
        Vertex<String> b = new Vertex<>(Vertex.Color.WHITE, "b");
        Vertex<String> c = new Vertex<>(Vertex.Color.WHITE, "c");
        Vertex<String> d = new Vertex<>(Vertex.Color.WHITE, "d");
        Vertex<String> e = new Vertex<>(Vertex.Color.WHITE, "e");
        Vertex<String> f = new Vertex<>(Vertex.Color.WHITE, "f");
        Vertex<String> g = new Vertex<>(Vertex.Color.WHITE, "g");

        Digraph<String> graph = new Digraph<>();

	LinkedList<Vertex<String>> listA = new LinkedList<>();
        LinkedList<Vertex<String>> listB = new LinkedList<>();
        LinkedList<Vertex<String>> listC = new LinkedList<>();
        LinkedList<Vertex<String>> listD = new LinkedList<>();
        LinkedList<Vertex<String>> listE = new LinkedList<>();
        LinkedList<Vertex<String>> listF = new LinkedList<>();
        LinkedList<Vertex<String>> listG = new LinkedList<>();
		
	listA.offer(e); 
	listA.offer(c);
	listA.offer(b); 
		
	listC.offer(e);
	listC.offer(d);
		
	listE.offer(f);
		
	listG.offer(c); 
		
	Digraph<String> digraph = new Digraph<>(); 
		
	graph.addVertexList(a, listA);
        graph.addVertexList(b, listB);
        graph.addVertexList(c, listC);
        graph.addVertexList(d, listD);
        graph.addVertexList(e, listE);
        graph.addVertexList(f, listF);
        graph.addVertexList(g, listG);

        graph.setVertices(a, b, c, d, e, f, g);
		
        ArrayDeque<Vertex<String>> stack = new ArrayDeque<>(); 
		
	int time = 0;
		
	a.setD(0);
		
	for (Vertex<String> u : graph.getVertices()) {
            if (u.getColor() == Vertex.Color.WHITE) {
                dfsVisit(graph, u, stack, time);
	    }
        }
		
	while(!stack.isEmpty()) {
		System.out.println(stack.pop().getValue()); 
	}
    }

    static <T> void dfsVisit(Digraph<T> graph, Vertex<T> u, ArrayDeque<Vertex<T>> stack, int time) {
        time++;
        u.setD(time);
        u.setColor(Vertex.Color.GRAY);

        for (Vertex<T> v : graph.getVertexList(u)) {
            if (v.getColor() == Vertex.Color.WHITE) {
                v.setPrev(u);
                dfsVisit(graph, v, stack, time);
            }
        }

        u.setColor(Vertex.Color.BLACK);
        time++;
        u.setF(time);
		
		//Insert every vertex into the queue as it is finished
		stack.push(u); 
    }
}

 class Digraph<T> {

    Map<Vertex<T>, LinkedList<Vertex<T>>> adjList;
    private List<Vertex<T>> vertices;
    
    Digraph() {
	this.adjList = new HashMap<>();
    }

    void addVertexList(Vertex<T> vertex, LinkedList<Vertex<T>> list) {
	adjList.put(vertex, list);
    }

    LinkedList<Vertex<T>> getVertexList(Vertex<T> vertex) {
	return adjList.get(vertex);
    }

    Map<Vertex<T>, LinkedList<Vertex<T>>> getAdjList() {
	return adjList;
    }

    void setVertices(Vertex<T>... vertices) {
	this.vertices = Arrays.asList(vertices);
    }

    List<Vertex<T>> getVertices() {
	return vertices;
    }
}

class Vertex<T> {

    static enum Color { WHITE, GRAY, BLACK }

    private Color color;
    private int d;
    private int f;
    private Vertex<T> prev;
    private String value;

    public Vertex(Color color, String value) {
        this.color = color;
        this.d = -1;
        this.prev = null;
        this.value = value;
    }

    Vertex() {}

    public String getValue() {
        return value;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getF(){
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public Vertex<T> getPrev() {
        return prev;
    }

    public void setPrev(Vertex<T> vertex) {
        this.prev = vertex;
    }
}
