package org.learn;

import java.util.Arrays;

public class QuickSort {

    public static void quickSort(int[] arr, int low, int high) {
        int partitionBorder;
        if (low < high) {
            partitionBorder = partition(arr, low, high);
            quickSort(arr, low, partitionBorder);
            quickSort(arr, partitionBorder + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[(high + low) / 2], i = low, j = high, temp;
        while (true) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i >= j) {
                return j;
            }
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] arr = {33, 44, 56, 22, 566, 89, 453, 75, 7547, 5487, 568, 68, 57, 43, 765, 967, 9, 734, 6, 25, 436, 7548, 56, 853, 623, 6, 457, 547, 34, 654, 834, 563, 634, 7, 546, 34, 856, 834, 5, 458, 56, 734, 756, 8, 346, 3478, 45, 734, 745, 7234, 6, 357, 34, 64, 7, 5468, 547, 548, 56, 845, 7, 548, 45, 854, 8, 452, 400, 22, 67, 44, 33, 5, 77, 5, 5423, 65, 22, 765, 1000, 344, 35, 53, 3, 54, 68, 12, 57, 9, 34, 234, 56, 879, 745, 235, 346, 658, 865, 53, 325, 75, 856, 45, 26, 346, 54, 34, 352, 347, 587, 347};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
