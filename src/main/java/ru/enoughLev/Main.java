package ru.enoughLev;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {
    public static void main(String[] args) throws IOException {

        reflectMethod();
        nioMethod();
    }

    public static void reflectMethod() {
        String text = "This is texxxt";
        Printer printer = new Printer();
        Method[] methods = Printer.class.getDeclaredMethods();
        for (Method m : methods) {
            m.setAccessible(true);
            int count = 0;
            if (m.isAnnotationPresent(MixStr.class)) {
                count = m.getAnnotation(MixStr.class).value();
            }
            for (int i = 0; i < count; i++) {
                try {
                    m.invoke(printer, text);

                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println();
    }

    public static void nioMethod() throws IOException {
        String surname = "src/Iskhakov";
        String name = "Lev";
        Path rootDir = Paths.get(surname);
        Path myFile = Paths.get(surname, name);
        Files.createDirectory(rootDir);
        Files.createFile(myFile);

        String dir = "dir1";
        Path currentPath = Paths.get(String.valueOf(rootDir), dir);
        for (int i = 2; i <= 4; i++) {
            Files.createDirectory(currentPath);
            Path newFile = Paths.get(String.valueOf(currentPath), name);
            Files.createFile(newFile);
            currentPath = Paths.get(String.valueOf(currentPath), "dir" + i);
        }

        Files.createFile(Paths.get(surname, "dir1", "file1"));
        Files.createFile(Paths.get(surname, "dir1/dir2", "file2"));

        Files.walkFileTree(rootDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                System.out.println("D_" + dir.getFileName());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
                System.out.println("\tF_" + file.getFileName());
                return FileVisitResult.CONTINUE;
            }
        });
        Files.walkFileTree(rootDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

}
