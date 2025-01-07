package com.johnjiangtw0804;
import java.util.*;
import java.io.*;
import java.time.Instant;
/**
 * Design Unix File Search API to search file with different arguments as "extension", "name", "size", and "creation time"
 * The design should be maintainable to add new constraints.
 * Searching requirements include: File Size, Creation Time
 * Each constraints can be linked using AND OR, and NOT
 */
public class App
{
    public static void main( String[] args ) {
        // Search for file size > 100 MB
        File currentDirectory = new File(System.getProperty("user.dir"));
        long targeSizeBytes = 100 * 1024 * 1024;
        Random random = new Random();
        File file1 = new File("file1.txt");
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file1))) {
            long currentSize = 0;
            byte[] container = new byte[1024]; // 1kb
            while (currentSize < targeSizeBytes) {
                random.nextBytes(container);
                bos.write(container);
                currentSize += container.length;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        Specification<File> spec = new FileSizeSpecification(targeSizeBytes, targeSizeBytes);
        System.out.println("Test 1: File Size");
        System.out.println(FileSearcher.search(currentDirectory, spec));
        spec = spec.and(new FileExtensionSpecification(".txt"));
        System.out.println("Test 2: File Extension");
        System.out.println(FileSearcher.search(currentDirectory, spec));
        // search for name

        spec = spec.or(new FileNameSpecification("file2.txt"));
        System.out.println("Test 3: File Name");
        System.out.println(FileSearcher.search(currentDirectory, spec));

        System.out.println("Test 4: Creation Time");
        spec = spec.and(new FileCreationTimeSpecification(Instant.now().plusSeconds(5 * 60), Instant.now().plusSeconds(7 * 60)));
        System.out.println(FileSearcher.search(currentDirectory, spec));
    }
}
