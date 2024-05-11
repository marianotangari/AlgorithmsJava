import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    private static void primMst() {
        //TODO complete Prim's algorithm for MSTs.
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

        private final int val;
        private TreeNode parent;
        private int rank = 0;

        private TreeNode(int val) {
            this.val = val;
            this.parent = this;
        }
    }
}
