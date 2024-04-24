import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class TopologicalSort {

    public static void main(String[] args) {

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
