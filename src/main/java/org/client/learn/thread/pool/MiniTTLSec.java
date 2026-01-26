package org.client.learn.thread.pool;

import java.util.Map;

public class MiniTTLSec {

    // ⭐ 包装 Runnable
    public static Runnable wrap(Runnable task) {

        // 1️⃣ 捕获父线程 MDC
        Map<String, String> captured = MiniMDC.copy();

        return () -> {

            // 2️⃣ 备份子线程旧值
            Map<String, String> backup = MiniMDC.copy();

            try {
                // 3️⃣ 设置父线程值
                MiniMDC.set(captured);

                task.run();

            } finally {
                // 4️⃣ 恢复旧值（关键）
                MiniMDC.set(backup);
            }
        };
    }
}
