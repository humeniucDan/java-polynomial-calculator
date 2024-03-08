package org.humeniuc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;

public class GUIApplication extends Application {
    private static final int yRez= 720, xRez = 1280;
    private static final String title = "Polynomial Calculator";
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), xRez, yRez);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Polynomial Calculator");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();

        InputStream icoResource = GUIApplication.class.getResourceAsStream("imgs/math_ico.png");
        Image ico = null;
        if(icoResource != null) ico = new Image(icoResource);
        if(ico != null) stage.getIcons().add(ico);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}