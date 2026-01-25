package org.client.learn.thread;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * cqs aba问题
 */
public class CqsAbaDemo {
    private static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0); // 初始值 A，版本号 0

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            int[] stampHolder = new int[1];
            String oldValue = ref.get(stampHolder);
            int oldStamp = stampHolder[0];
            System.out.println("👤 线程1 拿到值=" + oldValue + "，版本=" + oldStamp);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            boolean success = ref.compareAndSet(oldValue, "C", oldStamp, oldStamp + 1);
            System.out.println("👤 线程1 CAS 是否成功？" + success + "，当前值：" + ref.getReference() + "，版本：" + ref.getStamp());
        });

        Thread t2 = new Thread(() -> {
            int stamp = ref.getStamp();
            ref.compareAndSet("A", "B", stamp, stamp + 1);
            System.out.println("👤 线程2 改成 B，版本=" + ref.getStamp());

            ref.compareAndSet("B", "A", ref.getStamp(), ref.getStamp() + 1);
            System.out.println("👤 线程2 又改回 A，版本=" + ref.getStamp());
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
