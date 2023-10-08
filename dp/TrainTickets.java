import java.util.Arrays; 

public class TrainTickets {
	
	public static void main(String[] args) {
		
		int[][] cost = {
			{0, 10, 75, 94, 100, 135, 140, 150, 260}, 
			{-1, 0, 35, 50, 60, 180, 181, 200, 211}, 
			{-1, -1, 0, 20, 32, 84, 200, 300, 340}, 
			{-1, -1, -1, 0, 15, 105, 120, 122, 193},
			{-1, -1, -1, -1, 0, 50, 60, 99, 190}, 
			{-1, -1, -1, -1, -1, 0, 99, 160, 177}, 
			{-1, -1, -1, -1, -1, -1, 0, 150, 400}, 
			{-1, -1, -1, -1, -1, -1, -1, 0, 320}, 
			{-1, -1, -1, -1, -1, -1, -1, -1, 400}, 
			}; 
			
		int[][] memo = new int[cost.length][cost.length]; 
		
		System.out.println(minCostBottomUp(cost)); 
		System.out.println(calculateMinCost(0, 8, cost, memo)); 
	}
	
	private static int minCostBottomUp(int[][] cost) {
		
		int n = cost.length;
		
		int[] minCost = new int[n];  
		minCost[0] = 0; 
		minCost[1] = cost[0][1]; 
		
		for (int i = 2; i < n; i++) {
			
			minCost[i] = cost[0][i];
			
			for (int j = 1; j < i; j++) {
				
				minCost[i] = Math.min(minCost[i], minCost[j] + cost[j][i]);
				
			}
		}
		
		return minCost[n - 1];
	}
	
	private static int calculateMinCost(int s, int d, int[][] cost, int[][] memo) {
		
		if(memo[s][d] > 0) return memo[s][d]; 
		
		int minCost = cost[s][d]; 
		
		if (s == d || s == d - 1) return minCost;

		for (int i = s + 1; i < d; i++) {
			
			int temp = calculateMinCost(s, i, cost, memo) + calculateMinCost(i, d, cost, memo); 
			
			if (temp < minCost) minCost = temp; 
		}
		
		memo[s][d] = minCost; 
		
		return minCost; 
	}
}