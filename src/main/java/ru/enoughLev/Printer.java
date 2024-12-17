package ru.enoughLev;

public class Printer {
    @MixStr(1)
    public static void printUpperCase(String s) {
        System.out.println(s.toUpperCase());
    }

    @MixStr(2)
    public static void printLowerCase(String s) {
        System.out.println(s.toLowerCase());
    }

    @MixStr(3)
    protected static void printJumpyString(String s) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                str.append(Character.toLowerCase(s.charAt(i)));
            } else str.append(Character.toUpperCase(s.charAt(i)));
        }
        System.out.println(str);
    }

    @MixStr(1)
    protected static void printWithoutSpaces(String s) {
        for(String word : s.split(" ")) System.out.print(word);
        System.out.println();
    }

    @MixStr(2)
    private static void reverseString(String s){
        for(int i = s.length()-1; i>=0; i--) System.out.print(s.charAt(i));
        System.out.println();
    }

    @MixStr(3)
    private static void printWithSpaces (String s) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            str.append(s.charAt(i));
            str.append(" ");
        }
        System.out.println(str);
    }
}
