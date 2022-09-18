package com.renamer;

import java.io.File;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.ArrayList;

public class SizeCollector implements Collector{
    private ArrayList<File> files;
    private Path path;

    private SizeCollector(){}

    public SizeCollector(Path path){
        this.path = path;
    }

    @Override public ArrayList<File> collect(){
        return files;

    }
}
