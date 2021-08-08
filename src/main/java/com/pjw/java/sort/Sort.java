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

        int middleIndex = (beginIndex + endIndex) / 2; //计算中间位
        mergeSortWithRange(array, beginIndex, middleIndex); //排序前半部分
        mergeSortWithRange(array, middleIndex + 1, endIndex); //排序后半部分
        mergeSortMergeArray(array, beginIndex, endIndex, middleIndex); //将两部分合并
    }

    /**
     * 归并排序，合并
     */
    private static void mergeSortMergeArray(int[] array, int beginIndex, int endIndex, int middleIndex) {
        int[] tmpArray = new int[endIndex - beginIndex + 1]; // 临时数组，长度为两部分之和
        int tmpArrayIndex = 0;

        int leftIndex = beginIndex; // 前半部分索引
        int rightIndex = middleIndex + 1; // 后半部分索引

        // 同时遍历并比较前后两部分数组，将值小的放入临时数组，直到任意一部分遍历结束
        while (leftIndex <= middleIndex && rightIndex <= endIndex) {
            if (array[leftIndex] <= array[rightIndex]) {
                tmpArray[tmpArrayIndex++] = array[leftIndex++];
            } else {
                tmpArray[tmpArrayIndex++] = array[rightIndex++];
            }
        }

        // 设置 start 和 end 准备处理未遍历完的那一部分
        int start = leftIndex;
        int end = middleIndex;
        if (rightIndex <= endIndex) {
            start = rightIndex;
            end = endIndex;
        }

        // 将未遍历完的那一部分的剩余值放入临时数组
        while (start <= end) {
            tmpArray[tmpArrayIndex++] = array[start++];
        }

        // 将临时数组的值放入原数组
        for (int i = 0; i <= endIndex - beginIndex; i++) {
            array[beginIndex + i] = tmpArray[i];
        }
    }

    /**
     * 快速排序
     */
    public static void quickSort(int[] array, int n) {
        if (n <= 1) {
            return;
        }
        quickSortWithRange(array, 0, n - 1);
    }

    /**
     * 快速排序，指定数组起止位置
     */
    private static void quickSortWithRange(int[] array, int beginIndex, int endIndex) {
        if (beginIndex >= endIndex) {
            return;
        }

        int middleIndex = quickSortPartition(array, beginIndex, endIndex);
        quickSortWithRange(array, beginIndex, middleIndex - 1);
        quickSortWithRange(array, middleIndex + 1, endIndex);
    }

    /**
     * 快速排序，分区，并返回中间值的索引位置
     */
    private static int quickSortPartition(int[] array, int beginIndex, int endIndex) {
        int endValue = array[endIndex];
        int i = beginIndex;

        for (int j = beginIndex; j < endIndex; j++) {
            if (array[j] < endValue) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
            }
        }

        int tmp = array[i];
        array[i] = endValue;
        array[endIndex] = tmp;

        return i;
    }

    /**
     * 计数排序
     */
    public static void countingSort(int[] array, int n) {
        if (n <= 1) {
            return;
        }

        // 查找数组的范围
        int max = array[0];
        for (int i = 1; i < n; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }

        // 计数数组
        int[] countArray = new int[max + 1];
        for (int i = 0; i < n; i++) {
            countArray[array[i]]++;
        }

        // 计数数组累加
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] = countArray[i] + countArray[i - 1];
        }

        // 临时数组存储排序后的值
        int[] tmpArray = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int index = countArray[array[i]] - 1;
            tmpArray[index] = array[i];
            countArray[array[i]]--;
        }

        // 赋值给原数组
        for (int i = 0; i < n; i++) {
            array[i] = tmpArray[i];
        }
    }

    /**
     * 堆排序
     */
    public static void heapSort(int[] array, int n) {
        buildHeap(array, n);
        int k = n;
        while (k > 0) {
            heapSwap(array, 0, k - 1);
            k--;
            heapify(array, k, 0);
        }
    }

    /**
     * 堆排序-建堆
     */
    private static void buildHeap(int[] array, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
    }

    /**
     * 堆排序-堆化
     */
    private static void heapify(int[] array, int n, int i) {
        while (true) {
            int maxIndex = i;
            int leftChild = i * 2 + 1;
            int rightChild = leftChild + 1;

            // 先和左子节点比较
            if (leftChild < n && array[i] < array[leftChild]) {
                maxIndex = leftChild;
            }
            // 再和右子节点比较
            if (rightChild < n && array[maxIndex] < array[rightChild]) {
                maxIndex = rightChild;
            }

            // 如果没有子节点比自己大，退出循环
            if (maxIndex == i) {
                break;
            }

            // 和比自己大的子节点交换
            heapSwap(array, i, maxIndex);
            i = maxIndex; // 从比自己大的子节点开始继续判断
        }
    }

    /**
     * 堆排序-交换数据
     */
    private static void heapSwap(int[] array, int index1, int index2) {
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }
}
