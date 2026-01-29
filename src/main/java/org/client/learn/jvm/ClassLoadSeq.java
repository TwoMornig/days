package org.client.learn.jvm;

public class ClassLoadSeq {
    public static void main(String[] args) {
        System.out.println(Test.str);
    }

    public static class Test {
        static {
            System.out.println("init start");
        }

        public static String str = "static str";
        //NOTE final 不会加载类 会在启动的时候 直接替换字符串
        // public static final String str = "final static str";
    }

    //NOTE 类加载流程
    // new 一定会加载
    // 使用其它类的静态成员 final除外
    // 反射 会加载
    // 调用子类信息
    // 加载接口的实现类 且接口有default默认方法

    //TODO 数组的定义会不会类加载
}
