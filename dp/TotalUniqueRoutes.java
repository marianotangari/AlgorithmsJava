import java.util.List; 
import java.util.Map; 
import java.util.Arrays; 

//Exercise 9.2 (Variation of 9.1 with blocked horizontal edges) from "Dynamic Programming For Coding Interview". 
public class TotalUniqueRoutes {
	

	public static void main(String[] args) {
		
		Map<Integer, List<Integer>> blockedEdges = Map.of(1, List.of(2), 2, List.of(0,1,2), 3, List.of(2)); 
		
		System.out.println(topDownFindRoutes(3,3, new Integer[4][4], blockedEdges)); 
	}
	
	private static int topDownFindRoutes(int i, int j, Integer[][] memo, Map<Integer, List<Integer>> blockedEdges) {
		
		if (memo[i][j] != null) return memo[i][j]; 
		
		if (i == 0 && j == 0) {
			memo[i][j] = 1; 
		}
		else if (j == 0) {
			memo[i][j] = topDownFindRoutes(i - 1, j, memo, blockedEdges); 
		} 
		else if (i == 0) {
			if (isBlocked(i, j, blockedEdges)) {
				memo[i][j] = 0; 
			} else {
				memo[i][j] = topDownFindRoutes(i, j - 1, memo, blockedEdges); 
			}
		}
		else {
			int x = topDownFindRoutes(i - 1, j, memo, blockedEdges); 
			int y = isBlocked(i, j, blockedEdges) ? 0 : topDownFindRoutes(i, j - 1, memo, blockedEdges); 
			
			memo[i][j] = x + y; 
		}
		return memo[i][j]; 
	}
	
	private static boolean isBlocked(int i, int j, Map<Integer, List<Integer>> blockedEdges) {
		
		List<Integer> blocked = blockedEdges.get(i); 
		
		return blocked != null && blocked.stream().anyMatch(n -> n == j - 1); 
	}
}