package com.pjw.java.sort;

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
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] > value) {
                    array[j + 1] = array[j];
                } else {
                    array[j + 1] = value;
                    break;
                }
            }
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
}
