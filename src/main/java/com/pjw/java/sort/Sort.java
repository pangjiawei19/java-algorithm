package com.pjw.java.sort;

import java.util.Arrays;

/**
 * @author pangjiawei
 * @created 2020-12-29 22:02:38
 */
public class Sort {

    /**
     * 冒泡排序
     */
    public static void bubbleSort(int[] array, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 1; j < n - i; j++) {
                if (array[j - 1] > array[j]) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    /**
     * 插入排序
     */
    public static void insertionSort(int[] array, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 1; i < n; i++) {
            int value = array[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (array[j] > value) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = value;
        }
    }

    /**
     * 选择排序
     */
    public static void selectionSort(int[] array, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = tmp;
            }
        }
    }

    /**
     * 归并排序
     */
    public static void mergeSort(int[] array, int n) {
        if (n <= 1) {
            return;
        }
        mergeSortWithRange(array, 0, n - 1);
    }

    /**
     * 归并排序，指定数组起始位置
     */
    private static void mergeSortWithRange(int[] array, int beginIndex, int endIndex) {
        if (beginIndex >= endIndex) {
            return;
        }

        int middleIndex = (beginIndex + endIndex) / 2;
        mergeSortWithRange(array, beginIndex, middleIndex);
        mergeSortWithRange(array, middleIndex + 1, endIndex);
        mergeSortMergeArray(array, beginIndex, endIndex, middleIndex);
    }

    /**
     * 归并排序，合并
     */
    private static void mergeSortMergeArray(int[] array, int beginIndex, int endIndex, int middleIndex) {
        int[] tmpArray = new int[endIndex - beginIndex + 1];
        int tmpArrayIndex = 0;

        int leftIndex = beginIndex;
        int rightIndex = middleIndex + 1;

        while (leftIndex <= middleIndex && rightIndex <= endIndex) {
            if (array[leftIndex] <= array[rightIndex]) {
                tmpArray[tmpArrayIndex] = array[leftIndex];
                leftIndex++;
            } else {
                tmpArray[tmpArrayIndex] = array[rightIndex];
                rightIndex++;
            }
            tmpArrayIndex++;
        }

        int start = leftIndex;
        int end = middleIndex;
        if (rightIndex <= endIndex) {
            start = rightIndex;
            end = endIndex;
        }

        while (start <= end) {
            tmpArray[tmpArrayIndex++] = array[start++];
        }

        for (int i = 0; i <= endIndex - beginIndex; i++) {
            array[beginIndex + i] = tmpArray[i];
        }
    }
}
