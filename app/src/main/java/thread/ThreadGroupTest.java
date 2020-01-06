package thread;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ThreadGroupTest {

    public static class MyThread implements Runnable {

        @Override
        public void run() {
           //try {
           //    System.out.println(Thread.currentThread().getName() + " -> start");
           //    TimeUnit.SECONDS.sleep(10);
           //    //随机发生异常
           //    //if (ThreadLocalRandom.current().nextInt(10) > 5) {
           //    //    throw new RuntimeException(Thread.currentThread().getName() + "发生异常");
           //    //}
           //    System.out.println(Thread.currentThread().getName() + " -> end");
           //} catch (InterruptedException e) {
           //    e.printStackTrace();
           //}
        }
    }

    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("MyGroup");

        for (int i = 0; i <= 10; i++) {
            new Thread(threadGroup, new MyThread(), "Thread " + i).start();
        }
        System.out.println("activeCount:" + threadGroup.activeCount());


        Thread[] threads = new Thread[10];
        int enumerate = threadGroup.enumerate(threads);
        System.out.println("enumerate:" + enumerate);
        System.out.println("activeCount:" + threadGroup.activeCount());

        for (int i = 0; i <= threadGroup.activeCount(); i++) {
            System.out.println("thread:" + threads[i].getName());
        }
    }
}