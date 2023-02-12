package com.example.projektni;

import BazaPodataka.BazaPodataka;
import Entiteti.*;
import Iznimke.NepotpunUnosException;
import datoteke.Promjene;
import datoteke.ZapisPromjene;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.projektni.LoginFormController.upozorenje;

public class EditSisavacController {
    public static Sisavci stari;
    Sisavci novi;

    @FXML private TextField imeTextfield;
    @FXML private ChoiceBox<Prehrana> prehranachoiceBox;
    @FXML private ChoiceBox<Staniste> stanistechoiceBox;
    @FXML private TextField tezinaTextfield;
    @FXML private TextField bojaKrznaTextfield;

    private String ime1;
    private String bojaKrzna1;
    private float tezina1;
    private Prehrana hrana1;
    private Staniste staniste1;

    public void initialize(){
        if(Optional.ofNullable(stari).isPresent()){
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

            bojaKrznaTextfield.setText(stari.getBojaKrzna());
        }else {

            prehranachoiceBox.getItems().add(Prehrana.valueOf("Biljojed"));
            prehranachoiceBox.getItems().add(Prehrana.valueOf("Mesojed"));
            prehranachoiceBox.getItems().add(Prehrana.valueOf("Svejed"));
            prehranachoiceBox.setValue(Prehrana.Biljojed);

            stanistechoiceBox.getItems().add(Staniste.valueOf("Grad"));
            stanistechoiceBox.getItems().add(Staniste.valueOf("More"));
            stanistechoiceBox.getItems().add(Staniste.valueOf("RijekaiJezera"));
            stanistechoiceBox.getItems().add(Staniste.valueOf("Planina"));
            stanistechoiceBox.getItems().add(Staniste.valueOf("Suma"));
            stanistechoiceBox.getItems().add(Staniste.valueOf("Mocvara"));
            stanistechoiceBox.getItems().add(Staniste.valueOf("VodenaStanista"));
            stanistechoiceBox.setValue(Staniste.Planina);
        }

    }

    public void dodaj() throws SQLException, IOException {


        try {
            ime1=imeTextfield.getText();
            bojaKrzna1= bojaKrznaTextfield.getText();
            tezina1=Float.parseFloat(tezinaTextfield.getText());
            hrana1=prehranachoiceBox.getValue();
            staniste1=stanistechoiceBox.getValue();
            if (ime1.isEmpty()||bojaKrzna1.isEmpty()||tezinaTextfield.getText().isEmpty()){
                throw new NepotpunUnosException("nepotpun unos kod edita sisavca");
            }else {
                novi= new Sisavci(ime1,hrana1,staniste1,tezina1,bojaKrzna1);
            }

        } catch (NumberFormatException e) {
            upozorenje("nepotpun unos");
            System.out.println("nepotpun unos");;
        } catch (NepotpunUnosException e ) {
            throw new RuntimeException(e);
        }
        BazaPodataka.editSisavac(stari,novi);
        ZapisPromjene.dodajPromjenu(
                new Promjene(BazaPodataka.trenutniUser, LocalDateTime.now(),"promjenjen sisavac "+stari.getIme() +" "+stari.getHrana() +" "+stari.getStaniste() +" "+stari.getTezina() +" " +stari.getBojaKrzna() +
                        " u" +" "+ime1 +" "+hrana1 +" "+staniste1 +" "+tezina1 +" " +bojaKrzna1)
        );
        upozorenje("uspjesno");



    }
    public void nazad() throws IOException {
        IzbornikController a= new IzbornikController();
        a.pregledSisavacScreen();
    }
    public void dodajNovu() throws SQLException, IOException {

        Sisavci tempSisavac = new Builder()
                .withIme(imeTextfield.getText())
                .withPrehrana(prehranachoiceBox.getValue())
                .withStaniste(stanistechoiceBox.getValue())
                .withTezina(Float.parseFloat(tezinaTextfield.getText()))
                .withBojaKrzna(bojaKrznaTextfield.getText())
                .buildSisavac();
        BazaPodataka.dodajSisavca(tempSisavac);

        ZapisPromjene.dodajPromjenu(
                new Promjene(BazaPodataka.trenutniUser, LocalDateTime.now(),"dodan sisavac "+imeTextfield.getText() +" "+prehranachoiceBox.getValue().toString()
                                                                                                +" "+stanistechoiceBox.getValue().toString() +" "+tezinaTextfield.getText()
                                                                                                +" " +bojaKrznaTextfield.getText())
        );


    }

}
