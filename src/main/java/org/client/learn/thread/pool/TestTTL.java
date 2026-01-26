package org.client.learn.thread.pool;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestTTL {

    static ExecutorService pool = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {

            String traceId = UUID.randomUUID().toString();

            MiniMDC.put("traceId", traceId);

            pool.submit(new MiniTTL().wrap(() -> {
                System.out.println(Thread.currentThread().getName() + " trace=" + MiniMDC.get("traceId"));
            }));
        }

        pool.shutdown();
    }
}
