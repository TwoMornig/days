package org.client.learn.thread.example;

/**
 * 定时任务线程池
 */
public class ScheduledThreadPoolDemo {
//    public static void main(String[] args) {
//        // 1. 创建一个定时任务线程池（核心线程数 = 2）
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
//
//        // 2. 延迟执行任务（延迟 2 秒后只执行一次）
//        scheduler.schedule(() -> {
//            System.out.println("延迟任务执行: " + Thread.currentThread().getName());
//        }, 2, TimeUnit.SECONDS);
//
//        // 3. 固定周期执行任务（延迟 1 秒后，每隔 3 秒执行一次）
//        scheduler.scheduleAtFixedRate(() -> {
//            System.out.println("固定周期任务执行: " + Thread.currentThread().getName() + " 时间: " + System.currentTimeMillis());
//        }, 1, 3, TimeUnit.SECONDS);
//
//        // 4. 固定延迟执行任务（前一个任务结束后，延迟 2 秒再执行）
//        scheduler.scheduleWithFixedDelay(() -> {
//            System.out.println("固定延迟任务执行: " + Thread.currentThread().getName() + " 时间: " + System.currentTimeMillis());
//            try {
//                Thread.sleep(2000); // 模拟任务耗时
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }, 1, 2, TimeUnit.SECONDS);
//
//        // ⚠️ 为了方便观察，这里不关闭线程池
//        // 如果要在实际项目中使用，可以在合适时机调用 scheduler.shutdown();
//    }
}
