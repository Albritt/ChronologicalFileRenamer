package com.renamer;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class PrefixRenamer implements Renamer{
    private String prefix;
    private ArrayList<File> files;

    private PrefixRenamer(){}

    public PrefixRenamer(ArrayList<File> files){
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
