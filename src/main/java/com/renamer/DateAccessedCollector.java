package com.renamer;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

public class DateAccessedCollector implements Collector {
    private ArrayList<File> files;
    private Path path;

    private DateAccessedCollector(){}
    public DateAccessedCollector(Path path){
        this.path = path;
    }

    @Override
    public ArrayList<File> collect() {
        return files;
    }
}
