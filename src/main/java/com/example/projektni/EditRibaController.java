package com.example.projektni;

import BazaPodataka.BazaPodataka;
import Entiteti.*;
import Iznimke.NepotpunUnosException;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.projektni.LoginFormController.upozorenje;

public class EditRibaController {
    public static Ribe stari;
    Ribe novi;

    @FXML
    private TextField imeTextfield;
    @FXML private ChoiceBox<Prehrana> prehranachoiceBox;
    @FXML private ChoiceBox<Staniste> stanistechoiceBox;
    @FXML private TextField tezinaTextfield;
    @FXML private ChoiceBox<Voda> vodaChoiceBox;

    private String ime1;
    private Voda voda1;
    private float tezina1;
    private Prehrana hrana1;
    private Staniste staniste1;

    public void initialize(){
        imeTextfield.setText(stari.getIme());

        prehranachoiceBox.getItems().add(Prehrana.valueOf("Biljojed"));
        prehranachoiceBox.getItems().add(Prehrana.valueOf("Mesojed"));
        prehranachoiceBox.getItems().add(Prehrana.valueOf("Svejed"));
        prehranachoiceBox.setValue(stari.getHrana());

        stanistechoiceBox.getItems().add(Staniste.valueOf("Grad"));
        stanistechoiceBox.getItems().add(Staniste.valueOf("More"));
        stanistechoiceBox.getItems().add(Staniste.valueOf("RijekaiJezera"));
        stanistechoiceBox.getItems().add(Staniste.valueOf("Planina"));
        stanistechoiceBox.getItems().add(Staniste.valueOf("Suma"));
        stanistechoiceBox.getItems().add(Staniste.valueOf("Mocvara"));
        stanistechoiceBox.getItems().add(Staniste.valueOf("VodenaStanista"));
        stanistechoiceBox.setValue(stari.getStaniste());

        tezinaTextfield.setText(String.valueOf(stari.getTezina()));

        vodaChoiceBox.getItems().add(Voda.valueOf("Slatkovodno"));
        vodaChoiceBox.getItems().add(Voda.valueOf("Morsko"));
        vodaChoiceBox.setValue(stari.getVoda());

    }

    public void dodaj() throws SQLException, IOException {


        try {
            ime1=imeTextfield.getText();
            voda1= (vodaChoiceBox.getValue());
            tezina1=Float.parseFloat(tezinaTextfield.getText());
            hrana1=prehranachoiceBox.getValue();
            staniste1=stanistechoiceBox.getValue();
            if (ime1.isEmpty() ||tezinaTextfield.getText().isEmpty()){
                throw new NepotpunUnosException("nepotpun unos kod edita ribe");
            }else {
                novi= new Ribe(ime1,hrana1,staniste1,tezina1,voda1);
            }

        } catch (NumberFormatException e) {
            upozorenje("nepotpun unos");
            System.out.println("nepotpun unos kod edita");;
        } catch (NepotpunUnosException e ) {
            throw new RuntimeException(e);
        }
        BazaPodataka.editRiba(stari,novi);
        upozorenje("uspjesno");

        //todo zapis u serijalizirani file promjenu


    }
    public void nazad() throws IOException {
        IzbornikController a= new IzbornikController();
        a.pregledRibaScreen();
    }
}
