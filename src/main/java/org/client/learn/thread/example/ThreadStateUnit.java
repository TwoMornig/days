package org.client.learn.thread.example;

public class ThreadStateUnit {
    public static void main(String[] args) throws InterruptedException {
        // 创建线程
        Thread t = new Thread(() -> {
            System.out.println("线程状态1: " + Thread.currentThread().getState()); // RUNNABLE

            try {
                // 线程休眠，进入TIMED_WAITING状态
                Thread.sleep(1000);
                System.out.println("线程状态2: " + Thread.currentThread().getState());

                // 同步块，可能进入BLOCKED状态
                synchronized (ThreadStateUnit.class) {
                    System.out.println("线程获得锁");
                }

                // 线程等待，进入WAITING状态
                synchronized (ThreadStateUnit.class) {
                    ThreadStateUnit.class.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程状态3: " + Thread.currentThread().getState()); // RUNNABLE
        });

        System.out.println("线程状态0: " + t.getState()); // NEW

        // 启动线程
        t.start();
        System.out.println("线程状态4: " + t.getState()); // RUNNABLE或TIMED_WAITING

        // 主线程休眠
        Thread.sleep(2000);
        System.out.println("线程状态5: " + t.getState()); // 可能是WAITING或TERMINATED

        // 唤醒等待的线程
        synchronized (ThreadStateUnit.class) {
            ThreadStateUnit.class.notify();
        }

        // 等待线程执行完毕
        t.join();
        System.out.println("线程状态6: " + t.getState()); // TERMINATED
    }
}
