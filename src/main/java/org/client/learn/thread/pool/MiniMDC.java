package org.client.learn.thread.pool;

import java.util.HashMap;
import java.util.Map;

public class MiniMDC {

    // ⭐ 每个线程一份 map
    private static final ThreadLocal<Map<String, String>> LOCAL =
            ThreadLocal.withInitial(HashMap::new);

    public static void put(String key, String value) {
        LOCAL.get().put(key, value);
    }

    public static String get(String key) {
        return LOCAL.get().get(key);
    }

    public static void remove(String key) {
        LOCAL.get().remove(key);
    }

    public static void clear() {
        LOCAL.remove();
    }

    // ⭐ 给 TTL 用
    public static Map<String, String> copy() {
        return new HashMap<>(LOCAL.get());
    }

    public static void set(Map<String, String> map) {
        LOCAL.set(new HashMap<>(map));
    }
}
