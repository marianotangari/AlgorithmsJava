import java.util.Arrays;

public class EditDistance {
	
	public static void main(String[] args) {
		
		String str1 = "Saturday"; 
		String str2 = "Sunday";
		
		System.out.println(findMinimumDistance(str1, str2, 0, 0)); 
		System.out.println(bottomUpMinDistance(str1, str2)); 
	}
	
	private static int findMinimumDistance(String str1, String str2, int k, int j) {
		
		if (k == str1.length()) return str2.length() - j;
		
		if (j == str2.length()) return str1.length() - k;
		
		if (str1.charAt(k) == str2.charAt(j)){ 
			return findMinimumDistance(str1, str2, k + 1, j + 1); 
		}
			
		return 1 + getMin(findMinimumDistance(str1, str2, k + 1, j), 
						  findMinimumDistance(str1, str2, k, j + 1), 
						  findMinimumDistance(str1, str2, k + 1, j + 1)); 
	}
	
	private static int bottomUpMinDistance(String str1, String str2) {
		
		int n = str1.length() + 1; 
		int m = str2.length() + 1; 
		
		int[][] tab = new int[n][m]; 
		
		for (int i = 0; i < n; i++) {
			tab[i][0] = i;  
		}
		
		for (int i = 0; i < m; i++) {
			tab[0][i] = i; 
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					tab[i][j] = tab[i - 1][j - 1]; 
				} else {
					tab[i][j] = 1 + getMin(tab[i - 1][j], tab[i][j - 1], tab[i - 1][j - 1]);
				}
			}
		}
		return tab[n - 1][m - 1]; 
	}
	
	private static int getMin(int... values) {
			return Arrays.stream(values).reduce(Integer.MAX_VALUE, Math::min);
    }
}