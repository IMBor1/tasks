package com.test.tasks.cuncurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private final int numberOfTasks;
    private final ExecutorService executorService;
    private final CyclicBarrier barrier;

    public ComplexTaskExecutor(int numberOfTasks, ExecutorService executorService, CyclicBarrier barrier) {
        this.numberOfTasks = numberOfTasks;
        this.executorService = Executors.newFixedThreadPool(numberOfTasks);
        this.barrier = new CyclicBarrier(numberOfTasks,() -> {
            System.out.println("Все задачи выполнены. Сложение результатов");
        });
    }

    public void executeTasks(int numberOfTasks) {
        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < numberOfTasks; i++) {
            int finalI = i;
            futures.add(executorService.submit(() -> {
                String result = new ComplexTask(finalI).call();
                System.out.println(Thread.currentThread().getName() + " completed " + result);
                barrier.await();
                return result;
            }));
        }

        for (Future<String> future : futures) {
            try {
                String result = future.get();
                System.out.println("Суммарный результат: " + result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }
}
