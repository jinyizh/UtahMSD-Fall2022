package com.example.synthesizer;

import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SineWaveWidget extends AudioComponentWidget {

    public SineWaveWidget(AudioComponent ac, AnchorPane parent, String name) {
        super(ac, parent, name);
        Slider slider = new Slider(220, 880, 440);
        slider.setOnMouseDragged(e -> handleSlider(e, slider));
        center.getChildren().add(slider);
    }

    @Override
    protected void handleSlider(MouseEvent e, Slider slider) {
        super.handleSlider(e, slider);
        int freq = (int) slider.getValue();
        nameLabel_.setText("Sine Wave (" + freq + " Hz)");
        ((SineWave) audioComponent_).setFrequency(freq);
    }
}
