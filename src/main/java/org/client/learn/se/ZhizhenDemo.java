package org.client.learn.se;

public class ZhizhenDemo {

    public static void main(String[] args) {
        A a = new A();
        a.b = 2;
        // 最常用、最可靠的方式
        System.out.println("t1 address: " + Integer.toHexString(System.identityHashCode(a)));
        System.out.println("t1 value: " + a.b);
        changeB(a);
        System.out.println("t4 address: " + Integer.toHexString(System.identityHashCode(a)));
        System.out.println("t4 value: " + a.b);
    }

    private static void changeB(A b) {
        // 最常用、最可靠的方式
        System.out.println("t2 address: " + Integer.toHexString(System.identityHashCode(b)));
        System.out.println("t2 value: " + b.b);
        b.b = 4;
        b = new A();
        b.b = 3;
        System.out.println("t3 address: " + Integer.toHexString(System.identityHashCode(b)));
        System.out.println("t3 value: " + b.b);
    }

    static class A {
        int b = 0;
    }
}
