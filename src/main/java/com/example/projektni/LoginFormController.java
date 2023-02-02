package com.example.projektni;

import BazaPodataka.BazaPodataka;
import Iznimke.NepotpunUnosException;
import Loger.Log;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button LoginButton;
    @FXML
    private void login() throws NepotpunUnosException {
        String ime=username.getText();
        String sifra = password.getText();
        try {
            if (sifra.isEmpty() || ime.isEmpty()) {
                upozorenje("nepotpun unos");
                throw new NepotpunUnosException("nepotpun unos kod logina");
            }
        }catch(NepotpunUnosException e){
            Log.info("nepotpun unos kod logina");
        }
        try {
            BazaPodataka.ProvjeriDaliPostoji(ime,sifra);
            if (BazaPodataka.isAdmin == 0 || BazaPodataka.isAdmin==1){
                //otvori noi prozor
                upozorenje("uspijeh");
            }
        } catch (SQLException e) {
            upozorenje("nije moguce povezivanje na bazu");
            Log.error("nije moguce spajanje na bazu");

        } catch (IOException e) {
            upozorenje("file nije pronaden");
            Log.error("nije moguce naci properties file");
        }
    }

    public static void upozorenje(String a) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(a);
        alert.showAndWait();
    }


}
