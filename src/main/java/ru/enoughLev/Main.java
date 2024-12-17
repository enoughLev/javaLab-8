package ru.enoughLev;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {
    public static void main(String[] args) {

        reflect();

    }

    public static void reflect() {
        String text = "This is texxxt";
        Printer printer = new Printer();
        Method[] methods = Printer.class.getDeclaredMethods();
        for (Method m : methods) {
            m.setAccessible(true);
            int count = 0;
            if (m.isAnnotationPresent(InvokeCount.class)) {
                count = m.getAnnotation(InvokeCount.class).value();
            }
            for (int i=0; i<count; i++){
                try{
                    m.invoke(printer, text);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
