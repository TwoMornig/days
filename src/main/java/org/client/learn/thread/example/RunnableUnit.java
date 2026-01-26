package org.client.learn.thread.example;

public class RunnableUnit {
    public static void main(String[] args) {
        Runnable task = () -> System.out.println("Hello from Runnable: " + Thread.currentThread().getName());
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
    }
}
//NOTE      是什么：Runnable 是一个函数式接口，只有 run() 方法。
//NOTE      怎么用：实现 Runnable，然后丢给 Thread 来执行。
//NOTE      关键点：
//NOTE          解耦：任务和线程分离，灵活。
//NOTE          可用 Lambda 简化写法。