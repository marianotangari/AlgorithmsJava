import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SCC {

    public static void main(String[] args) {
        int[][] input = {{1}, {2}, {3, 4}, {0}, {5}, {6}, {4, 7}, {}};
        int[][] input2 = {{1}, {2}, {0, 3}, {4}, {5, 7}, {6}, {4, 7}, {}};
        scc(input, new ArrayDeque<>());
        System.out.println("*****************");
        scc(input2, new ArrayDeque<>());
    }

    private static void scc(int[][] input, ArrayDeque<Integer> stack) {

        int n = input.length;

        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            if (!visited[i]) dfs(input, i, visited, stack);
        }

        List<Integer>[] adjList = reverseGraph(input);
        Arrays.fill(visited, false);

        List<List<Integer>> scc = new ArrayList<>();

        while(!stack.isEmpty()) {

            int p = stack.pop();

            if (!visited[p]) {

                List<Integer> sublist = new ArrayList<>();

                secondDfs(adjList, p, visited, sublist);

                scc.add(sublist);
            }
        }

        for (var list : scc) {
            System.out.println(list);
        }
    }


    private static List<Integer>[] reverseGraph(int[][] input) {

        int n = input.length;
        List<Integer>[] reversedAdjList = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            reversedAdjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j : input[i]) {
                reversedAdjList[j].add(i);
            }
        }

        return reversedAdjList;
    }

    private static void dfs(int[][] input, int u, boolean[] visited, ArrayDeque<Integer> stack) {

        visited[u] = true;

        int[] adj = input[u];

        for (int v : adj) {
            if (!visited[v]) {
                dfs(input, v, visited, stack);
            }
        }

        stack.push(u);
    }

    private static void secondDfs(List<Integer>[] adjList, int u, boolean[] visited, List<Integer> sublist) {

        visited[u] = true;

        List<Integer> adj = adjList[u];

        for (int v : adj) {
            if (!visited[v]) {
                secondDfs(adjList, v, visited, sublist);
            }
        }

        sublist.add(u);
    }
}
