package com.renamer;

import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/*Given a directory write an application that will go through each directory and count all files within the first-level
  directory and sequentially rename them and create a folder to move the original files into
 */
public class Application {
    public static void main(String[] args) {

        try{Path path = Paths.get(args[0]);
            if(Files.isDirectory(path)){
                FileCollector fileCollector = new FileCollector(path);
                ArrayList<File> files = fileCollector.collect();
                FileRenamer fileRenamer = new FileRenamer(files);
                fileRenamer.rename();
            }
            else
                System.out.println("Invalid directory provided");}
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Please input a valid path.");
        }



    }
}