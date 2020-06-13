package org.learn;

import java.util.Arrays;

public class QuickSort {

    /**
     * Quick sort is an efficient sorting algorithm that uses divide-and-conquer method.
     * <p>
     * Reference: https://en.wikipedia.org/wiki/Quicksort
     *
     * @param arr  - Array to be sorted (can be sub-array of original array)
     * @param low  - Beginning index of sub-array
     * @param high - End index of sub-array
     */
    public static void quickSort(int[] arr, int low, int high) {
        int partitionBorder;

        // Keep calling this method recursively as long as beginning index is less than end index
        if (low < high) {

            // Partition the array at hand into two
            partitionBorder = partition(arr, low, high);

            // Quick sort the left partition
            quickSort(arr, low, partitionBorder);

            // Quick sort the right partition
            quickSort(arr, partitionBorder + 1, high);
        }
    }

    /**
     * This method partitions the array into two and move the elements such that
     * elements towards left of pivot are less than pivot and
     * elements towards right of pivot are greater than pivot.
     *
     * @param arr - Array being sorted / partitioned
     * @param low
     * @param high
     * @return
     */
    private static int partition(int[] arr, int low, int high) {

        // Find a pivot to base the sorting on.
        int pivot = arr[(high + low) / 2], i = low, j = high, temp;
        while (true) {

            // Keep moving the left pointer right until an element is found which is not less than pivot
            // By the time this loop completes, element at i will be greater than pivot and will have to be moved to right of pivot
            while (arr[i] < pivot) {
                i++;
            }

            // Keep moving the right pointer left until an element is found which is not greater than pivot
            // By the time this loop completes, element at j will be less than pivot and will have to be moved to left of pivot
            while (arr[j] > pivot) {
                j--;
            }

            // If left pointer reaches or crosses the position as right pointer, return right pointer
            if (i >= j) {
                return j;
            }

            // Swap the elements at i and j
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] arr = {33, 44, 56, 22, 566, 89, 453, 75, 7547, 5487, 568, 68, 57, 43, 765, 967, 9, 734, 1, 25, 436, 7548, 56, 853, 623, 6, 457, 547, 34, 654, 834, 563, 634, 15, 546, 34, 856, 834, 55, 458, 56, 734, 756, 80, 346, 3478, 45, 734, 745, 7234, 6, 357, 34, 64, 7, 5468, 547, 548, 56, 845, 7, 548, 45, 854, 8, 452, 400, 22, 67, 44, 33, 5, 77, 5, 5423, 65, 22, 765, 1000, 344, 35, 53, 3, 54, 68, 12, 57, 9, 34, 234, 56, 879, 745, 235, 346, 658, 865, 53, 325, 75, 856, 45, 26, 346, 54, 34, 352, 347, 587, 347};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
