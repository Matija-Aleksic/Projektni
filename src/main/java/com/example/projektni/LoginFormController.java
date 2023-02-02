package com.example.projektni;

import BazaPodataka.BazaPodataka;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button LoginButton;

    private void login(){
        String ime=username.getText();
        String sifra = password.getText();
        try {
            BazaPodataka.ProvjeriDaliPostoji(ime,sifra);
        } catch (SQLException e) {
            upozorenje("nije moguce povezivanje na bazu");
        } catch (IOException e) {
            upozorenje("file nije pronaden");
        }
    }

    public static void upozorenje(String a) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(a);
        alert.showAndWait();
    }
}
