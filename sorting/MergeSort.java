import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class MergeSort{

    public static void main(String[] args) {

        int[]  randomIntsArray = IntStream.generate(() -> new Random().nextInt(10000000)).limit(10000000).toArray();

        //Just to check how much time the algorithm is going to take.
        System.out.println(LocalDateTime.now());

        mergeSort(randomIntsArray);

        System.out.println(LocalDateTime.now());

    }

    private static void mergeSort(int[] list) {

        int length = list.length;

        if (length < 2)
            return;

        int half = list.length / 2;

        int[] left = Arrays.copyOfRange(list, 0, half);
        int[] right = Arrays.copyOfRange(list, half, length);

        mergeSort(left);
        mergeSort(right);

        merge(list, left, right);

    }

    private static void merge(int[] sortedArr, int[] lowerArr, int[] upperArr) {

        int n = lowerArr.length, m = upperArr.length;

        int i = 0, j = 0, k = 0;

        while(i < n && j < m){
            sortedArr[k++] = lowerArr[i] < upperArr[j] ? lowerArr[i++] : upperArr[j++];
        }

        while(i < n){
            sortedArr[k++] = lowerArr[i++];
        }

        while(j < m){
            sortedArr[k++] = upperArr[j++];
        }
    }
}