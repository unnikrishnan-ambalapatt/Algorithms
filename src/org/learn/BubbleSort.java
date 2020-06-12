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

        Integer[] list = {33, 44, 56, 22, 566, 89, 453, 75, 7547, 5487, 568, 68, 57, 43, 765, 967, 9, 734, 6, 25, 436, 7548, 56, 853, 623, 6, 457, 547, 34, 654, 834, 563, 634, 7, 546, 34, 856, 834, 5, 458, 56, 734, 756, 8, 346, 3478, 45, 734, 745, 7234, 6, 357, 34, 64, 7, 5468, 547, 548, 56, 845, 7, 548, 45, 854, 8, 452, 400, 22, 67, 44, 33, 5, 77, 5, 5423, 65, 22, 765, 1000, 344, 35, 53, 3, 54, 68, 12, 57, 9, 34, 234, 56, 879, 745, 235, 346, 658, 865, 53, 325, 75, 856, 45, 26, 346, 54, 34, 352, 347, 587, 347};
        bubbleSort(list, SortOrder.ASC);
        System.out.println("Ascending: ");
        System.out.println(Arrays.toString(list));

        bubbleSort(list, SortOrder.DESC);
        System.out.println("Descending: ");
        System.out.println(Arrays.toString(list));
    }
}
