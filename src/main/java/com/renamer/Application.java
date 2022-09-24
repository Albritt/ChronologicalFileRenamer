package com.renamer;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;

/*Given a directory write an application that will go through each directory and count all files within the first-level
  directory and sequentially rename them and create a folder to move the original files into
 */
public class Application {
    public static void main(String[] args) {

        try{Path path = Paths.get(args[0]);
            if(Files.isDirectory(path)){
                FileCollector fileCollector = new DateCreatedFileCollector(path);
                try{fileCollector.collectFiles(fileCollector.showPath(),0);}
                catch (IOException e){e.printStackTrace();}
                HashMap<Integer, List<File>> files = fileCollector.getFullFilePath();
                FileRenamer numericalRenamer = new NumericalFileRenamer(files);
                numericalRenamer.rename();
            }
            else
                System.out.println("Invalid directory provided");}
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Please input a valid path.");
        }



    }
}