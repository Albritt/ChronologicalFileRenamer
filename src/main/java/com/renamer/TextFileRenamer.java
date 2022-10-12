package com.renamer;

import java.io.File;
import java.util.*;

public class TextFileRenamer extends FileRenamer {
    private final String prefix;
    private String[] illegalCharacters;

    public TextFileRenamer(HashMap<Integer,List<File>> fullFilePath, int startingNumber, String prefix){
        super(fullFilePath,startingNumber);
        //illegalCharacters = new String[]{"/","#","%","&","\\","{","}",",","<",">","*","?",
           //     "$","!",":","@","+","`","|","=","'"};
        this.prefix = prefix;
    }

    @Override
    protected File createRenamedFile(File file, int fileNumber) {
        int indexOfExtension = file.getName().lastIndexOf(".");
        String extensionName = file.getName().substring(indexOfExtension);
        String directoryPathName = file.getParent() + "/";
        return new File(directoryPathName + prefix + fileNumber + extensionName);

    }

    /*private String requestPrefix(){
        boolean acceptedInput = false;
        String prefixInput = new String("");

        while (acceptedInput == false){
            prefixInput = promptUserInput();
            if(containsBadInput(prefixInput) == false){
                acceptedInput = true;
            }
        }
        return prefixInput;
    }*/

    /*private String promptUserInput (){
        System.out.println("Please input prefix to use");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }*/

    /*private boolean containsBadInput(String prefixInput){
        boolean badInput = false;

        for(String illegalChar : illegalCharacters){
            if(prefixInput.contains(illegalChar)){
                //adviseBadInput(illegalChar);
                badInput = true;
                // throw new RuntimeException("Illegal character provided: " + illegalChar);
            }
        }
        return badInput;
    }*/

    /*private void adviseBadInput (String badInput){
        System.out.println("Invalid character " + badInput +
                ".");
    }*/
}
