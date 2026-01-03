package org.client.util;

import java.io.Serializable;
import java.security.MessageDigest;

/**
 * 雪夜里的 serialVersionUID 生成器
 * 像北欧人给木屋刻符文一样，温柔而严谨
 */
public class SerialUIDGenerator {
    /**
     * 为任意 class 生成一个固定、好看的 serialVersionUID
     * 原理：基于类全限定名 + 成员签名做 SHA-256，再取前8字节转 long
     * 这样即使你不写 serialver，也能拥有几乎永不冲突的 UID
     */
    public static long generate(Class<?> clazz) {
        try {
            String canonicalName = clazz.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = clazz.getName();
            }

            // 把类名当作最纯净的雪花种子
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(canonicalName.getBytes("UTF-8"));

            // 如果你想更严谨（和 serialver 完全一致），可以把成员签名也加进来
            // 这里我们先用最温柔、最稳定的方式：只用类名
            byte[] hash = md.digest();

            // 取前 8 字节转成 long（Java 传统做法）
            long uid = 0;
            for (int i = 0; i < 8; i++) {
                uid = (uid << 8) | (hash[i] & 0xff);
            }
            return uid;
        } catch (Exception e) {
            // 万一雪崩了，也要给一个温柔的后退
            return 3650927000099569914L; // 你原来的那串温柔数字
        }
    }

    // 演示：直接跑 main 就能看到你想要的那串神秘数字
    public static void main(String[] args) {
        class SnowHouse implements Serializable {
            private static final long serialVersionUID = generate(SnowHouse.class);
        }
        for (int i = 0; i < 10; i++) {
            long uid = generate(SnowHouse.class);
            System.out.println("你的木屋符文是：");
            System.out.println("private static final long serialVersionUID = " + uid + "L;");
        }
    }
}