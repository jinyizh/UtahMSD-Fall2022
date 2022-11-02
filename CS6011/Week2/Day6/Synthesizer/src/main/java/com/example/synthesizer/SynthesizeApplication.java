package com.example.synthesizer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
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
    public static ArrayList<AudioComponentWidget> widgets_ = new ArrayList<>(); // list of widgets connected to speaker
    public static ArrayList<AudioComponentWidget> widgetList_ = new ArrayList<>(); // widgets that have both in/out btns
    public int volume = 100;
    public Mixer mixer;

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Jinyi Zhou's Synthesizer");
        // right panel
        VBox rightPanel = new VBox();
        rightPanel.setPadding(new Insets(5));
        rightPanel.setStyle("-fx-background-color: blue");
        rightPanel.setSpacing(5);
        // buttons (list of audio component widgets)
        Button sineWaveBtn = new Button("Sine Wave");
        rightPanel.getChildren().add(sineWaveBtn);
        sineWaveBtn.setOnAction(e -> createSineWave("Sine Wave (440 Hz)"));
        Button squareWaveBtn = new Button("Square Wave");
        rightPanel.getChildren().add(squareWaveBtn);
        squareWaveBtn.setOnAction((e -> createSquareWave("Square Wave (440 Hz)")));
        Button linearRampBtn = new Button("Linear Ramp");
        rightPanel.getChildren().add(linearRampBtn);
        linearRampBtn.setOnAction(e -> createLinearRamp("Linear Ramp (start: 50 Hz, stop: 2000 Hz)"));
        Button vfSineWaveBtn = new Button("VF Sine Wave");
        rightPanel.getChildren().add(vfSineWaveBtn);
        vfSineWaveBtn.setOnAction(e -> createVFSineWave("VF Sine Wave"));
        Button filterBtn = new Button("Filter");
        rightPanel.getChildren().add(filterBtn);
        filterBtn.setOnAction((e -> createFilter("Filter (scale: 1.00)")));
        Button mixerBtn = new Button("Mixer");
        rightPanel.getChildren().add(mixerBtn);
        mixerBtn.setOnAction((e -> createMixer("Mixer")));
        root.setRight(rightPanel);

        // center panel stuff
        mainCanvas_ = new AnchorPane();
        mainCanvas_.setStyle("-fx-background-color: lightblue");
        // speaker
        Circle speaker = new Circle(350, 50, 10);
        speaker_ = speaker;
        speaker.setFill(Color.BLACK);
        mainCanvas_.getChildren().add(speaker_); // add to main canvas
        root.setCenter(mainCanvas_);

        // bottom panel stuff
        HBox bottomPanel = new HBox();
        // play button
        Button playBtn = new Button("Play");
        playBtn.setOnAction(e -> playNetwork());
        // volume slider
        Slider volumeSlider = new Slider(0, 100, 100);
        Label volumeLabel = new Label();
        volumeLabel.setText("Volume: 100%");
        volumeSlider.setOnMouseDragged(e -> handleVolume(e, volumeSlider, volumeLabel));
        bottomPanel.getChildren().add(playBtn);
        bottomPanel.getChildren().add(volumeLabel);
        bottomPanel.getChildren().add(volumeSlider);
        root.setBottom(bottomPanel);

        // last thing to do
        stage.setScene(scene);
        stage.show();
    }

    private void handleVolume(MouseEvent e, Slider volumeSlider, Label volumeLabel) {
        this.volume = (int) volumeSlider.getValue();
        volumeLabel.setText("Volume: " + this.volume + "%");
    }

    private void playNetwork() {
        if(widgets_.size() == 0) {
            return;
        }
        // get properties from the sys about sample rates, etc.
        try {
            Clip c = AudioSystem.getClip();
            AudioListener listener = new AudioListener(c);

            this.mixer = new Mixer();
            System.out.println( widgets_.size() + " widgets connected to speaker");
            for (AudioComponentWidget w : widgets_) {
                AudioComponent ac = w.getAudioComponent();
                mixer.connectInput(ac);
            }
            mixer.setVolume(this.volume);

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

    private void createSineWave(String name) {
        System.out.println("creating sine wave widget");
        AudioComponent sw = new SineWave(440);
        // even tho acw is not used, it is added to the mainCanvas_
        AudioComponentWidget acw = new SineWaveWidget(sw, mainCanvas_, name); // inheritance
    }

    private void createSquareWave(String name) {
        System.out.println("creating square wave widget");
        AudioComponent sw = new SquareWave(440);
        AudioComponentWidget acw = new SquareWaveWidget(sw, mainCanvas_, name);
    }

    private void createLinearRamp(String name) {
        System.out.println("creating linear ramp widget");
        AudioComponent lr = new LinearRamp(50, 2000);
        AudioComponentWidget acw = new LinearRampWidget(lr, mainCanvas_, name);
    }

    private void createVFSineWave(String name) {
        System.out.println("creating linear ramp widget");
        AudioComponent ac = new VFSineWave(null);
        AudioComponentWidget acw = new VFSineWaveWidget(ac, mainCanvas_, name);
        SynthesizeApplication.widgetList_.add(acw);
    }

    private void createFilter(String name) {
        System.out.println("creating filter widget");
        AudioComponent ac = new Filter(1.0);
        AudioComponentWidget acw = new FilterWidget(ac, mainCanvas_, name);
        widgetList_.add(acw);
    }

    private void createMixer(String name) {
        System.out.println("creating filter widget");
        AudioComponent ac = new Mixer();
        AudioComponentWidget acw = new MixerWidget(ac, mainCanvas_, name);
        widgetList_.add(acw);
    }

    public static void main(String[] args) {
        launch();
    }
}