package org.client.learn.jvm;

public class FinalizeOverWrite {

    private static Test a;

    public static void main(String[] args) throws InterruptedException {
        a = new Test();
        //这里设置a为null 获取不到new的的UI小
        a = null;
        //手动申请gc 一般情况下会执行
        System.gc();
        //等待gc执行
        Thread.sleep(1000);

        System.out.println(a);
    }

    private static class Test {
        @Override
        protected void finalize() throws Throwable {
            System.out.println(this + "自救之路");
            System.out.println(Thread.currentThread());
            a = this;
        }
    }
}
