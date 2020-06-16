package org.learn;

import java.util.Arrays;

public class CountingSort {

    public static int[] countingSort(int[] arr, int max) {

        int[] elemCount = new int[max + 1];
        for (int item : arr) {
            elemCount[item]++;
        }
        int sumSoFar = 0;
        for (int i = 0; i < elemCount.length; i++) {
            sumSoFar += elemCount[i];
            elemCount[i] = sumSoFar;
        }
        int[] sortedList = new int[max];
        for (int i = arr.length - 1; i >= 0; i--) {
            sortedList[elemCount[arr[i]] - 1] = arr[i];
            elemCount[arr[i]]--;
        }
        return Arrays.copyOfRange(sortedList, 0, arr.length);
    }

    public static void main(String[] args) {
        int[] arr = {8, 7, 6, 5, 4, 3, 2, 10, 9, 55, 66, 44, 3, 4, 66};
        arr = countingSort(arr, 75);
        System.out.println(Arrays.toString(arr));
    }
}
