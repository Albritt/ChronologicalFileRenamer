package com.renamer;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public abstract class FileCollector{
    private HashMap<Integer, List<File>> fullFilePath;
    private Path directoryPath;
    private int directoryCount;

    private int fileTreeBase = 0;

    public FileCollector(Path directoryPath){
        this.directoryPath = directoryPath;
        fullFilePath = new HashMap<>();
    }

    private File[] showPath() throws IOException{
        return directoryPath.toFile().listFiles();
    }

    public void collectFromPath() throws IOException{
        if(this.showPath() != null) {
            collectFiles(this.showPath(),fileTreeBase);
        }
        else{
            throw new RuntimeException("Directory is empty, no renaming can be done.");
        }
    }

    private void collectFiles (File[] files, int fileTreeLevel) throws IOException{
        ArrayList<File> directoryFiles =  new ArrayList<>();
        for (File file: files){
            if(file.isFile()){
                directoryFiles.add(file);
            }
            else if(file.isDirectory()){
                directoryCount++;
                collectFiles(Objects.requireNonNull(file.listFiles()), directoryCount);
            }
        }

        if(directoryFiles.size() > 0){
            for(File file :directoryFiles){
                System.out.println(Files.getAttribute(file.toPath(),"creationTime").toString());
            }
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
