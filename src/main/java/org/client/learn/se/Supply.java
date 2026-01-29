package org.client.learn.se;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Consumer：吃掉数据（消费）
 * Function：变形数据（转换）
 * Predicate：审判数据（判断）
 * Supplier：凭空造物（生产）
 */
public class Supply {

    public static void main(String[] args) {
        Student student = new Student();
        student.setNameByValue(() -> {
            return "set name";
        });

        //NOTE consumer 形变
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        };
        consumer = obi -> {
            System.out.println(obi);
        };
        consumer = i -> System.out.println(i);
        consumer = System.out::println;
        consumer = consumer.andThen(i -> System.out.println(i + " go on"));
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().forEach(consumer);


        Function<Integer, String> function = new Function<Integer, String>() {
            @Override
            public String apply(Integer i) {
                return "数字:" + i;
            }
        };
        function = i -> "numbers:" + i;
        System.out.println(function.apply(10));


        Predicate<Integer> predicate = i -> i % 2 == 0;
        System.out.println(predicate.test(4));
        list.stream().filter(i -> i % 2 == 0).forEach(System.out::println);
        list.stream().filter(predicate).forEach(System.out::println);

        Predicate<Integer> gt3 = i -> i > 3;
        Predicate<Integer> even2 = i -> i % 2 == 0;

        list.stream().filter(gt3.and(even2)).forEach(System.out::println);


        //NOTE Predicate 过滤
        Predicate<Integer> even = i -> i % 2 == 0;

        //NOTE Function 转换
        Function<Integer, String> toStr = i -> "result:" + i;

        //NOTE Consumer 消费
        Consumer<String> print = System.out::println;

        //NOTE Supplier 兜底
        Supplier<String> defaultVal = () -> "is null";

        //NOTE 串起来
        list.stream().filter(even).map(toStr).findFirst().orElseGet(defaultVal);

        list.stream().filter(even).map(toStr).forEach(print);

    }

    static class Student {
        private String name;

        private void setNameByValue(Supplier<String> function) {
            this.name = function.get();
        }
    }
}
