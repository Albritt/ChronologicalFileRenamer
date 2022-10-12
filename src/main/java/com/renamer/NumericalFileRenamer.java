package com.renamer;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class NumericalFileRenamer extends FileRenamer {

    public NumericalFileRenamer(HashMap<Integer, List<File>> fullFilePath, int startingNumber){
       super(fullFilePath,startingNumber);
    }


    @Override
    protected File createRenamedFile(File file, int fileNumber){
        int indexOfExtension = file.getName().lastIndexOf(".");
        String extensionName = file.getName().substring(indexOfExtension);
        String directoryPathName = file.getParent() + "/";
        return new File(directoryPathName + fileNumber + extensionName);
    }
}
