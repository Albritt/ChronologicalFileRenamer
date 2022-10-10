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

public class LastModifiedSortedFileCollector extends SortedFileCollector {
    public LastModifiedSortedFileCollector(Path directoryPath){
        super(directoryPath);
    }
    @Override
    void sortDirectoryFiles(ArrayList<File> directoryFiles) {
        Comparator<File> lastModifiedComparator = Comparator.comparing(file -> {
            try{
                return Files.readAttributes(Paths.get(file.toURI()), BasicFileAttributes.class).lastModifiedTime();
            }catch(IOException ex){
                ex.printStackTrace();
                return null;
            }
        });

        Collections.sort(directoryFiles, lastModifiedComparator);

    }
}
