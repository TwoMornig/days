package org.client.learn.se;


public class test {
    public static void main(String[] args) {
        int i = 10;


        String grade;
        switch (i) {
            case 10:
                grade = "a";
                break;
            case 9:
                grade = "b";
                break;
            case 8:
            case 7:
                grade = "c";
                break;
            default:
                grade = "d";
        }

        grade = switch (i) {
            case 10 -> "a";
            case 9 -> "b";

            default -> "d";
        };
    }
}
