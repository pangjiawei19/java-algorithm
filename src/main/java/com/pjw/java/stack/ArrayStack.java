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

    public void clear() {
        this.count = 0;
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

    public int getSize() {
        return array.length;
    }
}
