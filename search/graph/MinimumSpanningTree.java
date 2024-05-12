import java.util.*;

/**
 * Minimum Spanning Tree using Kruskal and Prim Algorithms.
 */
public class MinimumSpanningTree {

    public static void main(String[] args) {

        var edges = Arrays.asList(
                new WeightedEdge(new int[]{0, 1}, 7),
                new WeightedEdge(new int[]{0, 2}, 8),
                new WeightedEdge(new int[]{1, 2}, 3),
                new WeightedEdge(new int[]{1, 3}, 6),
                new WeightedEdge(new int[]{1, 5}, 5),
                new WeightedEdge(new int[]{2, 3}, 4),
                new WeightedEdge(new int[]{2, 5}, 3),
                new WeightedEdge(new int[]{3, 5}, 2),
                new WeightedEdge(new int[]{3, 4}, 5),
                new WeightedEdge(new int[]{4, 5}, 2)
        );

        kruskalMst(edges);
    }

    private static void kruskalMst(List<WeightedEdge> edges) {

        int n = edges.size();

        List<TreeNode> initialNodes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            makeSet(initialNodes, i);
        }

        Collections.sort(edges);

        for (var e : edges) {

            var x = initialNodes.get(e.edge[0]);
            var y = initialNodes.get(e.edge[1]);

            if (findSet(x) != findSet(y)) {
                System.out.println(Arrays.toString(e.edge));
                union(x, y);
            }
        }
    }

    private static void primMst(List<WeightedEdge> edges) {

        var adjList = buildAdjList(edges);

        //TODO find min edge weight for each weighted edges.

        PriorityQueue<TreeNodeWithKey> minHeap = new PriorityQueue<>((a,b) -> b.key - a.key);
    }

    private static void makeSet(List<TreeNode> nodes, int value) {
        nodes.add(new TreeNode(value));
    }

    private static TreeNode findSet(TreeNode node) {

        if (node.parent != node) {
            node.parent = findSet(node.parent);
        }

        return node.parent;
    }

    private static void union(TreeNode x, TreeNode y) {
        link(findSet(x), findSet(y));
    }

    private static void link(TreeNode x, TreeNode y) {

        if (x != y) {
            if (x.rank == y.rank) {
                y.parent = x;
                x.rank++;
            } else if (x.rank > y.rank) {
                y.parent = x;
            } else {
                x.parent = y;
            }
        }
    }

    private static class WeightedEdge implements Comparable<WeightedEdge>{

        private final int[] edge;
        private final int weight;

        public WeightedEdge(int[] edge, int weight) {
            this.edge = edge;
            this.weight = weight;
        }

        @Override
        public int compareTo(WeightedEdge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    private static class TreeNode {

        protected final int val;
        protected TreeNode parent;
        protected int rank = 0;

        private TreeNode(int val) {
            this.val = val;
            this.parent = this;
        }
    }

    private static class TreeNodeWithKey extends TreeNode {

        private final int key;

        private TreeNodeWithKey(int val, int key) {
            super(val);
            this.key = key;
        }
    }

    private static Map<Integer, List<int[]>> buildAdjList(List<WeightedEdge> edges) {

        Map<Integer, List<int[]>> adjList = new HashMap<>();

        for (var e : edges) {

            int x = e.edge[0];
            int y = e.edge[1];

            adjList.computeIfAbsent(x, v -> new ArrayList<>()).add(new int[]{y, e.weight});
            adjList.computeIfAbsent(y, v -> new ArrayList<>()).add(new int[]{x, e.weight});
        }

        return adjList;
     }
}
