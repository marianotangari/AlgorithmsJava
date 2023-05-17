import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {

        //input array.
        int[] array = {2, 4, 13, 5, 17, 9, 8 ,6, 10, 1, 11, 14, 7, 20, 23, 24};
        heapSort(array);

        //printing the output.
        System.out.println(Arrays.toString(array));
    }

    private static void heapSort(int[] arr) {

        buildMaxHeap(arr);

        int size = arr.length - 1;

        for (int i = size; i >= 0; i--) {
            swap(arr, 0, i);
            size--;
            maxHeapify(arr, 0, size);
        }
    }

    private static void buildMaxHeap(int[] arr) {

        int size = arr.length - 1;

        for (int i = size / 2; i >= 0; i--) {
            maxHeapify(arr, i, size);
        }
    }
    private static void maxHeapify(int[] arr, int i, int size) {

        int largest = i;
        int left = (2 * i) + 1;
        int right = (2 * i) + 2;

        if (left <= size && arr[left] > arr[i]) {
            largest = left;
        }
        if (right <= size && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, i, largest);
            maxHeapify(arr, largest, size);
        }
    }
    private static void swap(int[] arr, int i, int element) {
        int temp = arr[i];
        arr[i] = arr[element];
        arr[element] = temp;
    }
}