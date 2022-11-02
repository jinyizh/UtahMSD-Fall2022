package com.example.synthesizer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SquareWaveWidget extends AudioComponentWidget {
    public SquareWaveWidget(AudioComponent ac, AnchorPane parent, String name) {
        super(ac, parent, name);
        // add slider
        Slider slider = new Slider(220, 880, 440);
        slider.setOnMouseDragged(e -> handleSlider(e, slider));
        center.getChildren().add(slider);
        // right side of widget
        VBox rightSide = new VBox();
        rightSide.setAlignment(Pos.CENTER_LEFT);
        rightSide.setPadding(new Insets(3));
        rightSide.setSpacing(5);
        // output circle
        Circle output = new Circle(10);
        output.setFill(Color.ORANGE);
        output.setOnMousePressed(e -> startConnection(e, output));
        output.setOnMouseDragged(e -> moveConnection(e, output));
        output.setOnMouseReleased(e -> endConnection(e, output));
        rightSide.getChildren().add(output);
        baseLayout.getChildren().add(rightSide);
    }

    @Override
    protected void handleSlider(MouseEvent e, Slider slider) {
        super.handleSlider(e, slider);
        int freq = (int) slider.getValue();
        nameLabel_.setText("Square Wave (" + freq + " Hz)");
        ((SquareWave) audioComponent_).setFrequency(freq);
    }


}
