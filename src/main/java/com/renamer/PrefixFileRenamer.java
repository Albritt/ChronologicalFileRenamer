package com.renamer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PrefixFileRenamer extends FileRenamer {
    private String prefix;
    private String[] illegalCharacters;

    public PrefixFileRenamer(HashMap<Integer,List<File>> fullFilePath){
        super(fullFilePath);
        illegalCharacters = new String[]{"/","#","%","&","\\","{","}",",","<",">","*","?",
                "$","!",":","@","+","`","|","=","'"};
    }

    @Override
    File createRenamedFile(File file, int fileNumber) {
        this.prefix = requestPrefix();
        int indexOfExtension = file.getName().lastIndexOf(".");
        String extensionName = file.getName().substring(indexOfExtension);
        String directoryPathName = file.getParent().toString() + "/";
        File renamedFile = new File(directoryPathName + prefix + fileNumber + extensionName);
        return renamedFile;

    }

    private String requestPrefix(){
        boolean acceptedInput = false;
        while (!acceptedInput){
            System.out.println("Please input prefix to use");
            Scanner scanner = new Scanner(System.in);
            String prefixInput = scanner.nextLine();
            for(String illegalChar : illegalCharacters){
                if(prefixInput.contains(illegalChar)){
                    System.out.println("Invalid character " + illegalChar +
                            ". Please input valid string without: " + illegalCharacters.toString());
                }
            }
            acceptedInput = true;
        }
        return prefix;


    }
}
