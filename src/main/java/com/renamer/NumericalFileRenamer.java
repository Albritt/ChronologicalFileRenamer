package com.renamer;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class NumericalFileRenamer extends FileRenamer {

    public NumericalFileRenamer(HashMap<Integer, List<File>> fullFilePath){
       super(fullFilePath);
    }


    @Override
    protected File createRenamedFile(File file, int fileNumber){
        int indexOfExtension = file.getName().lastIndexOf(".");
        String extensionName = file.getName().substring(indexOfExtension);
        String directoryPathName = file.getParent().toString() + "/";
        File renamedFile = new File(directoryPathName + fileNumber + extensionName);
        return renamedFile;
    }
}
