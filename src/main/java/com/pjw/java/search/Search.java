package com.pjw.java.search;

/**
 * @author pangjiawei
 * @created 2021-01-07 18:28:55
 */
public class Search {

    /**
     * 二分查找
     */
    public static int binarySearch(int[] array, int n, int value) {
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            // 当 left 和 right 很大时，可能会移除，所以可以改写为：int middle = left + (right - left) / 2
            // 为追求性能，可以进一步改写为：int middle = left + ((right - left) >> 1)
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

    /**
     * 二分查找，返回元素第一个出现的位置
     */
    public static int binarySearchFirst(int[] array, int n, int value) {
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (value < array[middle]) {
                right = middle - 1;
            } else if (value > array[middle]) {
                left = middle + 1;
            } else {
                if (middle == 0 || array[middle - 1] != value) {
                    return middle;
                } else {
                    right = middle - 1;
                }
            }
        }

        return -1;
    }

    /**
     * 二分查找，返回元素最后一个出现的位置
     */
    public static int binarySearchFinal(int[] array, int n, int value) {
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (value < array[middle]) {
                right = middle - 1;
            } else if (value > array[middle]) {
                left = middle + 1;
            } else {
                if (middle == n - 1 || array[middle + 1] != value) {
                    return middle;
                } else {
                    left = middle + 1;
                }
            }
        }

        return -1;
    }

    /**
     * 二分查找，返回第一个大于等于指定元素的位置
     */
    public static int binarySearchFirstEqualOrGreater(int[] array, int n, int value) {
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (value <= array[middle]) {
                if (middle == 0 || array[middle - 1] < value) {
                    return middle;
                } else {
                    right = middle - 1;
                }
            } else if (value > array[middle]) {
                left = middle + 1;
            }
        }

        return -1;
    }

    /**
     * 二分查找，返回最后一个小于等于指定元素的位置
     */
    public static int binarySearchFinalEqualOrLess(int[] array, int n, int value) {
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (value < array[middle]) {
                right = middle - 1;
            } else if (value >= array[middle]) {
                if (middle == n - 1 || array[middle + 1] > value) {
                    return middle;
                } else {
                    left = middle + 1;
                }
            }
        }

        return -1;
    }
}
