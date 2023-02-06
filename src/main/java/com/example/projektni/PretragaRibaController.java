package com.example.projektni;

import BazaPodataka.BazaPodataka;
import Entiteti.Prehrana;
import Entiteti.Ribe;
import Entiteti.Staniste;
import Entiteti.Voda;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.projektni.LoginFormController.upozorenje;

public class PretragaRibaController {
    @FXML
    private ChoiceBox<String> prehranachoiceBox;
    @FXML private ChoiceBox<String> stanistechoiceBox;
    @FXML private Button izmijeniButton;
    @FXML private Button obrisiButton;
    @FXML private TableView<Ribe> ribeTableView;
    @FXML private TableColumn<Ribe,String> imeTableColumn;
    @FXML private TableColumn<Ribe,String> prehranaTableColumn;
    @FXML private TableColumn<Ribe,String> stanisteTableColumn;
    @FXML private TableColumn<Ribe,String> tezinaTableColumn;
    @FXML private TableColumn<Ribe,String> vodaTableColumn;
    @FXML private TextField imeTextField;
    @FXML private TextField tezinaTextField;
    @FXML private ChoiceBox<String> vodaChoiceBox;
    public void initialize() throws SQLException, IOException {
        List<Ribe> popis = new ArrayList<>();
        popis= BazaPodataka.dohvatiRibe("null",null,null,0,null);
        imeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIme()));
        prehranaTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHrana().toString()));
        stanisteTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStaniste().toString()));
        tezinaTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTezinaString()));
        vodaTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVoda().toString()));
        ribeTableView.setItems(FXCollections.observableList(popis));

        prehranachoiceBox.getItems().add(("Biljojed"));
        prehranachoiceBox.getItems().add(("Mesojed"));
        prehranachoiceBox.getItems().add(("Svejed"));
        prehranachoiceBox.getItems().add((""));
        prehranachoiceBox.setValue((""));

        stanistechoiceBox.getItems().add("More");
        stanistechoiceBox.getItems().add("RijekaiJezera");
        stanistechoiceBox.getItems().add("Mocvara");
        stanistechoiceBox.getItems().add("VodenaStanista");
        stanistechoiceBox.getItems().add("");
        stanistechoiceBox.setValue("");

        vodaChoiceBox.getItems().add("Slatkovodno");
        vodaChoiceBox.getItems().add("Morsko");
        vodaChoiceBox.getItems().add("");
        vodaChoiceBox.setValue("");

    }
    public void Obrisi() throws SQLException, IOException {

        if (BazaPodataka.isAdmin==1){
            Ribe ribe= ribeTableView.getSelectionModel().getSelectedItem();
            BazaPodataka.obrisiRibu(ribe);
            LoginFormController.upozorenje("uspjesno");
            List<Ribe>temp;
            //da se refresha
            temp=BazaPodataka.dohvatiRibe("null",null,null,0,null);
            ribeTableView.setItems(FXCollections.observableList(temp));
        }else {
            upozorenje("nemate admin privilegije");
        }

    }

    public void Izmijeni() throws IOException {
        if (BazaPodataka.isAdmin==1){
            IzbornikController a = new IzbornikController();

            if (ribeTableView.getSelectionModel().isEmpty()){
                upozorenje("nije odabrana zivotinja");
            }else{
                EditRibaController.stari= ribeTableView.getSelectionModel().getSelectedItem();
            }
            a.editRibaScreen();
        }else {
            upozorenje("nemate admin privilegije");
        }
    }
    public void pretraga() throws SQLException, IOException {
        String ime = "null";
        Voda voda = null;
        Prehrana prehrana = null;
        Staniste staniste= null;
        if (!imeTextField.getText().isEmpty()) {
            ime = imeTextField.getText();
        }
        float tezina = 0;
        if (!tezinaTextField.getText().isEmpty()) {
            tezina = Float.parseFloat(tezinaTextField.getText());
        }

        if (vodaChoiceBox.getValue() != "") {
            voda = Voda.valueOf(vodaChoiceBox.getValue());
        }
        if (prehranachoiceBox.getValue()!=""){
            prehrana= Prehrana.valueOf(prehranachoiceBox.getValue());
        }
        if (stanistechoiceBox.getValue() !="") {
            staniste= Staniste.valueOf(stanistechoiceBox.getValue());
        }
        List<Ribe> temp = new ArrayList<>();
        temp= BazaPodataka.dohvatiRibe(ime,prehrana,staniste,tezina, voda);
        ribeTableView.setItems(FXCollections.observableList(temp));
    }


}
