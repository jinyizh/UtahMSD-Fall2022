package com.example.synthesizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        VBox componentWidget = new VBox();
//        componentWidget.setStyle("-fx-background-color: lightblue");
//        Label title = new Label();
//        title.setText("Sine wave");
//        Slider slider = new Slider(220, 880, 440);
////        slider.setOnMouseDragged();
//        componentWidget.getChildren().add(title);
//        componentWidget.getChildren().add(slider);
//        componentWidget.relocate(50, 100);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
    }

//    private void handleSlider(MouseEvent e, Slider slider, Lable title) {
//        int value = (int) slider.getValue();
//        title.setText("Sine wave (" + value + "Hz)");
//    }

    private void handleMousePress(MouseEvent e) {
        System.out.println("mouse pressed");
    }

    public static void main(String[] args) {
        launch();
    }
}