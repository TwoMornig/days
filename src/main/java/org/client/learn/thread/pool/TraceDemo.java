package org.client.learn.thread.pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TraceDemo {

    static ExecutorService pool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        MiniMDC.put("traceId", "TRACE-10086");

        CompletableFuture.runAsync(
                new MiniTTL().wrap(() -> {
                    log("step1");

                    CompletableFuture.runAsync(
                            new MiniTTL().wrap(() -> log("step2")),
                            pool
                    );
                }),
                pool
        );
    }

    static void log(String msg) {
        System.out.println(
                Thread.currentThread().getName()
                        + " trace=" + MiniMDC.get("traceId")
                        + " " + msg
        );
    }
}
