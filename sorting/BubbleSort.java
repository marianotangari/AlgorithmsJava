import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.IntStream;

public class BubbleSort{

    public static void main(String[] args) {

        int[] randomIntsArray = IntStream.generate(() -> new Random().nextInt(1000)).limit(1000).toArray();

        //Just to check how much time the algorithm is going to take.
        System.out.println(LocalDateTime.now());

        bubbleSort(randomIntsArray);

        System.out.println(LocalDateTime.now());
    }

    public static int[] bubbleSort (int[] array){

        int x, y;
        boolean swapped;

        do {

            swapped = false;

            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    x = array[i + 1];
                    y = array[i];
                    array[i] = x;
                    array[i + 1] = y;
                    swapped = true;
                }
            }

        } while (swapped);

        return array;
    }
}