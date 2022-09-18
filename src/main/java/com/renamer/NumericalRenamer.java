package com.renamer;

import java.io.File;
import java.util.ArrayList;

public class NumericalRenamer implements Renamer {
    private ArrayList<File> files;

    private NumericalRenamer() {
    }

    public NumericalRenamer(ArrayList<File> files){
        this.files = files;
    }

    @Override
    public void rename(){
        return;
    }
}
