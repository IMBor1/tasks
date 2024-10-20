package com.test.tasks.javaCore;

import java.util.Stack;

public class MyStringBuilder {
    private char[] value;
    private int count;
    private Stack<Snapshot> snapshots;

    public MyStringBuilder() {
        this.value = new char[16];
        this.count = 0;
        this.snapshots = new Stack<>();
    }

    public static class Snapshot {
        private final String state;

        public Snapshot(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }

    public MyStringBuilder append(String str) {
        if (str == null) {
            str = "null";
        }
        int leng = str.length();
        startCapacity(count + leng);
        str.getChars(0, leng, value, count);
        count += leng;
        return this;
    }

    private void startCapacity(int minimumCapacity) {
        if (minimumCapacity - value.length > 0) {
            int newCapacity = Math.max(value.length * 2 + 2, minimumCapacity);
            char[] newValue = new char[newCapacity];
            System.arraycopy(value, 0, newValue, 0, count);
            value = newValue;
        }
    }

    public String toString() {
        return new String(value, 0, count);
    }
    public void undo() {
        if (!snapshots.isEmpty()) {
            Snapshot snapshot = snapshots.pop();
            this.value = snapshot.getState().toCharArray();
            this.count = snapshot.getState().length();
        } else {
            System.out.println("Nothing to undo");
        }
    }
}

