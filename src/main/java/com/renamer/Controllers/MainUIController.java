package com.renamer.Controllers;

import com.renamer.FileCollector;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {
    private FileCollector fileCollector;

    @FXML
    private Button clearButton;

    @FXML
    private Button closeButton;

    @FXML
    private TextArea listFilesTextArea;

    @FXML
    private Button openButton;

    @FXML
    private GridPane optionsGridPane;

    @FXML
    private ComboBox<String> renamingStyleComboBox;

    @FXML
    private Button runButton;

    @FXML
    private ComboBox<String> sortingTypeComboBox;

    public boolean areSelectionsEmptyOnRun(ComboBox<String> sortingTypeComboBox, ComboBox<String> renamingStyleComboBox){
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sortingTypeComboBox.getItems().addAll("Unsorted", "Date Created", "Last Modified", "File Size");
        renamingStyleComboBox.getItems().addAll("Numerical");
        closeButton.setOnAction(actionEvent -> Platform.exit());
        openButton.setOnAction(actionEvent -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Choose Directory");
            File selectedDirectory = directoryChooser.showDialog(openButton.getScene().getWindow());
        });
        clearButton.setOnAction(actionEvent ->
                listFilesTextArea.clear());
        runButton.setOnAction(actionEvent -> {
            if(areSelectionsEmptyOnRun(sortingTypeComboBox,renamingStyleComboBox) == false){
                if(sortingTypeComboBox.getSelectionModel().getSelectedItem() == "Unsorted"){

                }
            }
            });

        /*sortingTypeComboBox.setOnAction((actionEvent -> {
            String selectedItem = sortingTypeComboBox.getSelectionModel().getSelectedItem();
            if(selectedItem == "Unsorted"){
                renamingStyleComboBox.setVisible(false);
                renamingStyleComboBox.setValue("");
            }
            else{
                renamingStyleComboBox.setVisible(true);
            }
        }));*/
    }
}
