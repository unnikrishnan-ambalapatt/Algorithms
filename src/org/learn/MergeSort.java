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
        int[] arr = {44, 33, 5, 77, 5, 5423, 65, 22, 765};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
