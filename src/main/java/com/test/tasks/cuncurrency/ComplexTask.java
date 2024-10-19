package com.test.tasks.cuncurrency;

public class ComplexTask {
    private Long num1 = 100L;
    private Long num2 = 150L;

    private Long count1 = 0L;
    public Long count2 = 0L;

    public Long countNum1(Long num1) {
        new Thread()
        for (int i = 0; i < num1; i++) {
            count1 += i;
        } return count1;
    }
    public Long countNum2(Long num2) {
        for (int i = 0; i < num2; i++) {
            count2 += i;
        } return count2;
    }
}
