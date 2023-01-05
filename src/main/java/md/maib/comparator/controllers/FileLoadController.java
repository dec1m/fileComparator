package md.maib.comparator.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;

public class FileLoadController {
    @FXML
    private Button fileOpenButtonA;
    @FXML
    private Button fileOpenButtonB;


    @FXML
    protected void onOpenFileA() {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        System.out.println(file.getAbsolutePath());
    }

    @FXML
    protected void onOpenFileB() {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        System.out.println(file.getAbsolutePath());
    }
}