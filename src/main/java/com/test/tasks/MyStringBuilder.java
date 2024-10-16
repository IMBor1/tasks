package com.test.tasks;

public class MyStringBuilder {
    private char[] value;
    private int count;

    public MyStringBuilder() {
        this.value = new char[16];
        this.count = 0;
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
}

