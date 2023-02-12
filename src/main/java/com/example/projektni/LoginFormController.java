package com.example.projektni;

import BazaPodataka.BazaPodataka;
import Iznimke.NepotpunUnosException;
import Loger.Log;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class LoginFormController {
    private static final String DATABASE_FILE = "src/main/resources/Properties";

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button LoginButton;
    @FXML
    private void login() throws NepotpunUnosException, IOException {
        String ime = username.getText();
        String sifra = password.getText();
        try {
            if (sifra.isEmpty() || ime.isEmpty()) {
                upozorenje("nepotpun unos");
                throw new NepotpunUnosException("nepotpun unos kod logina");
            }
        } catch (NepotpunUnosException e) {
            Log.info("nepotpun unos kod logina");
        }
        String korisnikpass;
        String adminpass;
        try {
            Properties svojstva = new Properties();
            svojstva.load(new FileReader(DATABASE_FILE));
            adminpass = svojstva.getProperty("admin");
            korisnikpass = svojstva.getProperty("korisnik");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        IzbornikController a = new IzbornikController();
        if (BCrypt.checkpw(sifra, korisnikpass)) {
            System.out.println("Password matches korisnik");
            BazaPodataka.trenutniUser = ime;
            BazaPodataka.isAdmin = 0;
            a.pregledSisavacScreen();
        } else if (BCrypt.checkpw(sifra, adminpass)) {
            BazaPodataka.trenutniUser = ime;
            BazaPodataka.isAdmin = 1;
            System.out.println("Password matches admin");
            a.pregledSisavacScreen();
        } else {
            upozorenje("krivi username ili sifra");
            System.out.println("Password does not match");
        }
    }

    public static void upozorenje(String a) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(a);
        alert.showAndWait();
    }

}
