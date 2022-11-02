package com.example.synthesizer;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FilterWidget extends AudioComponentWidget {
    Filter filter_;
    public FilterWidget(AudioComponent ac, AnchorPane parent, String name) {
        super(ac, parent, name);
        // center portion of widget
        filter_ = (Filter) ac;
        Slider slider = new Slider(0, 15, 1);
        slider.setOnMouseDragged(e -> handleSlider(e, slider));
        center.getChildren().add(slider);
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
        output.setOnMouseReleased(e -> endConnection(e, output));
        rightSide.getChildren().add(output);
        baseLayout.getChildren().add(rightSide);
    }

    @Override
    protected void handleSlider(MouseEvent e, Slider slider) {
        super.handleSlider(e, slider);
        double scale = slider.getValue();
        nameLabel_.setText("Filter (scale: " + String.format("%.2f", scale) + ")");
        filter_.setScale(scale);
    }

    @Override
    protected void endConnection(MouseEvent e, Circle outputCircle) {
        Circle speaker = SynthesizeApplication.speaker_;
        Bounds speakerBounds = speaker.localToScreen(speaker.getBoundsInLocal());
        double distance = Math.sqrt(Math.pow(speakerBounds.getCenterX() - e.getScreenX(), 2.0) +
                                    Math.pow(speakerBounds.getCenterY() - e.getScreenY(), 2.0));
        System.out.println("dist: " + distance);
        if (distance < 10) {
            // handle actually connecting to speaker
            SynthesizeApplication.widgets_.add(this);
        } else {
            parent_.getChildren().remove(line_);
            line_ = null;
        }

        // previously created an arraylist of AudioComponentWidget
        // for each comp in components
        //      comp.get circles <- returns no circles if the ac widg has no inputs
        //      do the abov distan cod on this  circle
    }
}
