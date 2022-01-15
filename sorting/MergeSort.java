import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class MergeSort{

    public static void main(String[] args) {

        int[]  randomIntsArray = IntStream.generate(() ->
                new Random().nextInt(10000000)).limit(10000000).toArray();

        //Just to check how much time the algorithm is going to take.
        System.out.println(LocalDateTime.now());

        mergeSort(randomIntsArray);

        System.out.println(LocalDateTime.now());

    }

    private static int[] mergeSort(int[] list) {

        int half = list.length / 2, length = list.length;

        return length < 2 ? list : merge(list, mergeSort(Arrays.copyOfRange(list, 0, half)), mergeSort(Arrays.copyOfRange(list, half, length)));

    }

    private static int[] merge(int[] sortedArr, int[] lowerArr, int[] upperArr) {

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

        return sortedArr;
    }
}