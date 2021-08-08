package com.pjw.java.heap;

/**
 * @author pangjiawei
 * @created 2021-01-26 12:29:17
 */
public class Heap {

    private int[] array; // 数组，从下标 1 开始存储数据
    private int n; // 堆中可存储的最大数据个数
    private int count; // 堆中已存储的数据个数

    public Heap(int capacity) {
        this.array = new int[capacity + 1];
        this.n = capacity;
        this.count = 0;
    }

    public void insert(int num) {
        // 判断堆满
        if (count >= n) {
            return;
        }

        // 将数据插到堆尾
        this.count++;
        this.array[count] = num;

        // 自下向上进行堆化
        int i = count;
        while (i / 2 > 0 && array[i] > array[i / 2]) {
            swap(array, i, i / 2);
            i = i / 2;
        }
    }

    public void removeMax() {
        // 堆空判断
        if (count < 1) {
            return;
        }

        count--; // 堆数据减1
        array[1] = array[count]; // 将堆尾数据放到堆顶

        int i = 1; // 从堆顶节点开始
        while (true) {
            int maxIndex = i;

            // 先和左子节点比较
            if (i * 2 <= count && array[i] < array[i * 2]) {
                maxIndex = i * 2;
            }
            // 再和右子节点比较
            if (i * 2 + 1 <= count && array[maxIndex] < array[i * 2 + 1]) {
                maxIndex = i * 2 + 1;
            }

            // 如果没有子节点比自己大，退出循环
            if (maxIndex == i) {
                break;
            }

            // 和比自己大的子节点交换
            swap(array, i, maxIndex);
            i = maxIndex; // 从比自己大的子节点开始继续判断
        }
    }

    private void swap(int[] array, int index1, int index2) {
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }
}
