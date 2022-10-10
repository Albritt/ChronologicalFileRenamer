package com.renamer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;

/*Given a directory write an application that will go through each directory and count all files within the first-level
  directory and sequentially rename them and create a folder to move the original files into
 */
public class FileRenamerApplication extends Application {
    public static void main(String[] args) {
        launch(args);

        /*try {
            Path path = Paths.get(args[0]);
            if (Files.isDirectory(path)) {
                FileCollector fileCollector = new DateCreatedSortedFileCollector(path);
                try {
                    fileCollector.collectFromPath();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                FileRenamer fileRenamer = new PrefixFileRenamer(fileCollector.getFullFilePath());
                fileRenamer.rename();
            } else
                System.out.println("Invalid directory provided");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Please input a valid path.");
        }*/


    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/MainUIView.fxml"));

        Scene scene = new Scene(anchorPane, 600, 400);

        stage.setScene(scene);
        stage.setTitle("File Renamer");
        stage.setAlwaysOnTop(false);
        stage.setResizable(false);
        stage.show();

    }
}