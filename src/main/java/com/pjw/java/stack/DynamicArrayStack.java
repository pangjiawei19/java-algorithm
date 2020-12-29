package com.pjw.java.stack;

public class DynamicArrayStack<T> extends ArrayStack<T> {

    public DynamicArrayStack() {
        super(1 << 3);
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean push(T obj) {
        if (super.isFull()) {
            Object[] newArray = new Object[(int) (array.length * 1.5)];
            System.arraycopy(array, 0, newArray, 0, array.length);
            this.array = newArray;
        }
        return super.push(obj);
    }


    public static void main(String[] args) {
        ArrayStack<Integer> stack = new DynamicArrayStack<>();

        for (int i = 0; i < 20; i++) {
            System.out.printf("push %s, result: %s\n", i, stack.push(i));
        }

        System.out.println("---------");

        while (!stack.isEmpty()) {
            System.out.printf("pop %s\n", stack.pop());
        }
    }
}
