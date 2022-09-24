package com.renamer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NumericalFileRenamer extends FileRenamer {
    private List<File> files;

    private NumericalFileRenamer() {
    }

    public NumericalFileRenamer(List<File> files){
        this.files = files;
    }

    @Override
    public void rename(){
        return;
    }
}
