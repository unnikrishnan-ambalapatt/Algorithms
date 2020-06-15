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
        int[] sortedList = new int[arr.length];
        for (int item : arr) {
            sortedList[elemCount[item]] = item;
            elemCount[item]++;
        }
        return sortedList;
    }

    public static void main(String[] args) {
        int[] arr = {34, 2, 54, 75, 7, 3, 75, 2, 6};
        arr = countingSort(arr, 75);
        System.out.println(Arrays.toString(arr));
    }
}
