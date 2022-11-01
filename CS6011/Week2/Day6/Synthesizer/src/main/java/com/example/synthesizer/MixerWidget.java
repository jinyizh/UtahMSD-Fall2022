package com.example.synthesizer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MixerWidget extends AudioComponentWidget {
    private Mixer mixer;
    public MixerWidget(AudioComponent ac, AnchorPane parent, String name) {
        super(ac, parent, name);
        this.mixer = new Mixer();
    }
}
