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
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.projektni.LoginFormController.upozorenje;

public class EditPticaController {
    public static Ptice stari;
    Ptice novi;

    @FXML
    private TextField imeTextfield;
    @FXML private ChoiceBox<Prehrana> prehranachoiceBox;
    @FXML private ChoiceBox<Staniste> stanistechoiceBox;
    @FXML private TextField tezinaTextfield;
    @FXML private TextField rasponkrilaTextfield;

    private String ime1;
    private String rasponkrila1;
    private float tezina1;
    private Prehrana hrana1;
    private Staniste staniste1;

    public void initialize(){
        if (Optional.ofNullable(stari).isPresent()) {
            imeTextfield.setText(stari.getIme());


            stanistechoiceBox.setValue(stari.getStaniste());

            tezinaTextfield.setText(String.valueOf(stari.getTezina()));

            rasponkrilaTextfield.setText(stari.getSirinaKrila());
        }
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
        stanistechoiceBox.setValue(Staniste.Grad);

    }

    public void dodaj() throws SQLException, IOException {


        try {
            ime1=imeTextfield.getText();
            rasponkrila1= (rasponkrilaTextfield.getText());
            tezina1=Float.parseFloat(tezinaTextfield.getText());
            hrana1=prehranachoiceBox.getValue();
            staniste1=stanistechoiceBox.getValue();
            if (ime1.isEmpty() ||tezinaTextfield.getText().isEmpty()){
                throw new NepotpunUnosException("nepotpun unos kod edita ptica");
            }else {
                novi= new Ptice(ime1,hrana1,staniste1, (int) tezina1,rasponkrila1);
            }

        } catch (NumberFormatException e) {
            upozorenje("nepotpun unos");
            System.out.println("nepotpun unos");
        } catch (NepotpunUnosException e ) {
            throw new RuntimeException(e);
        }
        BazaPodataka.editPtica(stari,novi);
        ZapisPromjene.dodajPromjenu(
                new Promjene(BazaPodataka.trenutniUser, LocalDateTime.now(),"promjenjena ptica "+stari.getIme() +" "+stari.getHrana() +" "+stari.getStaniste() +" "+stari.getTezina() +" " +stari.getSirinaKrila() +
                        " u" +" "+ime1 +" "+hrana1 +" "+staniste1 +" "+tezina1 +" " +rasponkrila1)
        );
        upozorenje("uspjesno");



    }
    public void nazad() throws IOException {
        IzbornikController a= new IzbornikController();
        a.pregledPticaScreen();
    }

    public void dodajNovu() throws SQLException, IOException {

        Ptice tempPtica = new Builder()
        .withIme(imeTextfield.getText())
        .withPrehrana(prehranachoiceBox.getValue())
        .withStaniste(stanistechoiceBox.getValue())
        .withTezina(Float.parseFloat(tezinaTextfield.getText()))
        .withSirinaKrila(rasponkrilaTextfield.getText().toString())
        .buildPtica();
        ZapisPromjene.dodajPromjenu(
                new Promjene(BazaPodataka.trenutniUser, LocalDateTime.now(),"Dodana nova ptica "+imeTextfield.getText() +" "+prehranachoiceBox.getValue().toString() +
                                                                                                    " "+stanistechoiceBox.getValue().toString() +" "+tezinaTextfield.getText()
                                                                                                    +" " +rasponkrilaTextfield.getText().toString())
        );
        BazaPodataka.dodajPtice(tempPtica);
        System.out.println("zapis u file");
        ZapisPromjene.save();


    }
}
