package com.example.synthesizer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FilterWidget extends AudioComponentWidget {
    AudioComponent filter;
    public FilterWidget(AnchorPane parent, String name) {
        super(parent, name);
        this.filter = new Filter(1.0);
        Slider slider = new Slider(0, 10, 1);
        slider.setOnMouseDragged(e -> handleSlider(e, slider));
        center.getChildren().add(slider);
    }

    protected void handleSlider(MouseEvent e, Slider slider) {
        super.handleSlider(e, slider);
        double scale = slider.getValue();
        nameLabel_.setText("Filter (scale: " + scale + ")");
        this.filter = new Filter(scale);
    }
}
