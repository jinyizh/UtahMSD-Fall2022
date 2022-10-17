package com.example.synthesizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.util.ArrayList;

public class SynthesizeApplication extends Application {
    private AnchorPane mainCanvas_;
    public static Circle speaker_;
    public static ArrayList<AudioComponentWidget> widgets_ = new ArrayList<>(); // list of widgets; private is safer

    @Override
    public void start(Stage stage) throws IOException {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("My Synthesizer");

        // right
        VBox rightPanel = new VBox();
        rightPanel.setPadding(new Insets(5));
        rightPanel.setStyle("-fx-background-color: lightblue");
        rightPanel.setSpacing(5);

        Button sineWaveBtn = new Button("Since Wave");
        rightPanel.getChildren().add(sineWaveBtn);
        sineWaveBtn.setOnAction(e -> createComponent("SineWave"));

        // center panel stuff:
        mainCanvas_ = new AnchorPane();
        mainCanvas_.setStyle("-fx-background-color: white");

        Circle speakerCircle = new Circle(450, 50, 10);
        speakerCircle.setFill(Color.BLACK);
        mainCanvas_.getChildren().add(speakerCircle); // add to main canvas

        // bottom panel stuff:
        HBox bottomPanel = new HBox();
        Button playBtn = new Button("Play");
        playBtn.setOnAction(e -> playNetwork());
        bottomPanel.getChildren().add(playBtn);

        // put the panels into te BorderPane (root)
        root.setRight(rightPanel);
        root.setCenter(mainCanvas_);
        root.setBottom(bottomPanel);

        // last thing to do...
        stage.setScene(scene);
        stage.show();
    }

    private void playNetwork() {
        if(widgets_.size() == 0) {
            return;
        }
        // get properties from the sys about sample rates, etc.
        try {
            Clip c = AudioSystem.getClip();
            AudioListener listener = new AudioListener(c);

            Mixer mixer = new Mixer();
            for (AudioComponentWidget w : widgets_) {
                AudioComponent ac = w.getAudioComponent();
                mixer.connectInput(ac);
            }

            AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);

            // without mixer:
//            AudioComponentWidget acw = widgets_.get(0);
//            AudioComponent ac = acw.getAudioComponent();
//            byte[] data = ac.getClip().getData();

            // with mixer:
            byte[] data = mixer.getClip().getData();

            c.open(format16, data, 0, data.length);
            c.start();
            c.addLineListener(listener);
//            while (c.getFramePosition() < AudioClip.totalSample || c.isActive() || c.isRunning()) {
//                // do nothing
//            }
        } catch (LineUnavailableException e) {

        };
    }

    private void createComponent(String sineWave) {
        System.out.println("creating component");
        AudioComponent sw = new SineWave(440);
        AudioComponentWidget acw = new AudioComponentWidget(sw, mainCanvas_, "Sine Wave (440 Hz)");
        widgets_.add(acw);
    }

    public static void main(String[] args) {
        launch();
    }
}