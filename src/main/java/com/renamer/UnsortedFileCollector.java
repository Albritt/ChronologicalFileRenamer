package com.renamer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class UnsortedFileCollector implements FileCollector {
    protected HashMap<Integer, List<File>> fullFilePath;
    protected Path directoryPath;
    protected int directoryCount;

    protected int fileTreeBase = 0;

    public UnsortedFileCollector(Path directoryPath){
        this.directoryPath = directoryPath;
        fullFilePath = new HashMap<>();
    }

    @Override
    public File[] showPath() throws IOException {
        return directoryPath.toFile().listFiles();
    }

    @Override
    public void collectFromPath() throws IOException {
        if(this.showPath() == null) {
            throw new RuntimeException(directoryPath.toString() +
                " is empty or not a directory, no renaming can be done.");
        }
        else{
            collectFiles(this.showPath(),fileTreeBase);
        }
    }


    protected void collectFiles(File[] files, int fileTreeLevel) throws IOException {
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
            for (File file : directoryFiles) {
            }
            //sortDirectoryFiles(directoryFiles);
            fullFilePath.put(fileTreeLevel, directoryFiles);
        }
    }

    @Override
    public HashMap<Integer, List<File>> getFullFilePath() {
        return this.fullFilePath;
    }

    @Override
    public void setFullFilePath(HashMap<Integer, List<File>> fullFilePath) {
        this.fullFilePath = fullFilePath;

    }

    @Override
    public Path getDirectory() {
        return this.directoryPath;
    }

    @Override
    public void setDirectory(Path directoryPath) {
        this.directoryPath = directoryPath;
    }
}
