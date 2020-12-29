package com.pjw.java.queue;

/**
 * @author pangjiawei
 * @created 2020-12-29 12:09:15
 */
public class CircularArrayQueue<T> extends ArrayQueue<T> {

    public CircularArrayQueue(int length) {
        super(length);
    }

    @Override
    public boolean isFull() {
        return (tail + 1) % array.length == head;
    }

    @Override
    public boolean enqueue(T obj) {
        if (isFull()) {
            return false;
        }
        array[tail] = obj;
        tail = (tail + 1) % array.length;
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        Object obj = array[head];
        head = (head + 1) % array.length;
        return (T) obj;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new CircularArrayQueue<>(4);

        for (int i = 0; i < 5; i++) {
            System.out.printf("enqueue %s, result: %s\n", i, queue.enqueue(i));
        }

        System.out.println("---------");

        while (!queue.isEmpty()) {
            System.out.printf("dequeue %s\n", queue.dequeue());
        }

        System.out.println("---------");

        for (int i = 0; i < 5; i++) {
            System.out.printf("enqueue again %s, result: %s\n", i, queue.enqueue(i));
        }
    }
}
