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

    private HashMap<Integer, List<File>> fullFilePath;
    public FileRenamer(){}

    abstract void rename();
}
