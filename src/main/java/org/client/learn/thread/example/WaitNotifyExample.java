package org.client.learn.thread.example;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

@Slf4j
public class WaitNotifyExample {
    private final Queue<String> queue = new LinkedList<>();
    private final int MAX_SIZE = 5;

    public synchronized void produce(String data) throws InterruptedException {
        // 使用while循环检查条件，防止虚假唤醒
        while (queue.size() == MAX_SIZE) {
            log.info("队列已满，生产者等待...");
            this.wait(); // 队列满了，生产者线程等待
        }

        queue.add(data);
        log.info("生产数据: " + data + ", 当前队列大小: " + queue.size());

        // 只通知消费者线程，避免不必要的唤醒
        this.notify(); // 在单生产者单消费者的情况下可以用notify提高效率
    }

    public synchronized String consume() throws InterruptedException {
        // 使用while循环检查条件，防止虚假唤醒
        while (queue.isEmpty()) {
            log.info("队列为空，消费者等待...");
            this.wait(); // 队列空了，消费者线程等待
        }

        String data = queue.poll();
        log.info("消费数据: " + data + ", 当前队列大小: " + queue.size());

        // 只通知生产者线程，避免不必要的唤醒
        this.notify(); // 在单生产者单消费者的情况下可以用notify提高效率
        return data;
    }

    // 对于多生产者多消费者的场景，应改用notifyAll避免线程饥饿
    public synchronized void produceMulti(String data) throws InterruptedException {
        while (queue.size() == MAX_SIZE) {
            log.info(Thread.currentThread().getName() + ": 队列已满，生产者等待...");
            this.wait();
        }

        queue.add(data);
        log.info(Thread.currentThread().getName() + ": 生产数据: " + data + ", 当前队列大小: " + queue.size());

        // 当有多个生产者和消费者时，必须用notifyAll确保正确唤醒
        this.notifyAll();
    }

    public static void main(String[] args) {
        WaitNotifyExample example = new WaitNotifyExample();

        // 创建生产者线程
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    example.produce("数据-" + i);
                    Thread.sleep(new Random().nextInt(1000));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 创建消费者线程
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    example.consume();
                    Thread.sleep(new Random().nextInt(1000));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}