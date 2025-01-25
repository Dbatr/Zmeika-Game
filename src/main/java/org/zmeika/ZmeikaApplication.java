package org.zmeika;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ZmeikaApplication extends Application {
    @Override
    public void start(Stage stage) {
        GameField gameField = new GameField();
        StackPane root = new StackPane();
        root.getChildren().add(gameField);
        Scene scene = new Scene(root, 640, 640);

        stage.setScene(scene);
        stage.setTitle("Змейка");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}