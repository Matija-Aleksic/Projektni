package com.example.projektni;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import threads.SaveThread;
import threads.Ucitajtthread;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.projektni.PrikazPromjenaController.promjenes;
import static datoteke.ZapisPromjene.loadChangeLogs;

public class HelloApplication extends Application {
    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void main(String[] args) {

        Timeline loop = new Timeline(
                new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Platform.runLater(new SaveThread());
                        Platform.runLater(new Ucitajtthread());
                    }
                }));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();

        SaveThread a = new SaveThread();
        Ucitajtthread b = new Ucitajtthread();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(b);
        executorService.execute(a);
        promjenes.addAll(loadChangeLogs());

        System.out.println("pokrenut thread");
        launch();
    }
}