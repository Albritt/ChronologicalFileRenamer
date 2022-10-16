package com.renamer;

import java.io.File;
import java.util.*;

public class TextFileRenamer extends FileRenamer {
    private final String prefix;
    private final String[] illegalCharacters;

    public TextFileRenamer(HashMap<Integer,List<File>> fullFilePath, int startingNumber, String prefix){
        super(fullFilePath,startingNumber);
        illegalCharacters = new String[]{"/","#","%","&","\\","{","}",",","<",">","*","?",
                "$","!",":","@","+","`","|","=","'"};
        if(containsIllegalChars(prefix)){
            throw new IllegalArgumentException("Invalid character provided in TextFileRenamer constructor");
        }
        else
            this.prefix = prefix;
    }

    private boolean containsIllegalChars(String appendText) {
        boolean illegalCharFound = false;

        for(String illegalChar : illegalCharacters) {
            if (appendText.contains(illegalChar)) {
                illegalCharFound = true;
                break;
            }
        }
        return illegalCharFound;
    }

    @Override
    protected File createRenamedFile(File file, int fileNumber) {
        int indexOfExtension = file.getName().lastIndexOf(".");
        String extensionName = file.getName().substring(indexOfExtension);
        String directoryPathName = file.getParent() + "/";
        return new File(directoryPathName + prefix + fileNumber + extensionName);

    }
}
