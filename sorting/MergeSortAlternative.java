import java.util.LinkedList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.time.LocalDateTime;


/**
 * Alternative MergeSort implementation using queues.
 */
public class MergeSort {

    public static void main(String[] args) {

        int[]  array = IntStream.generate(() -> new Random().nextInt(10000000)).limit(10000000).toArray();

        System.out.println(LocalDateTime.now());

        mergeSort(array, 0, array.length - 1);

        System.out.println(LocalDateTime.now());
    }

    private static void mergeSort(int[] arr, int low, int high) {

        if (low < high) {

            int middle = (low + high) / 2;

            mergeSort(arr, low, middle);
            mergeSort(arr, middle + 1, high);

            merge(arr, low, middle, high);
        }
    }

    private static void merge(int[] arr, int low, int middle, int high) {

        LinkedList<Integer> queue1 = new LinkedList<>();
        LinkedList<Integer> queue2 = new LinkedList<>();

        for (int i = low; i <= middle; i++) {
            queue1.offer(arr[i]);
        }

        for (int i = middle + 1; i <= high; i++) {
            queue2.offer(arr[i]);
        }

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            arr[low++] = queue1.peek() > queue2.peek() ? queue1.poll() : queue2.poll();
        }

        while(!queue1.isEmpty())
            arr[low++] = queue1.poll();

        while(!queue2.isEmpty())
            arr[low++] = queue2.poll();
    }
}
