package org.learn;

import java.util.Arrays;

public class BubbleSort {

    enum SortOrder {
        ASC, DESC
    }

    /**
     * Bubble sort is a simple but poorly performing algorithm to sort a list. Its worst case time complexity is О(n^2).
     *
     * @param list      - List to be sorted which will be sorted once function completes execution
     * @param sortOrder - Sorting order, can be SortOrder.ASC or SortOrder.DESC
     */
    public static void bubbleSort(Integer[] list, SortOrder sortOrder) {
        int temp;
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = i + 1; j < list.length; j++) {
                // Compare each pair of adjacent elements and swap if required
                if (SortOrder.DESC.equals(sortOrder)) {
                    if (list[j] > list[i]) {
                        swap(list, i, j);
                    }
                } else {
                    if (list[j] < list[i]) {
                        swap(list, i, j);
                    }
                }
            }
        }
    }

    private static void swap(Integer[] list, int i, int j) {
        int temp;
        temp = list[j];
        list[j] = list[i];
        list[i] = temp;
    }

    public static void main(String[] args) {

        Integer[] list = {33, 44, 56, 22, 566, 89, 2, 400, 22, 67};
        bubbleSort(list, SortOrder.ASC);
        System.out.println("Ascending: ");
        System.out.println(Arrays.toString(list));

        bubbleSort(list, SortOrder.DESC);
        System.out.println("Descending: ");
        System.out.println(Arrays.toString(list));
    }
}
