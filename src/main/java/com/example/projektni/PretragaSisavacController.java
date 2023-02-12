package com.example.projektni;


import BazaPodataka.BazaPodataka;
import Entiteti.Prehrana;
import Entiteti.Sisavci;
import Entiteti.Staniste;
import datoteke.Promjene;
import datoteke.ZapisPromjene;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;



import static com.example.projektni.LoginFormController.upozorenje;

public class PretragaSisavacController {
    @FXML private ChoiceBox<String> prehranachoiceBox;
    @FXML private ChoiceBox<String> stanistechoiceBox;
    @FXML private Button izmijeniButton;
    @FXML private Button obrisiButton;
    @FXML private   TableView<Sisavci> SisavciTableView;
    @FXML private TableColumn<Sisavci,String> imeTableColumn;
    @FXML private TableColumn<Sisavci,String> prehranaTableColumn;
    @FXML private TableColumn<Sisavci,String> stanisteTableColumn;
    @FXML private TableColumn<Sisavci,String> tezinaTableColumn;
    @FXML private TableColumn<Sisavci,String> bojaKrznaTableColumn;
    @FXML private TextField imeTextField;
    @FXML private TextField tezinaTextField;
    @FXML private TextField bojaKrznaTextField;
    public void initialize() throws SQLException, IOException {
        List<Sisavci> popis = new ArrayList<>();
        popis=BazaPodataka.dohvatiSisavce("null",null,null,0,"null");
        imeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIme()));
        popis.stream().sorted()
                        .count();
        prehranaTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHrana().toString()));
        stanisteTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStaniste().toString()));
        tezinaTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTezinaString()));
        bojaKrznaTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBojaKrzna()));
        SisavciTableView.setItems(FXCollections.observableList(popis));

        prehranachoiceBox.getItems().add("Biljojed");
        prehranachoiceBox.getItems().add("Mesojed");
        prehranachoiceBox.getItems().add("Svejed");
        prehranachoiceBox.getItems().add("");
        prehranachoiceBox.setValue("");

        stanistechoiceBox.getItems().add("Grad");
        stanistechoiceBox.getItems().add("More");
        stanistechoiceBox.getItems().add("RijekaiJezera");
        stanistechoiceBox.getItems().add("Planina");
        stanistechoiceBox.getItems().add("Suma");
        stanistechoiceBox.getItems().add("Mocvara");
        stanistechoiceBox.getItems().add("VodenaStanista");
        stanistechoiceBox.getItems().add("");
        stanistechoiceBox.setValue("");

    }
    public void Obrisi() throws SQLException, IOException {

        if (BazaPodataka.isAdmin==1){
            Sisavci sisavci= SisavciTableView.getSelectionModel().getSelectedItem();
            BazaPodataka.obrisi(sisavci);
            LoginFormController.upozorenje("uspjesno");
            List<Sisavci>temp;
            ZapisPromjene.dodajPromjenu(
                    new Promjene(BazaPodataka.trenutniUser, LocalDateTime.now(),"Obrisan Sisavac "+sisavci.getIme() +" "+sisavci.getHrana().toString() +" "+sisavci.getStaniste().toString() +" "+sisavci.getTezina() +" " +sisavci.getBojaKrzna())
            );
            //da se refresha
            temp=BazaPodataka.dohvatiSisavce("null",null,null,0,"null");
            SisavciTableView.setItems(FXCollections.observableList(temp));
        }else {
            upozorenje("nemate admin privilegije");
        }

    }
    public void Izmijeni() throws IOException {
        if (BazaPodataka.isAdmin==1){
            IzbornikController a = new IzbornikController();

            if (SisavciTableView.getSelectionModel().isEmpty()){
                a.editSisavacScreen();
            }else{
                EditSisavacController.stari= SisavciTableView.getSelectionModel().getSelectedItem();
            }
            a.editSisavacScreen();
        }else {
            upozorenje("nemate admin privilegije");
        }
    }
    public void pretraga() throws SQLException, IOException {
        String ime;
        String bojaKrzna="null";
        Prehrana prehrana;
        Staniste staniste= null;
        if (!imeTextField.getText().isEmpty()) {
            ime = imeTextField.getText();
        } else {
            ime = "null";
        }
        float tezina = 0;
        if (!tezinaTextField.getText().isEmpty()) {
            tezina = Float.parseFloat(tezinaTextField.getText());
        }

        if (!bojaKrznaTextField.getText().isEmpty()) {
            bojaKrzna = bojaKrznaTextField.getText();
        }
        if (prehranachoiceBox.getValue()!="") {
            prehrana = Prehrana.valueOf(prehranachoiceBox.getValue());
        } else {
            prehrana = null;
        }
        if (stanistechoiceBox.getValue() !="") {
            staniste= Staniste.valueOf(stanistechoiceBox.getValue());
        }
        List<Sisavci> temp = new ArrayList<>();
        temp= BazaPodataka.dohvatiSisavce(ime,prehrana,staniste,tezina,bojaKrzna);
        SisavciTableView.setItems(FXCollections.observableList(temp));
    }



}
