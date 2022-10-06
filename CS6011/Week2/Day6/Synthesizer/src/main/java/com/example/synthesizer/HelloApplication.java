//package com.example.synthesizer;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.stage.Stage;
//
//import javax.sound.sampled.AudioFormat;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
//import javax.sound.sampled.LineUnavailableException;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class HelloApplication extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//
//        BorderPane root = new BorderPane();
//        Scene scene = new Scence(root, 600, 400);
//
//        stage.setTitle("My Synth");
//
//        // right
//        VBox rightPanel = new VBox();
//        rightPanel.setPadding(new Insets(4));
//        rightPanel.setStyle("-fx-background-color: darkgray");
//
//        Button sineWaveBtn = new Button("Since Wave");
//        rightPanel.getChildren().add(sineWaveBtn);
//        sineWaveBtn.setOnAction(e -> createComponent("SineWave"));
//
//        // center panel stuff:
//        mainCanvas_ = new AnchorPane();
//        mainCanvas_.setStyle("-fx-background-color: darkgray");
//
//        Circle speakerCircle = new Circle(400, 200, 15);
//        speakerCircle.setFill(Color.BLACK);
//        mainCanvas_.getChildren().add(speakerCircle);
//
//        // bottom panel stuff:
//        HBox bottomPanel = new HBox();
//        Button playBtn = new Button("Play");
//        bottomPanel.getChildren().add(playBtn);
//
//        // put the panels into te BorderPane (root)
//        root.setRight(rightPanel);
//        root.setCenter(mainCanvas_);
//
//        // last thing to do...
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    private void playNetwork() {
//        if(widgets_.size() == 0) {
//            return;
//        }
//        try {
//
//            AudioListener listener = new AudioListener(c);
//
//            Clip c = AudioSystem.getClip();
//            AudioFormat format = new AudioFormat(44100, 16, 1, true, false);
//
//            AudioComponentWidget acw = widgets_.get(0);
//            AudioComponent ac = acw.getAudioComponent();
//            byte[] data = ac.getClip().getData();
//
//            c.open(format, data, 0, data.length);
//            c.start();
//            c.addLineListener(listener);
//            while (!done with c ) {
//
//            }
//        } catch (LineUnavailableException e) {
//
//        };
//    }
//
//    private void createComponent(String sineWave) {
//
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//    private AnchorPane mainCanvas_;
//    public static Circle speaker_;
//
//    public static ArrayList<AudioComponentWidget> widgets_ = new ArrayList<>(); // private is safer
//}