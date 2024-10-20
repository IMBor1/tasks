package com.test.tasks.cuncurrency;


import java.util.concurrent.Callable;

public class ComplexTask implements Callable<String> {
    private final int taskId;

    public ComplexTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep((long) (Math.random() * 1000));
         return "Результат задачи " + taskId;
    }
}
