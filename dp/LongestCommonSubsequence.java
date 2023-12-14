public class LongestCommonSubsequence {

    //Top-Down approach with Memoization.
    public Integer longestCommonSubsequenceTopDown(String text1, String text2, int i, int j, Integer[][] memo) {

        if (i < 0 || j < 0) return 0;

        if (memo[i][j] != null) return memo[i][j];

        if (text1.charAt(i) == text2.charAt(j)) {
            memo[i][j] = 1 + longestCommonSubsequenceTopDown(text1, text2, i - 1, j - 1, memo);
        } else {
            memo[i][j] = Math.max(
                    longestCommonSubsequenceTopDown(text1, text2, i, j - 1, memo),
                    longestCommonSubsequenceTopDown(text1, text2, i - 1, j, memo));
        }

        return memo[i][j];
    }

    //DP - Bottom Up with tabulation
    public int longestCommonSubsequenceBottomUp(String text1, String text2) {

        int length1 = text1.length();
        int length2 = text2.length();

        int[][] tab = new int[length1 + 1][length2 + 1];

        for (int i = 0; i <= length2; i++) {
            tab[0][i] += 0;
        }

        for (int i = 0; i <= length1; i++) {
            tab[i][0] += 0;
        }

        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    tab[i][j] = 1 + tab[i - 1][j - 1];
                } else {
                    tab[i][j] = Math.max(tab[i - 1][j], tab[i][j - 1]);
                }
            }
        }

        return tab[length1][length2];
    }
}
