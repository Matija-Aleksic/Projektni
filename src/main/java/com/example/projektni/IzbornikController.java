package com.example.projektni;

import BazaPodataka.BazaPodataka;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class IzbornikController {

    public void loginScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("loginScreen!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
        BazaPodataka.trenutniUser = null;
        BazaPodataka.isAdmin=0;
    }
    public void pregledSisavacScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PretragaSisavac.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("pregledSisavacScreen!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void editSisavacScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EditSisavac.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("editSisavacScreen!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    public void pregledPticaScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PretragaPtica.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("pregledPticaScreen!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void editPticaScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EditPtica.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("editPticaScreen!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    public void pregledRibaScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PretragaRiba.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("pregledRibaScreen!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void editRibaScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EditRiba.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("editRibaScreen!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void prikazPromjenaScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PrikazPromjena.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("prikazPromjenaScreen!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
}
