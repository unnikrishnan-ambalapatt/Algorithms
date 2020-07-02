package org.learn;

public class BinarySearch {

    /**
     * Binary search is a half-interval search with logarithmic complexity
     * that finds the position of a target value within a sorted array.
     *
     * @param arr    - Array to search
     * @param target - Item to be searched
     * @return index of target in arr if found, -1 if not found
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0, mid, right = arr.length - 1;

        while (left <= right) {
            mid = (int) Math.floor((left + right) / 2);
            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 5));
        System.out.println(binarySearch(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 0));
        System.out.println(binarySearch(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 10));
        System.out.println(binarySearch(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 1));
        System.out.println(binarySearch(new int[]{3, 5, 7, 8, 9, 12, 34, 56, 89}, 12));
    }
}
