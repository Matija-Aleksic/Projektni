package com.example.projektni;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.List;

import datoteke.Promjene;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;




public class PrikazPromjenaController {
    public static List<Promjene> logs;
    static String datoteka = "src/main/resources/com/example/projektni/promjene";
        @FXML private TableView<Promjene> tableView;

        @FXML private TableColumn<Promjene, String> userColumn;

        @FXML private TableColumn<Promjene, String> dateColumn;

        @FXML private TableColumn<Promjene, String> actionColumn;

        public static ObservableList<Promjene> promjenes = FXCollections.observableArrayList();

        @FXML
        public void initialize() {
            //loadChangeLogs();

            userColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser()));
            dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVrijeme().toString()));
            actionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSqlupit()));

            tableView.setItems(promjenes);
        }



}
