public class TotalNumberOfTiles {
	
	public static void main(String[] args) {
		int[] memo = new int[6];
		System.out.println(memoizedFindMaxNumberOfTiles(3, memo)); 
		System.out.println(bottomUpFindMaxNumberOfTiles(6));
	}
	
	//Fibonacci! 
	private static int memoizedFindMaxNumberOfTiles(int n, int[] memo) {
		
		if (n == 0) return 0; 
		if (n == 1) return 1; 
		if (n == 2) return 2;
		
		if (memo[n] != 0) return memo[n];  
		
		memo[n] = memoizedFindMaxNumberOfTiles(n - 1, memo) + memoizedFindMaxNumberOfTiles(n - 2, memo); 
		
		return memo[n]; 
	}	
	
	private static int bottomUpFindMaxNumberOfTiles(int n) {
		
		if (n == 1) return 1; 
		if (n == 2) return 2; 
		
		int a = 1; 
		int b = 2;
		int c = 0; 
		
		for (int i = 3; i <= n; i++) {
			c = a + b; 
			a = b; 
			b = c; 
		}
		
		return c; 
	}
}