package com.example.synthesizer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VFSineWaveWidget extends AudioComponentWidget {
    private VFSineWave vfSineWave;
    public VFSineWaveWidget(AudioComponent ac, AnchorPane parent, String name) {
        super(ac, parent, name);
        this.vfSineWave = new VFSineWave(null);
        // right side of widget
        VBox rightSide = new VBox();
        rightSide.setAlignment(Pos.CENTER_LEFT);
        rightSide.setPadding(new Insets(3));
        rightSide.setSpacing(5);
        // input circle
        input_ = new Circle(10);
        input_.setFill(Color.BLUE);
        rightSide.getChildren().add(input_);
        // output circle
        Circle output = new Circle(10);
        output.setFill(Color.ORANGE);
        output.setOnMousePressed(e -> startConnection(e, output));
        output.setOnMouseDragged(e -> moveConnection(e, output));
        output.setOnMouseReleased(e -> endConnectionInOut(e, output));
        rightSide.getChildren().add(output);
        baseLayout.getChildren().add(rightSide);
    }
}
