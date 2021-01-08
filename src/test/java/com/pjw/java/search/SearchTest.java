package com.pjw.java.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author pangjiawei
 * @created 2021-01-07 18:32:00
 */
public class SearchTest {

    private final int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private final int[] array2 = {1, 2, 3, 4, 4, 4, 4, 8, 9, 10};

    @Test
    void binarySearch() {
        int index1 = Search.binarySearch(array, array.length, 5);
        int index2 = Search.binarySearch(array, array.length, 15);
        Assertions.assertEquals(index1, 4);
        Assertions.assertEquals(index2, -1);
    }

    @Test
    void binarySearchFirst() {
        int index1 = Search.binarySearch(array2, array2.length, 4);
        int index2 = Search.binarySearchFirst(array2, array2.length, 4);
        int index3 = Search.binarySearchFirst(array2, array2.length, 15);
        Assertions.assertNotEquals(index1, 3);
        Assertions.assertEquals(index2, 3);
        Assertions.assertEquals(index3, -1);
    }

    @Test
    void binarySearchFinal() {
        int index1 = Search.binarySearch(array2, array2.length, 4);
        int index2 = Search.binarySearchFinal(array2, array2.length, 4);
        int index3 = Search.binarySearchFinal(array2, array2.length, 15);
        Assertions.assertNotEquals(index1, 6);
        Assertions.assertEquals(index2, 6);
        Assertions.assertEquals(index3, -1);
    }

    @Test
    void binarySearchFirstEqualOrGreater() {
        int index1 = Search.binarySearchFirstEqualOrGreater(array2, array2.length,4);
        int index2 = Search.binarySearchFirstEqualOrGreater(array2, array2.length,5);
        Assertions.assertEquals(index1, 3);
        Assertions.assertEquals(index2, 7);
    }

    @Test
    void binarySearchFinalEqualOrLess() {
        int index2 = Search.binarySearchFinalEqualOrLess(array2, array2.length, 4);
        int index3 = Search.binarySearchFinalEqualOrLess(array2, array2.length, 5);
        Assertions.assertEquals(index2, 6);
        Assertions.assertEquals(index3, 6);
    }
}
