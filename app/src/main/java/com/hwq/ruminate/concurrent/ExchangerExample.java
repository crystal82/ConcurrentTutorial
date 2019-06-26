package com.hwq.ruminate.concurrent;

import java.util.concurrent.Exchanger;

public class ExchangerExample {

    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();

        ExchangerRunnable exchangerRunnable1 =
                new ExchangerRunnable(exchanger, "Thread-0数据");

        ExchangerRunnable exchangerRunnable2 =
                new ExchangerRunnable(exchanger, "Thread-1数据");

        new Thread(exchangerRunnable1).start();
        new Thread(exchangerRunnable2).start();

    }

}

class ExchangerRunnable implements Runnable {

    Exchanger exchanger = null;
    Object object = null;

    ExchangerRunnable(Exchanger exchanger, Object object) {
        this.exchanger = exchanger;
        this.object = object;
    }

    @Override
    public void run() {
        try {
            Object previous = this.object;

            this.object = exchanger.exchange(this.object);

            System.out.println(
                    Thread.currentThread().getName() +
                            " exchanged " + previous + " for " + this.object
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}