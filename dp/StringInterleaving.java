//Exercise 9.3 from "Dynamic Programming For Coding Interview". 
public class StringInterleaving {

	public static void main(String[] args) {

		System.out.println("***TOP DOWN***");

		System.out.println(isInterleavingStringNoRepetitions("xyz", "abcd", "xabyczd"));
		System.out.println(topDownIsInterleaving("xyz", "abcd", "xabyczd", 0, 0, new Boolean[4][5]));
		System.out.println(topDownIsInterleaving("bcc", "bbca", "bbcbcac", 0, 0, new Boolean[4][5]));
		System.out.println(topDownIsInterleaving("bccga", "bbca", "bbcbcac", 0, 0, new Boolean[6][5]));
		System.out.println(topDownIsInterleaving("b", "bbca", "bbcbcac", 0, 0, new Boolean[2][5]));
		System.out.println(topDownIsInterleaving("b", "c", "bb", 0, 0, new Boolean[2][2]));

		System.out.println("***BOTTOM UP***");

		System.out.println(bottomUpIsInterleaving("xyz", "abcd", "xabyczd"));
		System.out.println(bottomUpIsInterleaving("bcc", "bbca", "bbcbcac"));
		System.out.println(bottomUpIsInterleaving("bccd", "bbca", "bbcbcadc"));
		System.out.println(bottomUpIsInterleaving("bcdc", "bbca", "bbcbcadc"));
		System.out.println(bottomUpIsInterleaving("bch", "bbca", "bbcbcac"));
		System.out.println(bottomUpIsInterleaving("bcht", "bbca", "bbcbcac"));
		System.out.println(bottomUpIsInterleaving("dra", "rffda", "drrffada"));
		System.out.println(bottomUpIsInterleaving("da", "rrffda", "drrffada"));
		System.out.println(bottomUpIsInterleaving("dr", "raffda", "drrffada"));
		System.out.println(bottomUpIsInterleaving("b", "c", "bb"));
	}

	private static boolean isInterleavingStringNoRepetitions(String str1, String str2, String str3) {

		int length1 = str1.length();
		int length2 = str2.length();
		int length3 = str3.length();

		if (length1 + length2 != length3) return false;

		int i = 0, j = 0, k = 0;

		while (k < length3) {

			char curr = str3.charAt(k++);

			if (i < length1 && str1.charAt(i) == curr) {
				i++;
			}
			else if (j < length2 && str2.charAt(j) == curr) {
				j++;
			}
			else {
				return false;
			}
		}
		return true;
	}

	//Top Down with Memoization
	private static boolean topDownIsInterleaving(String str1, String str2, String str3, int i, int j, Boolean[][] memo) {
		if (str1.length() + str2.length() != str3.length()) return false;

		if (i + j >= str3.length()) return true;

		if (memo[i][j] != null) {
			return memo[i][j];
		}

		boolean a = false;
		boolean b = false;

		if (i < str1.length() && str1.charAt(i) == str3.charAt(i + j)) {
			a = topDownIsInterleaving(str1, str2, str3, i + 1, j, memo);
		}

		if (j < str2.length() && str2.charAt(j) == str3.charAt(i + j)) {
			b = topDownIsInterleaving(str1, str2, str3, i, j + 1, memo);
		}

		memo[i][j] = a || b;

		return memo[i][j];
	}

	//DP - Bottom Up with Tabulation
	private static boolean bottomUpIsInterleaving(String str1, String str2, String str3) {

		if (str1.length() + str2.length() != str3.length()) return false;

		boolean[][] tab = new boolean[str1.length() + 1][str2.length() + 1];

		tab[0][0] = true;

		int length1 = str1.length();
		int length2 = str2.length();

		for (int i = 1; i <= length1; i++) {
			tab[i][0] = tab[i - 1][0] && str1.charAt(i - 1) == str3.charAt(0);
		}

		for (int j = 1; j <= length2; j++) {
			tab[0][j] = tab[0][j - 1] && str2.charAt(j - 1) == str3.charAt(0);
		}

		for (int i = 1; i <= length1; i++) {
			for (int j = 1; j <= length2; j++) {
				char k = str3.charAt(i + j - 1);
				tab[i][j] =  tab[i][j] = (str1.charAt(i - 1) == k && tab[i - 1][j]) || (str2.charAt(j - 1) == k && tab[i][j - 1]);
			}
		}

		return tab[length1][length2];
	}
}