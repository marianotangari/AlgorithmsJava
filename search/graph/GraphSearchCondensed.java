import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/*
     BFS and DFS implementation on Graphs (after CLRS).
     Condensed version.
     This file implements custom Graph and Vertex classes.
 */
public class GraphBFS {

    public static void main(String[] args) {

        LinkedList<Vertex<String>> queue = new LinkedList<>();

        Vertex<String> a = new Vertex<>(Vertex.Color.WHITE, "a");
        Vertex<String> b = new Vertex<>(Vertex.Color.WHITE, "b");
        Vertex<String> c = new Vertex<>(Vertex.Color.WHITE, "c");
        Vertex<String> d = new Vertex<>(Vertex.Color.WHITE, "d");
        Vertex<String> e = new Vertex<>(Vertex.Color.WHITE, "e");
        Vertex<String> f = new Vertex<>(Vertex.Color.WHITE, "f");
        Vertex<String> g = new Vertex<>(Vertex.Color.WHITE, "g");
        Vertex<String> h = new Vertex<>(Vertex.Color.WHITE, "h");

        LinkedList<Vertex<String>> listA = new LinkedList<>();
        LinkedList<Vertex<String>> listB = new LinkedList<>();
        LinkedList<Vertex<String>> listC = new LinkedList<>();
        LinkedList<Vertex<String>> listD = new LinkedList<>();
        LinkedList<Vertex<String>> listE = new LinkedList<>();
        LinkedList<Vertex<String>> listF = new LinkedList<>();
        LinkedList<Vertex<String>> listG = new LinkedList<>();
        LinkedList<Vertex<String>> listH = new LinkedList<>();

        listA.offer(e);
        listA.offer(b);
        listA.offer(c);

        listB.offer(a);
        listB.offer(c);

        listC.offer(a);
        listC.offer(b);
        listC.offer(e);
        listC.offer(d);
        listC.offer(g);

        listD.offer(e);
        listD.offer(c);

        listE.offer(f);
        listE.offer(a);
        listE.offer(c);
        listE.offer(d);
        listE.offer(g);

        listF.offer(e);

        listG.offer(e);
        listG.offer(c);

        Graph<String> graph = new Graph<>();

        graph.addVertexList(a, listA);
        graph.addVertexList(b, listB);
        graph.addVertexList(c, listC);
        graph.addVertexList(d, listD);
        graph.addVertexList(e, listE);
        graph.addVertexList(f, listF);
        graph.addVertexList(g, listG);

        graph.setVertices(a, b, c, d, e, f, g);

        //Setting the source vertex for BFS
        a.setColor(Vertex.Color.GRAY);

        //For BFS, this field holds the distance from vertex to source.
        //For DFS, fields 'd' and 'f' in Vertex class hold timestamps.
        a.setD(0);

        //Adding source vertex for BFS
        queue.offer(a);

        int time = 0;

        //BFS call
        bfs(graph, queue);

        for (Vertex<String> u : graph.getVertices()) {
            if (u.getColor() == Vertex.Color.WHITE)
                dfsVisit(graph, u, time);
        }
    }

    private static <T> void bfs(Graph<T> graph, LinkedList<Vertex<T>> queue) {
        while(!queue.isEmpty()){
            Vertex<T> u = queue.poll();
            graph.getVertexList(u).forEach(v -> processVertex(u, v, queue));
            u.setColor(Vertex.Color.BLACK);
            System.out.println(u.getValue());
        }
    }

    private static <T> void dfsVisit(Graph<T> graph, Vertex<T> u, int time) {
        time++;
        u.setD(time);
        u.setColor(Vertex.Color.GRAY);

        for (Vertex<T> v : graph.getVertexList(u)) {
            if (v.getColor() == Vertex.Color.WHITE) {
                System.out.println(v.getValue());
                v.setPrev(u);
                dfsVisit(graph, v, time);
            }
        }

        u.setColor(Vertex.Color.BLACK);
        time++;
        u.setF(time);
    }

    private static <T> void processVertex(Vertex<T> u, Vertex<T> v, LinkedList<Vertex<T>> queue) {
        if (v.getColor() == Vertex.Color.WHITE){
            v.setColor(Vertex.Color.GRAY);
            v.setD(u.getD() + 1);
            v.setPrev(u);
            queue.offer(v);
        }
    }

    private static class Graph<T> {

        private Map<Vertex<T>, LinkedList<Vertex<T>>> adjList;
        private List<Vertex<T>> vertices;

        private Graph() {
            this.adjList = new HashMap<>();
        }

        private void addVertexList(Vertex<T> vertex, LinkedList<Vertex<T>> list) {
            adjList.put(vertex, list);
        }

        private LinkedList<Vertex<T>> getVertexList(Vertex<T> vertex) {
            return adjList.get(vertex);
        }

        private Map<Vertex<T>, LinkedList<Vertex<T>>> getAdjList() {
            return adjList;
        }

        @SafeVarargs
        @SuppressWarnings("varargs")
        private void setVertices(Vertex<T>... vertices) {
            this.vertices = Arrays.asList(vertices);
        }

        private List<Vertex<T>> getVertices() {
            return vertices;
        }
    }

    private static class Vertex<T> {

        private static enum Color { WHITE, GRAY, BLACK }

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

        private Vertex() {}

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
}