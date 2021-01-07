package com.pjw.java.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author pangjiawei
 * @created 2020-12-29 17:28:48
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArrayStackTest {

    public static final int STACK_SIZE = 5;
    public static final int DYNAMIC_STACK_SIZE = 20;
    private static final ArrayStack<Integer> stack = new ArrayStack<>(STACK_SIZE);
    private static final ArrayStack<Integer> dynamicStack = new DynamicArrayStack<>();

    @BeforeEach
    void prepare() {

    }

    @Test
    @Order(1)
    void testPush() {
        for (int i = 0; i < 10; i++) {
            boolean result = stack.push(i);
            assertEquals(result, i < STACK_SIZE);
        }
    }

    @Test
    @Order(2)
    void testPop() {
        for (int i = 0; i < 10; i++) {
            Integer num = stack.pop();
            if (i < STACK_SIZE) {
                assertEquals(i, STACK_SIZE - num - 1);
            } else {
                assertNull(num);
            }
        }
    }

    @Test
    @Order(3)
    void testDynamicPush() {
        for (int i = 0; i < DYNAMIC_STACK_SIZE; i++) {
            boolean result = dynamicStack.push(i);
            assertEquals(result, true);
        }
    }

    @Test
    @Order(4)
    void testDynamicPop() {
        for (int i = 0; i < DYNAMIC_STACK_SIZE; i++) {
            Integer num = dynamicStack.pop();
            assertEquals(i, DYNAMIC_STACK_SIZE - num - 1);
        }
    }
}
