module md.maib.comparator {
    requires javafx.controls;
    requires javafx.fxml;


    opens md.maib.comparator to javafx.fxml;
    exports md.maib.comparator;
    exports md.maib.comparator.controllers;
    opens md.maib.comparator.controllers to javafx.fxml;
}