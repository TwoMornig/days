package org.client.learn.se;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        String s = null;
        Optional.ofNullable(s).get();
        Optional.ofNullable(s).orElse("asd");
        Optional.ofNullable(s).ifPresent(System.out::println);
        Optional.ofNullable(s).ifPresentOrElse(System.out::println, () -> {
            System.out.println("no value");
        });
        Optional.ofNullable(s).or(() -> Optional.of("123")).ifPresent(System.out::println);
    }

    private static void processUser(String str) {

    }

    private static void processUser() {

    }
}
