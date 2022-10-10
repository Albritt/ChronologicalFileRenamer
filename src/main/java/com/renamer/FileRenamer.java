package com.renamer;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public abstract class FileRenamer {
    public HashMap<Integer, List<File>> getFullFilePath() {
        return fullFilePath;
    }

    public void setFullFilePath(HashMap<Integer, List<File>> fullFilePath) {
        this.fullFilePath = fullFilePath;
    }
    private int fileCounter = 0;

    public int getFileCounter() {
        return fileCounter;
    }

    public void setFileCounter(int fileCounter) {
        this.fileCounter = fileCounter;
    }

    public String getOutputText() {
        return outputText;
    }

    public void setOutputText(String outputText) {
        this.outputText = outputText;
    }

    private String outputText;
    protected HashMap<Integer, List<File>> fullFilePath;
    public FileRenamer(HashMap<Integer, List<File>> fullFilePath){
        this.fullFilePath = fullFilePath;
    }

    public void rename(){
        StringBuilder sb = new StringBuilder();
        fullFilePath.forEach((directory, files)->{
            fileCounter = 0;
            for(File file : files){
                File renamedFile = createRenamedFile(file, fileCounter);
                if(file.renameTo(renamedFile)){
                    fileCounter++;
                    sb.append("Renamed " + file.getName() + " to " + renamedFile.getName() + "." );
                    sb.append("\n");
                }
                else{
                    sb.append("Failed to rename " + file.getName());
                    sb.append("\n");
                }

            }
            sb.append("Renamed " + fileCounter + " files");
            sb.append("\n");
            outputText = sb.toString();
            System.out.println(sb);

        });
    }

    abstract File createRenamedFile(File file, int fileCounter);
}
