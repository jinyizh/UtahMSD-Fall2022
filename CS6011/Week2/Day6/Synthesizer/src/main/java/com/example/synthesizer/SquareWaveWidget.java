package com.example.synthesizer;

import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SquareWaveWidget extends AudioComponentWidget {
    public SquareWaveWidget(AudioComponent ac, AnchorPane parent, String name) {
        super(ac, parent, name);
    }

    @Override
    protected void handleSlider(MouseEvent e, Slider slider) {
        super.handleSlider(e, slider);
        int freq = (int) slider.getValue();
        nameLabel_.setText("Square Wave (" + freq + " Hz)");
        ((SquareWave) audioComponent_).setFrequency(freq);
    }


}
