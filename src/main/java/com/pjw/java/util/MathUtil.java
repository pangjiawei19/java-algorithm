package com.pjw.java.util;

/**
 * @author pangjiawei
 * @created 2020-12-29 22:18:58
 */
public class MathUtil {

    public static void shuffleArray(int[] array) {
        if (array.length < 2) {
            return;
        }

        for (int i = array.length - 1; i > 0; i--) {
            int randomIndex = (int) (Math.random() * (i + 1));
            if (i != randomIndex) {
                int tmp = array[i];
                array[i] = array[randomIndex];
                array[randomIndex] = tmp;
            }
        }
    }
}
