import java.util.ArrayList;
import java.util.ArrayDeque; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// Kosaraju’s Algorithm
public class StronglyConnectedComponents {

    public static void main(String[] args) {

        Vertex<String> a = new Vertex<>("0");
        Vertex<String> b = new Vertex<>("1");
        Vertex<String> c = new Vertex<>("2");
        Vertex<String> d = new Vertex<>("3");
        Vertex<String> e = new Vertex<>("4");

        Digraph<String> graph = new Digraph<>();

		List<Vertex<String>> listA = new ArrayList<>();
        List<Vertex<String>> listB = new ArrayList<>();
        List<Vertex<String>> listC = new ArrayList<>();
        List<Vertex<String>> listD = new ArrayList<>();
        List<Vertex<String>> listE = new ArrayList<>();
		
		listA.add(c); 
		listA.add(d); 
		listB.add(a);
		listC.add(b);
			  
		listD.add(e);
			
		Digraph<String> digraph = new Digraph<>(); 
	
		
		graph.addVertexList(d, listD);
		graph.addVertexList(e, listE);
		graph.addVertexList(a, listA);
		graph.addVertexList(c, listC);
		graph.addVertexList(b, listB);
		

		//Stack.
		ArrayDeque<Vertex<String>> stack = new ArrayDeque<>(); 
			
		//First step: dfs to fill the stack.
		for (Map.Entry<Vertex<String>, List<Vertex<String>>> entry : graph.getAdjList().entrySet()) {
			if (!entry.getKey().isVisited()) {
				firstDfs(graph, entry, stack);
			}
		}
		
		System.out.println(graph); 
		
		//Second step: transposing the digraph. 
		transposeGraph(graph); 
		
		System.out.println(graph); 
		
		//Additional step: setting all vertices as non visited. 
		graph.getAdjList().forEach((k,v) -> k.setVisited(false)); 
		
		List<Set<Vertex<String>>> connectedComponents = new ArrayList<>(); 
		
		var adjList = graph.getAdjList();
		
		//Third step. 
		while (!stack.isEmpty()) {
			Vertex<String> v = stack.pop(); 
			if (!v.isVisited()) {
				System.out.println(secondDfs(v, adjList, stack, new HashSet<Vertex<String>>()));
			}
		}
    }

    static <T> void firstDfs(Digraph<T> graph, Map.Entry<Vertex<T>, List<Vertex<T>>> entry,
		ArrayDeque<Vertex<T>> stack) {
			
		Vertex<T> key = entry.getKey();
		key.setVisited(true);
        
        for (Vertex<T> v : entry.getValue()) {
            if (!v.isVisited()) {
				v.setVisited(true); 
                firstDfs(graph, entry, stack);
            }
        }
		//Insert every vertex into the stack as it is finished.
		stack.push(key); 
    }
	
	static <T> Set<Vertex<T>> secondDfs(Vertex<T> vertex, Map<Vertex<T>, List<Vertex<T>>> adjList,
		ArrayDeque<Vertex<T>> stack, Set<Vertex<T>> components) {
		
		List<Vertex<T>> list = adjList.get(vertex);
		
		for (Vertex<T> v : list) {
			if (!v.isVisited()) {
				components.add(vertex);
				components.add(v);
				v.setVisited(true); 
				secondDfs(v, adjList, stack, components); 
			}
		}
		return components;
    }
	
	static <T> void transposeGraph(Digraph<T> graph) {
		
		Map<Vertex<T>, List<Vertex<T>>> newAdjList = new HashMap<>();
		
		for (Map.Entry<Vertex<T>, List<Vertex<T>>> entry : graph.getAdjList().entrySet()) {
			for (Vertex<T> vertex : entry.getValue()) {
				List<Vertex<T>> connected = newAdjList.get(vertex); 
				if (connected == null) {
					connected = new ArrayList<>(); 
				}
				connected.add(entry.getKey());
				newAdjList.put(vertex, connected); 
			}
		}
		graph.setAdjList(newAdjList); 
	}
}

 class Digraph<T> {

    Map<Vertex<T>, List<Vertex<T>>> adjList;
    
    Digraph() {
		this.adjList = new HashMap<>();
    }

    void addVertexList(Vertex<T> vertex, List<Vertex<T>> list) {
		adjList.put(vertex, list);
    }

    List<Vertex<T>> getVertexFromAdjList(Vertex<T> vertex) {
		return adjList.get(vertex);
    }

    Map<Vertex<T>, List<Vertex<T>>> getAdjList() {
		return adjList;
    }
	
	void setAdjList(Map<Vertex<T>, List<Vertex<T>>> adjList) {
		this.adjList = adjList; 
	}
	
	@Override
	public String toString() {
		return adjList.toString();
	}
}

class Vertex<T> {

	private boolean visited; 
    private T value; 

    public Vertex(T value) {
        this.value = value;
    }

    public void setVisited(boolean visited) {
		this.visited = visited; 
	}
	
	public boolean isVisited() {
		return visited; 
	}
	
	@Override
	public String toString() {
		return this.value.toString(); 
	}
}