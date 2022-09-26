package com.renamer;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public abstract class SortedFileCollector extends UnsortedFileCollector implements FileCollector {


    public SortedFileCollector(Path directoryPath){
        super(directoryPath);
    }

    @Override
    public void collectFiles(File[] files, int fileTreeLevel) throws IOException {
        ArrayList<File> directoryFiles = new ArrayList<>();
        for (File file : files) {
            if (file.isFile()) {
                directoryFiles.add(file);
            } else if (file.isDirectory()) {
                directoryCount++;
                collectFiles(Objects.requireNonNull(file.listFiles()), directoryCount);
            }
        }

        if (directoryFiles.size() > 0) {
            sortDirectoryFiles(directoryFiles);
            fullFilePath.put(fileTreeLevel, directoryFiles);
        }
    }

    abstract void sortDirectoryFiles(ArrayList<File> directoryFiles);
}
