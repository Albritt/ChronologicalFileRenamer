package com.renamer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrefixFileRenamer extends FileRenamer {
    private String prefix;
    private List<File> files;

    private PrefixFileRenamer(){}

    public PrefixFileRenamer(List<File> files){
        this.files = files;
    }
    @Override
    public void rename() {
        return;
    }

    void requestPrefix(){
        System.out.println("Please input prefix to use");
        Scanner scanner = new Scanner(System.in);
        this.prefix = scanner.nextLine();
    }
}
