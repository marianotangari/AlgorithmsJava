import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.time.temporal.ChronoUnit;

public class QuickSort{

    public static void main(String[] args){

        int[] randomIntsArray = IntStream.generate(() -> new Random().nextInt(10000000)).limit(10000000).toArray();

        //Just to check how much time the algorithm is going to take.
        LocalDateTime now = LocalDateTime.now();

        quickSort(randomIntsArray, 0, randomIntsArray.length -1);

        System.out.println(ChronoUnit.MILLIS.between(now, LocalDateTime.now()));

    }

    private static void quickSort(int[] arr, int p, int pivot){

        if (p < pivot){

            int q = partition(arr, p, pivot);
            quickSort(arr, p, q - 1);
            quickSort(arr, q + 1, pivot);

        }
    }

    private static int partition(int[] arr, int p, int pivot){

        int i = p - 1, x = arr[pivot], temp;

        for (int j = p; j < pivot; j++){

            if(arr[j] <= x) {

                temp = arr[++i];
                arr[i] = arr[j];
                arr[j] = temp;

            }

        }

        temp = arr[++i];
        arr[i] = x;
        arr[pivot] = temp;

        return i++;
    }
}