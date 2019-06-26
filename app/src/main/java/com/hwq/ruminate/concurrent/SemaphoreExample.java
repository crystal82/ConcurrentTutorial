package com.hwq.ruminate.concurrent;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    public static void main(String[]args){
        Semaphore semaphore = new Semaphore(3);

        new Thread(new ThreadSemaphore(semaphore)).start();
        new Thread(new ThreadSemaphore(semaphore)).start();
        new Thread(new ThreadSemaphore(semaphore)).start();
        new Thread(new ThreadSemaphore(semaphore)).start();
        new Thread(new ThreadSemaphore(semaphore)).start();

    }

}

class ThreadSemaphore implements Runnable{

    private final Semaphore semaphore;

    ThreadSemaphore(Semaphore semaphore){
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+"获取到锁进来");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
        System.out.println(Thread.currentThread().getName()+"释放锁");

    }
}