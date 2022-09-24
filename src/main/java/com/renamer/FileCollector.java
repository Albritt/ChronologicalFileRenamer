package com.renamer;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public abstract class FileCollector{
    private HashMap<Integer, List<File>> fullFilePath;
    private Path directoryPath;
    private int baseLevel;
    private int directoryCount;

    public FileCollector(Path directoryPath){
        this.directoryPath = directoryPath;
        fullFilePath = new HashMap<Integer, List<File>>();
    }

    public File[] showPath() throws IOException{
        return directoryPath.toFile().listFiles();
    }

    public void collectFiles (File[] files, int fileTreeLevel) throws IOException{
        ArrayList<File> directoryFiles =  new ArrayList<File>();
        for (File file: files){
            if(file.isFile()){
                directoryFiles.add(file);
            }
            else if(file.isDirectory()){
                directoryCount++;
                collectFiles(file.listFiles(), directoryCount);
            }
        }

        if(directoryFiles.size() > 0){
            sortDirectoryFiles(directoryFiles);
            fullFilePath.put(fileTreeLevel,directoryFiles);
        }
    }

    abstract void sortDirectoryFiles(ArrayList<File> directoryFiles);
    public HashMap<Integer, List<File>> getFullFilePath() {
        return fullFilePath;
    }

    public void setFullFilePath(HashMap<Integer, List<File>> fullFilePath) {
        this.fullFilePath = fullFilePath;
    }

    public Path getDirectory() {
        return directoryPath;
    }

    public void setDirectory(Path directoryPath) {
        this.directoryPath = directoryPath;
    }

}
