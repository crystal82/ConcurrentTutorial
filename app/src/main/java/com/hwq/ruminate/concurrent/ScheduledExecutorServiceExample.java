package com.hwq.ruminate.concurrent;

import java.util.concurrent.*;

public class ScheduledExecutorServiceExample {

    public static void main(String[] args) {
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
}
