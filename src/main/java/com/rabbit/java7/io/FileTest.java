package com.rabbit.java7.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by zhangjing on 15/7/9.
 */
public class FileTest {
    @Test
    public void testDirectory() {
        String dir = "/Users/zhangjing";
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Paths.get(dir), ".*")) {
            for (Path p : entries) {
                System.out.println(p.getFileName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void walkPath() throws IOException {
        String dir = "/Users/zhangjing";
        Files.walkFileTree(Paths.get(dir), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @Test
    public void testFileSystem() {

    }
}
