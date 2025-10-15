package org.client.learn.thread;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ThreadSync {

    private static volatile boolean initFlag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            log.info("waiting data");
            while (!initFlag) {

            }
            log.info("wating success");
        }).start();
        Thread.sleep(2000);
        new Thread(() -> prepareData()).start();
    }

    public static void prepareData() {
        log.info("pre data start");
        initFlag = true;
        log.info("pre data end");
    }

}
//NOTE Thread 缓存一致性 主线嗅探机制  从 线程内存 和 主内存之间的数据同步问题