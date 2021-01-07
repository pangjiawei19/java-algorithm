package com.pjw.java.search;

/**
 * @author pangjiawei
 * @created 2021-01-07 18:28:55
 */
public class Search {

    public static int binarySearch(int[] array, int n, int value) {
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            // 当 left 和 right 很大时，可能会移除，所以可以改写为：int middle = low + (high - low) / 2
            // 为追求性能，可以进一步改写为：int middle = low + ((high - low) >> 1)
            int middle = (left + right) / 2;
            if (value == array[middle]) {
                return middle;
            } else if (value < array[middle]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return -1;
    }
}
