package org.client.learn.thread;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ThreadSync {

    private static volatile boolean initFlag = false;
    private static Integer a = 1;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            log.info("waiting data");
            while (!initFlag) {

            }
            System.out.println(a);
            log.info("wating success");
        }).start();
        Thread.sleep(2000);
        new Thread(() -> prepareData()).start();
    }

    public static void prepareData() {
        log.info("pre data start");
        initFlag = true;  // volatile 写
        try {
            Thread.sleep(1); // 模拟执行延迟，制造机会
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("pre data a");
        a = 2;  // 非 volatile 写
        log.info("pre data end");
    }
}
//NOTE Thread 缓存一致性(MESI modify exclusive shared invalid) 主线嗅探机制  从 线程内存 和 主内存之间的数据同步问题