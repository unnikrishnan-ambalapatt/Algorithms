package org.learn;

import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int middle = (begin + end) / 2;
            mergeSort(arr, begin, middle);
            mergeSort(arr, middle + 1, end);
            merge(arr, begin, middle, end);
        }
    }

    private static void merge(int[] arr, int begin, int middle, int end) {
        int[] temp = new int[end - begin + 1];
        int leftHalfPointer = begin, rightHalfPointer = middle + 1, tempCounter = 0;
        while (leftHalfPointer <= middle && rightHalfPointer <= end) {
            if (arr[leftHalfPointer] <= arr[rightHalfPointer]) {
                temp[tempCounter++] = arr[leftHalfPointer++];
            } else {
                temp[tempCounter++] = arr[rightHalfPointer++];
            }
        }
        while (leftHalfPointer <= middle) {
            temp[tempCounter++] = arr[leftHalfPointer++];
        }
        while (rightHalfPointer <= end) {
            temp[tempCounter++] = arr[rightHalfPointer++];
        }
        for (leftHalfPointer = begin; leftHalfPointer <= end; leftHalfPointer += 1) {
            arr[leftHalfPointer] = temp[leftHalfPointer - begin];
        }
    }

    public static void main(String[] args) {
        int[] arr = {33, 44, 56, 22, 566, 89, 453, 75, 7547, 5487, 568, 68, 57, 43, 765, 967, 9, 734, 6, 25, 436, 7548, 56, 853, 623, 6, 457, 547, 34, 654, 834, 563, 634, 7, 546, 34, 856, 834, 5, 458, 56, 734, 756, 8, 346, 3478, 45, 734, 745, 7234, 6, 357, 34, 64, 7, 5468, 547, 548, 56, 845, 7, 548, 45, 854, 8, 452, 400, 22, 67, 44, 33, 5, 77, 5, 5423, 65, 22, 765, 1000, 344, 35, 53, 3, 54, 68, 12, 57, 9, 34, 234, 56, 879, 745, 235, 346, 658, 865, 53, 325, 75, 856, 45, 26, 346, 54, 34, 352, 347, 587, 347};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
