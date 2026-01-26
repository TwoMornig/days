package org.client.learn.thread.example;

public class ThreadUnit {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello from Thread: " + Thread.currentThread().getName());
    }
}

//NOTE 为什么 thread  一个线程对象只能调用一次 start()。

//NOTE 想象你在电影院拿了一张电影票（Thread 对象），
//    第一次进场（start()）可以顺利看完一场电影（run()）。
//    出场后，这张票就作废了（TERMINATED）。
//    你不能用同一张票再进场看第二场。想看新的电影，就得买一张新票（重新 new Thread()）。

//NOTE 怎么用：继承 Thread，重写 run() 方法，调用 start() 开启新线程。
//  关键点：
//    run() 方法只是普通方法，直接调用不会启动新线程，必须用 start()。
//    一个线程对象只能调用一次 start()。