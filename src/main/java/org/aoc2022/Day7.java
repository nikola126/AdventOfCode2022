package org.aoc2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7 {
    public final String FILE_DIR = "./src/main/resources/day7.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day7() {
        try {
            scanner = new Scanner(file);
        } catch (Exception ignored) {
        }
    }

    public void partOne() throws FileNotFoundException {
        scanner = new Scanner(file);

        final long maxSize = 100000;

        AdventDirectory root = getRootDirectory(scanner);

        scanner.close();

        List<AdventDirectory> listToRemove = new ArrayList<>();

        traversePartOne(root, listToRemove, maxSize);

        long totalSize = 0;
        for (AdventDirectory directory : listToRemove) {
            totalSize += directory.getSize();
        }

        System.out.println("Total size: " + totalSize);
    }

    public void partTwo() throws FileNotFoundException {
        scanner = new Scanner(file);

        final long totalSize = 70000000;
        final long requiredSize = 30000000;

        AdventDirectory root = getRootDirectory(scanner);

        scanner.close();

        long usedSize = root.getSize();
        long freeSize = totalSize - usedSize;
        long sizeToFree = requiredSize - freeSize;

        List<Long> sizesToDelete = new ArrayList<>();

        traversePartTwo(root, sizesToDelete, sizeToFree);

        long minSize = Long.MAX_VALUE;

        for (Long size : sizesToDelete) {
            minSize = Math.min(minSize, size);
        }

        System.out.println("Min size: " + minSize);
    }

    private AdventDirectory getRootDirectory(Scanner scanner) {
        Stack<AdventDirectory> path = new Stack<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] tokens = line.split(" ");

            if (line.startsWith("$")) {
                switch (tokens[1]) {
                    case "cd" -> {
                        if (Objects.equals(tokens[2], "..")) {
                            // $ cd ..
                            AdventDirectory popped = path.pop();

                            AdventDirectory existingDirectory = null;
                            if (!path.isEmpty()) {
                                existingDirectory = path.peek().getDirectories().stream()
                                        .filter(ad -> ad.getName().equals(popped.getName()))
                                        .findFirst().orElse(null);
                            }

                            if (existingDirectory == null) {
                                path.peek().getDirectories().add(popped);
                            } else {
                                path.peek().getDirectories().remove(existingDirectory);
                                path.peek().getDirectories().add(popped);
                            }
                        } else {
                            // cd abc
                            String dirName = tokens[2];

                            AdventDirectory existingDirectory = null;
                            if (!path.isEmpty()) {
                                existingDirectory = path.peek().getDirectories().stream().filter(ad -> ad.getName().equals(dirName)).findFirst().orElse(null);
                            }

                            if (existingDirectory == null) {
                                path.push(new AdventDirectory(dirName));
                            } else {
                                path.push(existingDirectory);
                            }
                        }
                    }
                    case "ls" -> {
                    }
                }
            } else {
                if (Objects.equals(tokens[0], "dir")) {
                    // dir abc
                    String dirName = tokens[1];
                    path.peek().getDirectories().add(new AdventDirectory(dirName));
                } else {
                    // 123 abc
                    long size = Long.parseLong(tokens[0]);
                    String filename = tokens[1];
                    path.peek().getFiles().add(new AdventFile(size, filename));
                }
            }
        }

        return path.get(0);
    }

    private void traversePartOne(AdventDirectory currentDir, List<AdventDirectory> listToRemove, long maxSize) {
        if (currentDir.getSize() <= maxSize) {
            listToRemove.add(currentDir);
        }

        for (AdventDirectory child : currentDir.getDirectories()) {
            traversePartOne(child, listToRemove, maxSize);
        }
    }

    private void traversePartTwo(AdventDirectory currentDir, List<Long> sizesToDelete,  long sizeToFree) {
        long size = currentDir.getSize();

        if (size > sizeToFree) {
            sizesToDelete.add(size);
        }

        for (AdventDirectory child : currentDir.getDirectories()) {
            traversePartTwo(child, sizesToDelete, sizeToFree);
        }
    }

    private static class AdventFile {
        private long size;
        private String name;

        public AdventFile() {
        }

        public AdventFile(long size, String name) {
            this.size = size;
            this.name = name;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "AdventFile{" +
                    "size=" + size +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    private static class AdventDirectory {
        private String name;
        private List<AdventDirectory> directories = new ArrayList<>();
        private List<AdventFile> files = new ArrayList<>();

        public long getSize() {
            long size = 0;

            for (AdventFile file : files) {
                size += file.getSize();
            }

            for (AdventDirectory directory : directories) {
                size += directory.getSize();
            }

            return size;
        }

        public AdventDirectory() {
        }

        public AdventDirectory(String name) {
            this.name = name;
        }

        public List<AdventDirectory> getDirectories() {
            return directories;
        }

        public void setDirectories(List<AdventDirectory> directories) {
            this.directories = directories;
        }

        public List<AdventFile> getFiles() {
            return files;
        }

        public void setFiles(List<AdventFile> files) {
            this.files = files;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "AdventDirectory{" +
                    "name='" + name + '\'' +
                    ", directories=" + directories +
                    ", files=" + files +
                    '}';
        }
    }

}