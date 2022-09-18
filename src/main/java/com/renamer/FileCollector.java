package com.renamer;

import java.io.File;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileCollector {
    private ArrayList<File> files;
    private Path path;

    private FileCollector(){}
    public FileCollector(Path path){
        this.path = path;
    }

    public ArrayList<File> collect() {
        return files;
    }
}
