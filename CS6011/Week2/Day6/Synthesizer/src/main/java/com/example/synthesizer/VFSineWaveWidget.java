package com.example.synthesizer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VFSineWaveWidget extends AudioComponentWidget {
    private VFSineWave vfSineWave;
    public VFSineWaveWidget(AnchorPane parent, String name) {
        super(parent, name);
        this.vfSineWave = new VFSineWave(null);
    }
}
