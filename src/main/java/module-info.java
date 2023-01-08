module md.maib.comparator {
    requires javafx.controls;
    requires javafx.fxml;
    requires xlsx.streamer;
    requires poi;
    requires com.fasterxml.jackson.dataformat.csv;
    requires com.fasterxml.jackson.databind;


    opens md.maib.comparator to javafx.fxml;
    exports md.maib.comparator;
    exports md.maib.comparator.controllers;
    exports md.maib.comparator.model;
    opens md.maib.comparator.controllers to javafx.fxml;
}