package com.example.projektni;

import BazaPodataka.BazaPodataka;
import Entiteti.KopneneZivotinje;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public class PretragaKopneneController {
    @FXML private ChoiceBox<String> choiceBox;
    @FXML private Button izmijeniButton;
    @FXML private Button obrisiButton;
    @FXML private TableView<KopneneZivotinje> KopneneZivotinjeTableView;
    @FXML private TableColumn<KopneneZivotinje,String> imeTableColumn;
    @FXML private TableColumn<KopneneZivotinje,String> prehranaTableColumn;
    @FXML private TableColumn<KopneneZivotinje,String> stanisteTableColumn;
    @FXML private TableColumn<KopneneZivotinje,String> tezinaTableColumn;
    @FXML private TableColumn<KopneneZivotinje,String> brojNoguTableColumn;
    @FXML private TableColumn<KopneneZivotinje,String> bojaKrznaTableColumn;
    @FXML private TableColumn<KopneneZivotinje,String> brojOcijuTableColumn;
    private List<KopneneZivotinje> Zivine = new ArrayList<>();

    public void initialize() {
        choiceBox.getItems().addAll("sisavac", "kukac");
        choiceBox.setValue("sisavac");

    }

}
