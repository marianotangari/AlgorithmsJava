import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class GraphBFS {

    public static void main(String[] args) {

        LinkedList<Vertex> queue = new LinkedList<>();

        Vertex a = new Vertex(Vertex.Color.WHITE, "a");
        Vertex b = new Vertex(Vertex.Color.WHITE, "b");
        Vertex c = new Vertex(Vertex.Color.WHITE, "c");
        Vertex d = new Vertex(Vertex.Color.WHITE, "d");
        Vertex e = new Vertex(Vertex.Color.WHITE, "e");
        Vertex f = new Vertex(Vertex.Color.WHITE, "f");
        Vertex g = new Vertex(Vertex.Color.WHITE, "g");
        Vertex h = new Vertex(Vertex.Color.WHITE, "h");

        LinkedList<Vertex> listA = new LinkedList<>();
        LinkedList<Vertex> listB = new LinkedList<>();
        LinkedList<Vertex> listC = new LinkedList<>();
        LinkedList<Vertex> listD = new LinkedList<>();
        LinkedList<Vertex> listE = new LinkedList<>();
        LinkedList<Vertex> listF = new LinkedList<>();
        LinkedList<Vertex> listG = new LinkedList<>();
        LinkedList<Vertex> listH = new LinkedList<>();

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

        Graph graph = new Graph();

        graph.addVertexList(a, listA);
        graph.addVertexList(b, listB);
        graph.addVertexList(c, listC);
        graph.addVertexList(d, listD);
        graph.addVertexList(e, listE);
        graph.addVertexList(f, listF);
        graph.addVertexList(g, listG);

        //Setting the source vertex
        a.setColor(Vertex.Color.GRAY);
        a.setDistance(0);

        //Queueing source element
        queue.offer(a);

        while(!queue.isEmpty()){

            Vertex u = queue.poll();

            graph.getVertexList(u).forEach(v -> processVertex(u, v, queue));

            u.setColor(Vertex.Color.BLACK);

            System.out.println(u.getName());
        }
    }

    private static void processVertex(Vertex u, Vertex v, LinkedList<Vertex> queue) {
        if (v.getColor() == Vertex.Color.WHITE){
            v.setColor(Vertex.Color.GRAY);
            v.setDistance(u.getDistance() + 1);
            v.setPrev(u);
            queue.offer(v);
        }
    }

    private static class Graph {

        private Map<Vertex, LinkedList<Vertex>> adjList;

        private Graph() {
            this.adjList = new HashMap<>();
        }

        private void addVertexList(Vertex vertex, LinkedList<Vertex> list) {
            adjList.put(vertex, list);
        }

        private LinkedList<Vertex> getVertexList(Vertex vertex) {
            return adjList.get(vertex);
        }
    }

    private static class Vertex {

        private static enum Color { WHITE, GRAY, BLACK }

        private Color color;
        private int distance;
        private Vertex prev;
        private String name;

        public Vertex(Color color, String name) {
            this.color = color;
            this.distance = -1;
            this.prev = null;
            this.name = name;
        }

        private Vertex() {}

        public String getName() {
            return name;
        }

        public Color getColor() {
            return this.color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public Vertex getPrev() {
            return prev;
        }

        public void setPrev(Vertex vertex) {
            this.prev = vertex;
        }
    }
}