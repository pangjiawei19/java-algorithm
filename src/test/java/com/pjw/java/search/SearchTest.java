package com.pjw.java.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author pangjiawei
 * @created 2021-01-07 18:32:00
 */
public class SearchTest {

    private final int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    @Test
    void binarySearch() {
        int index1 = Search.binarySearch(array, array.length, 5);
        int index2 = Search.binarySearch(array, array.length, 15);
        Assertions.assertEquals(index1, 4);
        Assertions.assertEquals(index2, -1);
    }
}
