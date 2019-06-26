package com.hwq.ruminate.concurrent;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample {

    private static SimpleDateFormat time = new SimpleDateFormat("hh:MM:ss:SSSXXX");

    public static void main(String[] args) throws InterruptedException {

        DelayQueue<DelayedElement> queue = new DelayQueue<>();
        DelayedElement element1 = new DelayedElement(2000);
        DelayedElement element2 = new DelayedElement(0);
        DelayedElement element3 = new DelayedElement(400);
        DelayedElement element4 = new DelayedElement(-100);
        queue.put(element1);
        queue.put(element2);
        queue.put(element3);
        queue.put(element4);
        DelayedElement e = queue.take();
        System.out.println(time.format(new Date()) + "  e1:" + e.delayTime);
        DelayedElement e2 = queue.take();
        System.out.println(time.format(new Date()) + "  e2:" + e2.delayTime);
        DelayedElement e3 = queue.take();
        System.out.println(time.format(new Date()) + "  e3:" + e3.delayTime);
        DelayedElement e4 = queue.take();
        System.out.println(time.format(new Date()) + "  e4:" + e4.delayTime);
    }
}

class DelayedElement implements Delayed {
    long delayTime;
    long tamp;

    DelayedElement(long delay) {
        delayTime = delay;
        tamp = delay + System.currentTimeMillis();
    }

    @Override
    public long getDelay(@NonNull TimeUnit unit) {
        return tamp - System.currentTimeMillis();
//        return -1;
    }

    @Override
    public int compareTo(@NonNull Delayed o) {
        return tamp - ((DelayedElement) o).tamp > 0 ? 1 : -1;
    }
}