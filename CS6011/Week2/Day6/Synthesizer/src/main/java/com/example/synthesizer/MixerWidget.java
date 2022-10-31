package com.example.synthesizer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MixerWidget extends AudioComponentWidget {
    public MixerWidget(AudioComponent ac, AnchorPane parent, String name) {
        super(ac, parent, name);
        // left side of widget
        VBox leftSide = new VBox();
        leftSide.setAlignment(Pos.CENTER_LEFT);
        leftSide.setPadding(new Insets(3));
        leftSide.setSpacing(5);
        // input circle
        Circle input = new Circle(10);
        input.setFill(Color.GREEN);
        leftSide.getChildren().add(input);
        baseLayout.getChildren().add(leftSide);
    }
}
