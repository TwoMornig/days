package org.client.learn.jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class SoftRefDemo {
    public static void main(String[] args) {
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        SoftReference<Object> reference = new SoftReference<>(new Object(), queue);
        System.out.println(reference);

        try {
            List<String> list = new ArrayList<>();
            while (true) list.add("lbwnb");
        } catch (Exception e) {
            System.out.println("内存溢出" + e.getMessage());
            System.out.println("软引用对象" + reference.get());
            System.out.println(queue.poll());
        }
    }
}
