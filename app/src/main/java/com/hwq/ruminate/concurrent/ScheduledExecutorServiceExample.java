package com.hwq.ruminate.concurrent;

import java.util.concurrent.*;

public class ScheduledExecutorServiceExample {

    public static void main(String[] args) {
        scheduleAtFixedRate();

        //scheduleWithFixedDelay();
    }

    private static void schedule() {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(5);
        ScheduledFuture<Object> schedule = scheduledExecutorService.schedule(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("Executed!");
                return "Called!";
            }
        }, 1, TimeUnit.SECONDS);

        try {
            System.out.println(schedule.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //schedule.cancel();
        scheduledExecutorService.shutdown();
    }

    private static void scheduleAtFixedRate() {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("run " + System.currentTimeMillis());
            }
        }, 0, 100, TimeUnit.MILLISECONDS);

        executorService.schedule(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                executorService.shutdown();
                return "Called!";
            }
        }, 5, TimeUnit.SECONDS);
    }

    private static void scheduleWithFixedDelay() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        System.out.println("          x = " + System.currentTimeMillis());
        executorService.scheduleWithFixedDelay(new MyRunnable(), 1, 2, TimeUnit.SECONDS);
        System.out.println("          y = " + System.currentTimeMillis());
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("     begin = " + System.currentTimeMillis() + ", name: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(4);
                System.out.println("     end = " + System.currentTimeMillis() + ", name: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
