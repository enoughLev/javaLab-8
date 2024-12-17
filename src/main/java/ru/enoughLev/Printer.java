package ru.enoughLev;

public class Printer {
    @InvokeCount(1)
    public static void printUpperCase(String s) {
        System.out.println(s.toUpperCase());
    }

    @InvokeCount(1)
    public static void printLowerCase(String s) {
        System.out.println(s.toLowerCase());
    }

    @InvokeCount(1)
    protected static void printJumpyCase(String s) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                str.append(Character.toLowerCase(s.charAt(i)));
            }
            else str.append(Character.toUpperCase(s.charAt(i)));
        }
        System.out.println(str);;
    }

    @InvokeCount(1)
    protected static void printWithoutSpace(String s) {
        System.out.println("Nothing there...");
    }
}
