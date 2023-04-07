import java.util.Arrays; 
import java.util.Random;
import java.util.stream.IntStream;

public class InsertionSort {
	
	public static void main(String[] args) {
		
		int[] arr = IntStream.generate(() -> new Random().nextInt(20)).limit(30).toArray();
		
		insertionSort(arr); 
	}
	
	private static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			
			int key = arr[i]; 
			int j = i - 1; 
			
			while (j >= 0 && arr[j] > key) 
				arr[j + 1] = arr[j--]; 
			
			arr[++j] = key; 
		}
	}
}
			
		