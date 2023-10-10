public class TotalPathCount {
	
	public static void main(String[] args) {
		
		System.out.println(topDownFindTotalPath(2, 2, new int[3][3])); 
		System.out.println(bottomUpFindTotalPath(3,4)); 
	}
	
	private static int topDownFindTotalPath(int i, int j, int memo[][]) {

		if (memo[i][j] != 0) return memo[i][j]; 
		
		if (i == 0 && j == 0) {
			memo[i][j] = 1; 
		}
		
		else if (j == 0) {
			
			memo[i][j] = topDownFindTotalPath(i - 1, j, memo); 
			
		} 
		else if (i == 0) {
			
			memo[i][j] = topDownFindTotalPath(i, j - 1, memo); 
			
		} else {
			
			int x = topDownFindTotalPath(i - 1, j, memo);
			int y = topDownFindTotalPath(i, j - 1, memo); 
			
			memo[i][j] = x + y;
		}
		
		return memo[i][j]; 
	}	
	
	private static int bottomUpFindTotalPath(int n, int m) {
		
		int[][] tab = new int[n][m]; 
		
		for (int i = 1; i < n; i++) {
			tab[i][0] = 1; 
		}
		
		for (int i = 1; i < m; i++) {
			tab[0][i] = 1; 
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				tab[i][j] = tab[i - 1][j] + tab[i][j - 1]; 
			}
		}
		return tab[n - 1][m - 1]; 
	}
}