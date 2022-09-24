package com.renamer;

import java.io.File;
import java.util.*;

public class PrefixFileRenamer extends FileRenamer {
    private String prefix;
    private String[] illegalCharacters;

    public PrefixFileRenamer(HashMap<Integer,List<File>> fullFilePath){
        super(fullFilePath);
        illegalCharacters = new String[]{"/","#","%","&","\\","{","}",",","<",">","*","?",
                "$","!",":","@","+","`","|","=","'"};
        this.prefix = requestPrefix();
    }

    @Override
    File createRenamedFile(File file, int fileNumber) {
        int indexOfExtension = file.getName().lastIndexOf(".");
        String extensionName = file.getName().substring(indexOfExtension);
        String directoryPathName = file.getParent().toString() + "/";
        File renamedFile = new File(directoryPathName + prefix + fileNumber + extensionName);
        return renamedFile;

    }

    private String requestPrefix(){
        boolean acceptedInput = false;
        String prefixInput = new String();
        while (acceptedInput == false){
            System.out.println("Please input prefix to use");
            Scanner scanner = new Scanner(System.in);
            prefixInput = scanner.nextLine();
            for(String illegalChar : illegalCharacters){
                if(prefixInput.contains(illegalChar)){
                    System.out.println("Invalid character " + illegalChar +
                            ". Please input valid string.");
                }
            }
            acceptedInput = true;
        }
        return prefixInput;


    }
}
