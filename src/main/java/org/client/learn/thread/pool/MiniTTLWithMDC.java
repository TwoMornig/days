package org.client.learn.thread.pool;

import org.slf4j.MDC;

import java.util.UUID;

public class MiniTTLWithMDC {

    public static void main(String[] args) {

        Runnable task = () -> {

            try {
                String traceId = UUID.randomUUID().toString();

                // ⭐ 相当于 ThreadLocal.set()
                MDC.put("traceId", traceId);

                log("start work");

                sleep();

                log("finish work");

            } finally {
                // ⭐ 一定清理（线程池必须）
                MDC.clear();
            }
        };

        new Thread(task, "T-A").start();
        new Thread(task, "T-B").start();
    }

    private static void log(String msg) {
        System.out.println(Thread.currentThread().getName() + " traceId=" + MDC.get("traceId") + " " + msg);
    }

    private static void sleep() {
        try {
            Thread.sleep(500);
        } catch (Exception ignored) {
        }
    }
}
