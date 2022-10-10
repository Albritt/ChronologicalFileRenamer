package com.renamer.Controllers;

import com.renamer.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {
    private FileCollector fileCollector;
    private FileRenamer fileRenamer;
    private Path selectedDirectory;


    @FXML
    private Button clearButton;

    @FXML
    private Button closeButton;

    @FXML
    private GridPane numericalRenameOptionsGridPane;

    @FXML
    private Button openButton;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private TextField prefixText;


    @FXML
    private TextField pathTextField;

    @FXML
    private ComboBox<String> renamingStyleComboBox;

    @FXML
    private Button runButton;

    @FXML
    private ComboBox<String> sortingTypeComboBox;

    @FXML
    private TextField startingNumber;


    public boolean selectionsEmptyOnRun(ComboBox<String> sortingTypeComboBox, ComboBox<String> renamingStyleComboBox){
        boolean isSortingTypeEmpty = sortingTypeComboBox.getSelectionModel().isEmpty();
        boolean isRenamingStyleEmpty = renamingStyleComboBox.getSelectionModel().isEmpty();
        if(isSortingTypeEmpty == true){
            return true;
        }
        if(isRenamingStyleEmpty == true){
            return true;
        }
        else
            return false;
    }
    public void initializeClearButton(){
        clearButton.setOnAction(actionEvent ->{
            outputTextArea.clear();
            pathTextField.clear();
                });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeSortingTypeComboBox(sortingTypeComboBox);
        initializeRenamingStyleComboBox(renamingStyleComboBox);
        initializeClearButton();
        initializeCloseButton();
        initializeOpenButton();
        initializeRunButton();
    }

    private void initializeRunButton() {
        runButton.setOnAction(actionEvent -> {
            if(selectionsEmptyOnRun(sortingTypeComboBox,renamingStyleComboBox) == false){
                switch(sortingTypeComboBox.getSelectionModel().getSelectedItem()) {
                    case "Unsorted":
                        fileCollector = new UnsortedFileCollector(selectedDirectory);
                        break;
                    case "Last Modified":
                        fileCollector = new LastModifiedSortedFileCollector(selectedDirectory);
                        break;
                    case "Date Created":
                        fileCollector = new DateCreatedSortedFileCollector(selectedDirectory);
                        break;
                    case "File Size":
                        fileCollector = new SizeSortedFileCollector(selectedDirectory);
                        break;
                    default:
                        Alert badCaseAlert = new Alert(Alert.AlertType.ERROR);
                        badCaseAlert.setContentText("Case provided does not exist. Check Sorting Type");
                        badCaseAlert.show();
                        throw new UnsupportedOperationException("Case provided does not exist");
                }
                if (Files.isDirectory(selectedDirectory)) {
                    try {
                        fileCollector.collectFromPath();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(prefixText != null) {
                    fileRenamer = new NumericalFileRenamer(fileCollector.getFullFilePath());
                    fileRenamer.rename();
                    outputTextArea.appendText(fileRenamer.getOutputText());
                } else
                    outputTextArea.appendText("Invalid directory provided");
            }
            }
            });
    }

    private void initializeOpenButton() {
        openButton.setOnAction(actionEvent -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Choose Directory");
            selectedDirectory = directoryChooser.showDialog(openButton.getScene().getWindow()).toPath();
            if(selectedDirectory != null)
                pathTextField.setText(selectedDirectory.toString());
        });

    }

    private void initializeRenamingStyleComboBox(ComboBox<String> renamingStyleComboBox) {
        renamingStyleComboBox.getItems().addAll("Numerical");
        renamingStyleComboBox.setValue("Numerical");
    }

    private void initializeSortingTypeComboBox(ComboBox<String> sortingTypeComboBox) {
        sortingTypeComboBox.getItems().addAll("Unsorted", "Date Created", "Last Modified", "File Size");
        sortingTypeComboBox.setValue("Unsorted");
    }

    private void initializeCloseButton() {
        closeButton.setOnAction(actionEvent -> Platform.exit());
    }
}
