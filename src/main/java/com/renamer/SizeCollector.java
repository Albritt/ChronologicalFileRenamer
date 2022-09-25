package com.renamer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SizeCollector extends FileCollector{
    public SizeCollector(Path directoryPath){
        super(directoryPath);
    }
    @Override
    void sortDirectoryFiles(ArrayList<File> directoryFiles) {
        Comparator<File> sizeComparator = Comparator.comparing(file -> {
            try{
                return Files.readAttributes(Paths.get(file.toURI()), BasicFileAttributes.class).size();
            }catch(IOException ex){
                ex.printStackTrace();
                return null;
            }
        });

        Collections.sort(directoryFiles, sizeComparator);

    }
}
