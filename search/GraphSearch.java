import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class GraphSearch {

    public static void main(String[] args) {

        LinkedList<Vertex<String>> queue = new LinkedList<>();

        Graph<String> graph = initializeGraph(queue);

        bfs(graph, queue);
    }

    private static Graph<String> initializeGraph(LinkedList<Vertex<String>> queue) {

        Vertex<String> a = new Vertex<>(Vertex.Color.WHITE, "a");
        Vertex<String> b = new Vertex<>(Vertex.Color.WHITE, "b");
        Vertex<String> c = new Vertex<>(Vertex.Color.WHITE, "c");
        Vertex<String> d = new Vertex<>(Vertex.Color.WHITE, "d");
        Vertex<String> e = new Vertex<>(Vertex.Color.WHITE, "e");
        Vertex<String> f = new Vertex<>(Vertex.Color.WHITE, "f");
        Vertex<String> g = new Vertex<>(Vertex.Color.WHITE, "g");

        LinkedList<Vertex<String>> listA = new LinkedList<>();
        LinkedList<Vertex<String>> listB = new LinkedList<>();
        LinkedList<Vertex<String>> listC = new LinkedList<>();
        LinkedList<Vertex<String>> listD = new LinkedList<>();
        LinkedList<Vertex<String>> listE = new LinkedList<>();
        LinkedList<Vertex<String>> listF = new LinkedList<>();
        LinkedList<Vertex<String>> listG = new LinkedList<>();

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

        //Setting the source vertex
        a.setColor(Vertex.Color.GRAY);
        a.setDistance(0);

        //Queueing source element
        queue.offer(a);

        return graph;
    }

    private static void bfs(Graph<String> graph, LinkedList<Vertex<String>> queue) {

        while(!queue.isEmpty()){

            Vertex<String> u = queue.poll();

            graph.getVertexList(u).forEach(v -> processVertex(u, v, queue));

            u.setColor(Vertex.Color.BLACK);

            System.out.println(u.getValue());
        }
    }

    private static void processVertex(Vertex<String> u, Vertex<String> v, LinkedList<Vertex<String>> queue) {
        if (v.getColor() == Vertex.Color.WHITE){
            v.setColor(Vertex.Color.GRAY);
            v.setDistance(u.getDistance() + 1);
            v.setPrev(u);
            queue.offer(v);
        }
    }
}