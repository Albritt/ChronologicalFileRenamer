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
    private TextField startingNumberTextField;


    public boolean comboBoxHasSelection(ComboBox<?> comboBox){
        boolean isComboBoxEmpty = comboBox.getSelectionModel().isEmpty();
        if(isComboBoxEmpty)
            return false;
        else
            return true;
    }
    public void initializeClearButton(){
        clearButton.setOnAction(actionEvent ->{
            outputTextArea.clear();
            pathTextField.clear();
            runButton.setDisable(true);
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
            if((comboBoxHasSelection(sortingTypeComboBox) && comboBoxHasSelection(renamingStyleComboBox))){
                selectSortingStyle();
                try {
                    fileCollector.collectFromPath();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(chooseRenamerStyle()){
                    fileRenamer.rename();
                    outputTextArea.appendText(fileRenamer.getOutputText());
                }
            }else
                showErrorAlert("Please ensure a sorting and renaming style are selected.");
        });
    }

    private boolean chooseRenamerStyle() {
        try{
            int startingNumber = Integer.parseInt(startingNumberTextField.getText());
            if(prefixText.getText() == null)
                fileRenamer = new NumericalFileRenamer(fileCollector.getFullFilePath(), startingNumber);
            else{
                try{
                    fileRenamer = new TextFileRenamer(fileCollector.getFullFilePath(),
                            startingNumber,prefixText.getText());
                }catch(IllegalArgumentException illegalArgumentException){
                    showErrorAlert("Bad input given, please check text pattern for renaming");
                    return false;
                }
            }

            return true;
        }catch(NumberFormatException numberFormatException){
            showErrorAlert("Integer not provided for 'Start With' field");
            return false;
        }

    }

    private void selectSortingStyle() {
        switch (sortingTypeComboBox.getSelectionModel().getSelectedItem()) {
            case "Unsorted" -> fileCollector = new UnsortedFileCollector(selectedDirectory);
            case "Last Modified" -> fileCollector = new LastModifiedSortedFileCollector(selectedDirectory);
            case "Date Created" -> fileCollector = new DateCreatedSortedFileCollector(selectedDirectory);
            case "File Size" -> fileCollector = new SizeSortedFileCollector(selectedDirectory);
            default -> {
                showErrorAlert("Case provided does not exist. Check Sorting Type");
                throw new UnsupportedOperationException("Case provided does not exist");
            }
        }
    }

    private void showErrorAlert(String message) {
        Alert badCaseAlert = new Alert(Alert.AlertType.ERROR);
        badCaseAlert.setContentText(message);
        badCaseAlert.show();
    }

    private void initializeOpenButton() {
        openButton.setOnAction(actionEvent -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Choose Directory");
            File file = directoryChooser.showDialog(openButton.getScene().getWindow());
            if(file == null || !file.isDirectory()){
                runButton.setDisable(true);
            }else{
                selectedDirectory = file.toPath();
                pathTextField.setText(selectedDirectory.toString());
                runButton.setDisable(false);
            }
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
