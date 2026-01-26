package org.client.learn.thread.example;

/**
 * 死锁demo
 */
public class JoinDeadlockDetect {
    private static Thread t1;
    private static Thread t2;

    public static void main(String[] args) throws Exception {
        t1 = new Thread(() -> {
            try {
                System.out.println("t1: 等待 t2 结束");
                t2.join(); // t1 等 t2
                System.out.println("t1: t2 结束了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-1");

        t2 = new Thread(() -> {
            try {
                System.out.println("t2: 等待 t1 结束");
                t1.join(); // t2 等 t1
                System.out.println("t2: t1 结束了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-2");

        t1.start();
        t2.start();

        // 等 5 秒，检查线程状态
        Thread.sleep(5000);

        System.out.println("\n==== 死锁检测 ====");
        System.out.println("t1 状态: " + t1.getState());
        System.out.println("t2 状态: " + t2.getState());

        // 打印堆栈，看看他们卡在哪里
        for (Thread t : new Thread[]{t1, t2}) {
            for (StackTraceElement ste : t.getStackTrace()) {
                System.out.println(t.getName() + " -> " + ste);
            }
            System.out.println();
        }
    }
}
