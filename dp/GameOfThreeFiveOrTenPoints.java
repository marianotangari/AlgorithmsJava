import java.util.Arrays;

public class GameOfThreeFiveOrTenPoints {

	public static void main(String[] args) {
		
		int[] memo = new int[12]; 
		Arrays.fill(memo, -1); 
		System.out.println(topDownFindTotalNumberOfWays(15, memo)); 
	}
	
	
	private static int topDownFindTotalNumberOfWays(int n, int[] memo) {
		
		if (n == 0) return 1;
		if (n < 0) return 0; 
		
		if (memo[n] > -1) return memo[n];
		
		memo[n] = topDownFindTotalNumberOfWays(n - 3, memo) + topDownFindTotalNumberOfWays(n - 5, memo) + 
			topDownFindTotalNumberOfWays(n - 10, memo);
			
		return memo[n];
	}
}