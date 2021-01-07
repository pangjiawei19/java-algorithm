package com.pjw.java.sort;

import com.pjw.java.util.MathUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author pangjiawei
 * @created 2020-12-29 22:09:53
 */
public class SortTest {

    private final int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
    private int[] anotherArray;

    @BeforeEach
    void initAnotherArray() {
        anotherArray = Arrays.copyOf(array, array.length);
        MathUtil.shuffleArray(anotherArray);
    }

    @Test
    void bubbleSort() {
        Sort.bubbleSort(anotherArray, anotherArray.length);
        Assertions.assertArrayEquals(array, anotherArray);
    }

    @Test
    void insertionSort() {
        Sort.insertionSort(anotherArray, anotherArray.length);
        Assertions.assertArrayEquals(array, anotherArray);
    }

    @Test
    void selectionSort() {
        Sort.selectionSort(anotherArray, anotherArray.length);
        Assertions.assertArrayEquals(array, anotherArray);
    }

    @Test
    void mergeSort() {
        Sort.mergeSort(anotherArray, anotherArray.length);
        Assertions.assertArrayEquals(array, anotherArray);
    }

    @Test
    void quickSort() {
        Sort.quickSort(anotherArray, anotherArray.length);
        Assertions.assertArrayEquals(array, anotherArray);
    }

    @Test
    void countingSort() {
        Sort.countingSort(anotherArray, anotherArray.length);
        Assertions.assertArrayEquals(array, anotherArray);
    }
}
