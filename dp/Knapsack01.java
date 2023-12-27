public class Knapsack01 {

    private static int topDownKnapsack(int[] w, int[] v, int n, int c, Integer[] memo) {

        if (n < 0) return 0;

        if (memo[n] != null) return memo[n];

        if(w[n] > c) return 0;

        memo[n] =  Math.max(v[n] + topDownKnapsack(w, v, n - 1, c - w[n], memo), topDownKnapsack(w, v, n - 1, c, memo));

        return memo[n];
    }
}
