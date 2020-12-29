package com.pjw.java.sort;

import com.pjw.java.util.MathUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author pangjiawei
 * @created 2020-12-29 22:09:53
 */
public class SortTest {

    private final int[] array = {1, 2, 3, 4, 5, 6, 7, 8};

    @Test
    void bubbleSort() {
        int[] anotherArray = Arrays.copyOf(array, array.length);
        MathUtil.shuffleArray(anotherArray);
        Sort.bubbleSort(anotherArray, anotherArray.length);
        Assertions.assertArrayEquals(array, anotherArray);
    }

    @Test
    void insertionSort() {
        int[] anotherArray = Arrays.copyOf(array, array.length);
        MathUtil.shuffleArray(anotherArray);
        Sort.insertionSort(anotherArray, anotherArray.length);
        Assertions.assertArrayEquals(array, anotherArray);
    }

    @Test
    void selectionSort() {
        int[] anotherArray = Arrays.copyOf(array, array.length);
        MathUtil.shuffleArray(anotherArray);
        Sort.selectionSort(anotherArray, anotherArray.length);
        Assertions.assertArrayEquals(array, anotherArray);
    }
}
