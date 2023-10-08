public class MatrixMinPath {
	
	public static void main(String[] args) {
		
		int[][] matrix = {{1,3,5,8},{4,2,1,7},{4,3,2,3}}; 
		 
		int[][] memo = new int[3][4];
		
		System.out.println(topDownFindMinCost(matrix, 2, 3, memo));
		System.out.println(bottomUpFindMinCost(matrix, 3, 4));		
		System.out.println(bottomUpFindMinCostWithDiagonal(matrix, 3, 4));		
	}
	
	private static int topDownFindMinCost(int[][] matrix, int i, int j, int[][] memo) {
		
		if (memo[i][j] != 0) {
			return memo[i][j]; 
		}
		if (i == 0 && j == 0) {
			memo[i][j] = matrix[0][0]; 
		}
		else if (j == 0) {
			memo[i][j] = topDownFindMinCost(matrix, i - 1, j, memo) + matrix[i][0]; 
		}
		else if (i == 0) {
			memo[i][j] = topDownFindMinCost(matrix, i, j - 1, memo) + matrix[0][j]; 
		}
		else {
			int y = topDownFindMinCost(matrix, i - 1, j, memo); 
			int x = topDownFindMinCost(matrix, i, j - 1, memo); 
			
			memo[i][j] = matrix[i][j] + Math.min(x, y); 
		}
		
		return memo[i][j];
	}
	
	private static int bottomUpFindMinCost(int[][] matrix, int n, int m) {
		
		int[][] arr = new int[n][m]; 
		
		arr[0][0] = matrix[0][0]; 
		
		for (int j = 1; j < m; j++) {
			arr[0][j] = arr[0][j - 1] + matrix[0][j]; 
		}
		
		for (int i = 1; i < n; i++) {
			arr[i][0] = arr[i - 1][0] + matrix[i][0]; 
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				arr[i][j] = matrix[i][j] + Math.min(arr[i - 1][j], arr[i][j - 1]); 
			}
		}
			
		return arr[n - 1][m - 1];
	}
	
	private static int bottomUpFindMinCostWithDiagonal(int[][] matrix, int n, int m) {
		
		int[][] arr = new int[n][m]; 
		
		arr[0][0] = matrix[0][0]; 
		
		for (int j = 1; j < m; j++) {
			arr[0][j] = arr[0][j - 1] + matrix[0][j]; 
		}
		
		for (int i = 1; i < n; i++) {
			arr[i][0] = arr[i - 1][0] + matrix[i][0]; 
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				arr[i][j] = matrix[i][j] + Math.min(Math.min(arr[i - 1][j], arr[i][j - 1]), arr[i - 1][j - 1]); 
			}
		}
			
		return arr[n - 1][m - 1];
	}
}