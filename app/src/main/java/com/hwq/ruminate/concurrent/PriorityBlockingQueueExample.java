package com.hwq.ruminate.concurrent;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueExample {

    public static void main(String[] args) throws Exception {


        PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<Task>();

        Task t1 = new Task();
        t1.setId(3);
        t1.setName("id为3");
        Task t2 = new Task();
        t2.setId(4);
        t2.setName("id为4");
        Task t3 = new Task();
        t3.setId(1);
        t3.setName("id为1");

        q.add(t1);
        q.add(t2);
        q.add(t3);

        System.out.println("容器：" + q);
        System.out.println(q.take().getId());
        System.out.println("容器：" + q);

    }
}

class Task implements Comparable<Task> {
    private int id;
    private String name;

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Task task) {
        return Integer.compare(this.id, task.id);
    }

    @Override
    public String toString() {
        return this.id + "," + this.name;
    }
}