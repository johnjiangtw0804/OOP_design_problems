package com.johnjiangtw0804.repo.FileSearch;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.util.*;

// search by name
class FileNameSpecification implements Specification<File> {
    private final String fileName;
    public FileNameSpecification(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public boolean isSatisfiedBy(File item) {
        return item.getName().equals(this.fileName);
    }
}

// search by file extension
class FileExtensionSpecification implements Specification<File> {
    private final String fileExtension;
    public FileExtensionSpecification (String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Override
    public boolean isSatisfiedBy(File item) {
        return item.getName().endsWith(fileExtension);
    }
}

// search by size
class FileSizeSpecification implements Specification<File> {
    private final long minSize;
    private final long maxSize;
    public FileSizeSpecification(long maxSize, long minSize) {
        this.maxSize = maxSize;
        this.minSize = minSize;
    }

    @Override
    public boolean isSatisfiedBy(File item) {
        return item.length() <= maxSize && item.length() >= minSize;
    }
}

// search by file creation time
class FileCreationTimeSpecification implements Specification<File> {
    // 2024-01-01T00:00:00Z means "January 1, 2024, at 00:00:00 (midnight) UTC."
    private final Instant from;
    private final Instant to;
    public FileCreationTimeSpecification(Instant from, Instant to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isSatisfiedBy(File item) {
        try {
            BasicFileAttributes basicFileAttributes = Files.readAttributes(item.toPath(), BasicFileAttributes.class);
            return (from == null || !basicFileAttributes.creationTime().toInstant().isAfter(from)) &&
             (to == null || !basicFileAttributes.creationTime().toInstant().isBefore(to));
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}

public class FileSearcher {
    static List<File> res = new ArrayList<>();
    public static List<File> search(File directory, Specification<File> specification) {
        res.clear();
        DFS(directory, specification);
        return res;
    }
    public static void DFS(File directory, Specification<File> specification) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                DFS(file, specification);
            } else {
                if (specification.isSatisfiedBy(file)) {
                    res.add(file);
                }
            }
        }
    }
}
