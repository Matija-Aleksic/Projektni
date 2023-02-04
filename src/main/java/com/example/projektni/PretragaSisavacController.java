package com.example.projektni;


import Entiteti.Sisavci;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

import java.util.ArrayList;

public class PretragaSisavacController {
    @FXML private ChoiceBox<String> choiceBox;
    @FXML private Button izmijeniButton;
    @FXML private Button obrisiButton;
    @FXML private   TableView<Sisavci> SisavciTableView;
    @FXML private TableColumn<Sisavci,String> imeTableColumn;
    @FXML private TableColumn<Sisavci,String> prehranaTableColumn;
    @FXML private TableColumn<Sisavci,String> stanisteTableColumn;
    @FXML private TableColumn<Sisavci,String> tezinaTableColumn;
    @FXML private TableColumn<Sisavci,String> bojaKrznaTableColumn;

    public void initialize() {




    }
    public void Obrisi(){
        //obrisi odabranu zivotinju iz table view
    }
    public void Izmijeni(){
        //otvori ekran za izmjenu
    }

}
