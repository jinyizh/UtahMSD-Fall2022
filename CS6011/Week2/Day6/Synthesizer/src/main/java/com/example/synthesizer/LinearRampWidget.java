package com.example.synthesizer;

import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LinearRampWidget extends AudioComponentWidget{
    int startFreq = 50;
    int stopFreq = 2000;

    public LinearRampWidget(AudioComponent ac, AnchorPane parent, String name) {
        super(ac, parent, name);
        Slider startSlider = new Slider(0, 3000, 50);
        Slider stopSlider = new Slider(0, 3000, 2000);
        startSlider.setOnMouseDragged(e -> handleSlider(e, startSlider));
        stopSlider.setOnMouseDragged(e -> handleSliderStop(e, stopSlider));
        center.getChildren().add(startSlider);
        center.getChildren().add(stopSlider);
    }

    @Override
    protected void handleSlider(MouseEvent e, Slider slider) {
        super.handleSlider(e, slider);
        startFreq = (int) slider.getValue();
        nameLabel_.setText("Linear Ramp (start: " + startFreq + " Hz, stop: " + stopFreq + " Hz)");
        ((LinearRamp) audioComponent_).setStart(startFreq);
    }

    private void handleSliderStop(MouseEvent e, Slider slider) {
        stopFreq = (int) slider.getValue();
        nameLabel_.setText("Linear Ramp (start: " + startFreq + " Hz, stop: " + stopFreq + " Hz)");
        ((LinearRamp) audioComponent_).setStop(stopFreq);
    }
}
