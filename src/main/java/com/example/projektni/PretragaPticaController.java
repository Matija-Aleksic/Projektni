package com.example.projektni;

import BazaPodataka.BazaPodataka;
import Entiteti.Prehrana;
import Entiteti.Ptice;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.projektni.LoginFormController.upozorenje;


public class PretragaPticaController {
    Alert ab = new Alert(Alert.AlertType.CONFIRMATION);
    @FXML
    private ChoiceBox<String> prehranachoiceBox;
    @FXML
    private ChoiceBox<String> stanistechoiceBox;
    @FXML
    private Button izmijeniButton;
    @FXML
    private Button obrisiButton;
    @FXML
    private TableView<Ptice> pticeTableView;
    @FXML
    private TableColumn<Ptice, String> imeTableColumn;
    @FXML
    private TableColumn<Ptice, String> prehranaTableColumn;
    @FXML
    private TableColumn<Ptice, String> stanisteTableColumn;
    @FXML
    private TableColumn<Ptice, String> tezinaTableColumn;
    @FXML
    private TableColumn<Ptice, String> rasponKrilaTableColumn;
    @FXML
    private TextField imeTextField;
    @FXML
    private TextField tezinaTextField;
    @FXML
    private TextField sirinakrilaTextField;

    public void initialize() throws SQLException, IOException {
        ab.setContentText("Jeste Li sigurni?");
        List<Ptice> popis = new ArrayList<>();
        popis = BazaPodataka.dohvatiptice("null", null, null, 0, 0);
        List<Ptice> sortedList = popis.stream()
                .sorted(Comparator.comparing(Ptice::getIme))
                .collect(Collectors.toList());
        imeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIme()));
        prehranaTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHrana().toString()));
        stanisteTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStaniste().toString()));
        tezinaTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTezinaString()));
        rasponKrilaTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSirinaKrila()));
        pticeTableView.setItems(FXCollections.observableList(sortedList));

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
        ab.showAndWait();
        if (ab.getResult() == ButtonType.OK) {
            if (BazaPodataka.isAdmin == 1) {
                Ptice ptice = pticeTableView.getSelectionModel().getSelectedItem();
                BazaPodataka.obrisiptice(ptice);
                ZapisPromjene.dodajPromjenu(
                        new Promjene(BazaPodataka.trenutniUser, LocalDateTime.now(), "obrisana ptica" + ptice.getIme() + " " + ptice.getHrana().toString() + " " + ptice.getStaniste().toString() + " " + ptice.getTezina() + " " + ptice.getSirinaKrila())
                );
                LoginFormController.upozorenje("uspjesno");
                List<Ptice> temp;
                //da se refresha
                temp = BazaPodataka.dohvatiptice("null", null, null, 0, 0);
                pticeTableView.setItems(FXCollections.observableList(temp));
            } else {
                upozorenje("nemate admin privilegije");
            }
        }

    }

    public void Izmijeni() throws IOException {
        ab.showAndWait();
        if (ab.getResult() == ButtonType.OK) {
            if (BazaPodataka.isAdmin == 1) {
                IzbornikController a = new IzbornikController();

                if (pticeTableView.getSelectionModel().isEmpty()) {
                    a.editPticaScreen();
                } else {
                    EditPticaController.stari = pticeTableView.getSelectionModel().getSelectedItem();
                }
                a.editPticaScreen();
            } else {
                upozorenje("nemate admin privilegije");
            }
        }
    }

    public void pretraga() throws SQLException, IOException {
        String ime = "null";
        int sirinakrila = 0;
        Prehrana prehrana = null;
        Staniste staniste = null;
        if (!imeTextField.getText().isEmpty()) {
            ime = imeTextField.getText();
        }
        float tezina = 0;
        if (!tezinaTextField.getText().isEmpty()) {
            tezina = Float.parseFloat(tezinaTextField.getText());
        }

        if (!sirinakrilaTextField.getText().isEmpty()) {
            sirinakrila = Integer.parseInt(sirinakrilaTextField.getText());
        }
        if (prehranachoiceBox.getValue() != "") {
            prehrana = Prehrana.valueOf(prehranachoiceBox.getValue());
        }
        if (stanistechoiceBox.getValue() != "") {
            staniste = Staniste.valueOf(stanistechoiceBox.getValue());
        }
        List<Ptice> temp = new ArrayList<>();
        temp = BazaPodataka.dohvatiptice(ime, prehrana, staniste, tezina, sirinakrila);
        pticeTableView.setItems(FXCollections.observableList(temp));
    }

}
