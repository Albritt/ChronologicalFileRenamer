package com.renamer;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public abstract class FileRenamer {
    private int subDirectoryFileCounter = 0;
    private int totalFileCount = 0;

    private final int startingNumber;


    private String outputText;
    protected HashMap<Integer, List<File>> fullFilePath;
    public FileRenamer(HashMap<Integer, List<File>> fullFilePath, int startingNumber){
        this.fullFilePath = fullFilePath;
        this.startingNumber = startingNumber;
    }

    public int getSubDirectoryFileCounter() {
        return subDirectoryFileCounter;
    }

    public void setSubDirectoryFileCounter(int subDirectoryFileCounter) {
        this.subDirectoryFileCounter = subDirectoryFileCounter;
    }

    public String getOutputText() {
        return outputText;
    }

    public void setOutputText(String outputText) {
        this.outputText = outputText;
    }
    public HashMap<Integer, List<File>> getFullFilePath() {
        return fullFilePath;
    }
    public void setFullFilePath(HashMap<Integer, List<File>> fullFilePath) {
        this.fullFilePath = fullFilePath;
    }

    public void rename(){
        StringBuilder sb = new StringBuilder();
        fullFilePath.forEach((directory, files)->{
            subDirectoryFileCounter = startingNumber;
            for(File file : files){
                File renamedFile = createRenamedFile(file, subDirectoryFileCounter);
                if(renamedFile.exists()){
                    sb.append("File " + renamedFile + " already exists. Skipping to avoid overwrite");
                }
                else {
                    if(file.renameTo(renamedFile)){
                        subDirectoryFileCounter++;
                        sb.append("Renamed " + file.getName() + " to " + renamedFile.getName() + "." );
                    }
                    else{
                        sb.append("Failed to rename " + file.getName());
                    }
                }
                sb.append("\n");
            }
            totalFileCount = (subDirectoryFileCounter - startingNumber) + totalFileCount;
        });
        sb.append("Renamed " + totalFileCount + " files");
        sb.append("\n");
        outputText = sb.toString();
    }

    protected abstract File createRenamedFile(File file, int fileCounter);
}
