package com.pjw.java.stack;

public class ArrayStack<T> {

    protected Object[] array;
    private int count;

    public ArrayStack(int length) {
        this.array = new Object[length];
        this.count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == array.length;
    }

    public boolean push(T obj) {
        if (isFull()) {
            return false;
        }
        array[count] = obj;
        count++;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (this.isEmpty()) {
            return null;
        }
        Object obj = array[count - 1];
        count--;
        return (T) obj;
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>(5);

        for (int i = 0; i < 6; i++) {
            System.out.printf("push %s, result: %s\n", i, stack.push(i));
        }

        System.out.println("---------");

        while (!stack.isEmpty()) {
            System.out.printf("pop %s\n", stack.pop());
        }
    }
}
