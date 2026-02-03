package org.client.learn.se;

import java.util.Objects;

public class MiniHashMap<K, V> {

    // ===== 节点（链表）=====
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    // ===== 核心字段 =====
    private Node<K, V>[] table;
    private int size;

    private static final int DEFAULT_CAP = 16;
    private static final float LOAD_FACTOR = 0.75f;

    public MiniHashMap() {
        table = new Node[DEFAULT_CAP];
    }

    // ===== hash + index =====
    private int index(K key) {
        int hash = Objects.hashCode(key);
        return hash & (table.length - 1); // 等价 %
    }

    // ===== put =====
    public void put(K key, V value) {

        if (size >= table.length * LOAD_FACTOR) {
            resize();
        }

        int i = index(key);

        Node<K, V> head = table[i];

        // 桶为空
        if (head == null) {
            table[i] = new Node<>(key, value);
            size++;
            return;
        }

        // 遍历链表
        Node<K, V> cur = head;
        while (true) {
            if (Objects.equals(cur.key, key)) {
                cur.value = value; // 覆盖
                return;
            }
            if (cur.next == null) break;
            cur = cur.next;
        }

        cur.next = new Node<>(key, value);
        size++;
    }

    // ===== get =====
    public V get(K key) {
        int i = index(key);

        Node<K, V> cur = table[i];

        while (cur != null) {
            if (Objects.equals(cur.key, key)) {
                return cur.value;
            }
            cur = cur.next;
        }
        return null;
    }

    // ===== 扩容 =====
    private void resize() {

        Node<K, V>[] old = table;
        table = new Node[old.length * 2];

        size = 0;

        for (Node<K, V> node : old) {
            while (node != null) {
                put(node.key, node.value); // 重新算 index
                node = node.next;
            }
        }
    }
}

