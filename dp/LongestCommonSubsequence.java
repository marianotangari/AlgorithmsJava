public class LongestCommonSubsequence {

    //DP - Bottom Up with tabulation
    public int longestCommonSubsequence(String text1, String text2) {

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
