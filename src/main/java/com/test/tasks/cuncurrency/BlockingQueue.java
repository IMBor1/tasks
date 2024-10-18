package com.test.tasks.cuncurrency;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {
    private Queue<Object> queue;
    private int maxSize;
    public BlockingQueue(int size) {
        this.queue = new LinkedList<>();
        this.maxSize = size;
    }
    public synchronized void enqueue(Object item) throws InterruptedException {
        while (queue.size() == maxSize) {
            wait();
        }
        queue.add(item);
        notifyAll();
    }

    public synchronized Object dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        Object item = queue.poll();
        notifyAll();
        return item;
    }

    public synchronized int sizeQueue() {
        return queue.size();
    }
}
