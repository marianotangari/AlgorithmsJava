import java.util.ArrayList;
import java.util.List;

public class UnionFind {

    private static class TreeNode {

        private final int val;
        private TreeNode parent;
        private int rank = 0;

        private TreeNode(int val) {
            this.val = val;
            this.parent = this;
        }
    }

    public static void main(String[] args) {

        int[][] adjList = {{1,2}, {0,2}, {0,1,4,5}, {0,4}, {2,3}, {2}, {7,8}, {6,8}, {6}, {10,11}, {9}, {9}};
        int n = adjList.length;

        List<TreeNode> initialNodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            makeSet(initialNodes, i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < adjList[i].length; j++) {
                union(initialNodes.get(i), initialNodes.get(adjList[i][j]));
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.printf("Vertex %d belongs to tree with root %d%n", i, initialNodes.get(i).parent.val);
        }
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
}
