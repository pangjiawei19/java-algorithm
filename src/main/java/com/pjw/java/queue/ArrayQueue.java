package com.pjw.java.queue;

public class ArrayQueue<T> {

    protected Object[] array;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int length) {
        this.array = new Object[length];
    }

    public boolean isFull() {
        return tail == array.length;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public boolean enqueue(T obj) {
        if (isFull()) {
            return false;
        }
        array[tail] = obj;
        tail++;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        Object obj = array[head];
        head++;
        return (T) obj;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(4);

        for (int i = 0; i < 5; i++) {
            System.out.printf("enqueue %s, result: %s\n", i, queue.enqueue(i));
        }

        System.out.println("---------");

        while (!queue.isEmpty()) {
            System.out.printf("dequeue %s\n", queue.dequeue());
        }
    }
}
