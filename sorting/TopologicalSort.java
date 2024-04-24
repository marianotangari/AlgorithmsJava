import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

//Topological sort implementing Kahn's Algorithm (BFS)
public class TopologicalSort {

    public static void main(String[] args) {

        //Array index represent a vertex in the graph.
        //The following graph contains 5 vertices 0,1,2,3 and 4.
        int[][] input = {{1,2}, {3,4}, {3}, {4}, {}};
        int[] dg = buildInDegreeArray(input);

        List<Integer> output = new ArrayList<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < dg.length; i++) {
            if (dg[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {

            int node = queue.poll();

            output.add(node);

            int[] arr = input[node];

            for (int i : arr) {
                if (--dg[i] == 0) {
                    queue.offer(i);
                }
            }
        }

        System.out.println(output);
    }

    //We calculate the initial set of vertices with in degree == 0.
    private static int[] buildInDegreeArray(int[][] in) {

        int[] dg = new int[in.length];

        for (int[] arr : in) {
            for (int j : arr) {
                dg[j]++;
            }
        }

        return dg;
    }
}
