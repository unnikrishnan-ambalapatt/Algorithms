package org.learn;

import java.util.Arrays;

public class CountingSort {

    /**
     * Counting sort works by counting the number of objects that have each distinct key value.
     * <p>
     * Reference: https://en.wikipedia.org/wiki/Counting_sort
     *
     * @param arr - Array to be sorted
     * @param max - Highest value present in the array
     * @return
     */
    public static int[] countingSort(int[] arr, int max) {

        // This array would store number of occurrences of each element
        // at the index corresponding to the value of the element
        int[] elemCount = new int[max + 1];
        for (int item : arr) {
            elemCount[item]++;
        }

        int sumSoFar = 0;

        // Add the occurrences at element with the count so far
        for (int i = 0; i < elemCount.length; i++) {
            sumSoFar += elemCount[i];
            elemCount[i] = sumSoFar;
        }
        int[] sortedList = new int[max];

        // Place the elements in order based on elemCount
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
