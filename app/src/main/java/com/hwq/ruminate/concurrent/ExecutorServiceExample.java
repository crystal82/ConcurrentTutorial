package com.hwq.ruminate.concurrent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class ExecutorServiceExample {

    public static void main(String[] args) {
        //submitCallTest();
        //invokeAnyTest();
        //invokeAllTest();
    }

    private static void executeTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            final int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "  Asynchronous task " + finalI);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("------" + Thread.currentThread().getName() + "  Task End -----" + finalI);
                }
            });
        }
        executorService.shutdownNow();
    }

    private static void submitTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Asynchronous task");
            }
        });
        //returns null if the task has finished correctly.
        try {
            Object o = future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    private static void submitCallTest() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Object> future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("Asynchronous Callable");
                return "Callable Result";
            }
        });

        try {
            System.out.println("future.get() = " + future.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void invokeAnyTest() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Set<Callable<String>> set = new HashSet<>();
        set.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 1";
            }
        });
        set.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 2";
            }
        });
        set.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 3";
            }
        });
        set.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 4";
            }
        });
        String result = null;
        try {
            result = executorService.invokeAny(set);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result = " + result);
        executorService.shutdown();
    }

    private static void invokeAllTest() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Set<Callable<String>> set = new HashSet<>();
        set.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 1";
            }
        });

        set.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 2";
            }
        });
        set.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 3";
            }
        });

        List<Future<String>> list = null;
        try {
            list = executorService.invokeAll(set);
            for (Future<String> future : list) {
                System.out.println("result = " + future.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
