package com.pjw.java.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author pangjiawei
 * @created 2020-12-29 22:19:40
 */
public class MathUtilTest {

    @Test
    void shuffleArray() {
        for (int i = 0; i < 10; i++) {
            int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
            String text1 = Arrays.toString(array);
            MathUtil.shuffleArray(array);
            String text2 = Arrays.toString(array);
            Assertions.assertNotEquals(text1, text2);
        }
    }
}
