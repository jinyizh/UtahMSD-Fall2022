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

public class SineWaveWidget extends AudioComponentWidget {

    public SineWaveWidget(AudioComponent ac, AnchorPane parent, String name) {
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
        output.setOnMouseReleased(e -> endConnectionInOut(e, output));
        rightSide.getChildren().add(output);
        baseLayout.getChildren().add(rightSide);
    }

    @Override
    protected void handleSlider(MouseEvent e, Slider slider) {
        super.handleSlider(e, slider);
        int freq = (int) slider.getValue();
        nameLabel_.setText("Sine Wave (" + freq + " Hz)");
        ((SineWave) audioComponent_).setFrequency(freq);
    }

    @Override
    protected void endConnection(MouseEvent e, Circle outputCircle) {
        boolean connectedToWidget = false;
        // previously created an arraylist of AudioComponentWidget
        // for each comp in components
        //      comp.get circles <- returns no circles if the ac widg has no inputs
        //      do the abov distan cod on this  circle

        if (SynthesizeApplication.widgetList_.size() != 0) {
            for (AudioComponentWidget acw : SynthesizeApplication.widgetList_) {
                Circle inputCircle = acw.input_;
                Bounds acwBounds = inputCircle.localToScreen(inputCircle.getBoundsInLocal());
                double acwDistance = Math.sqrt(Math.pow(acwBounds.getCenterX() - e.getScreenX(), 2.0) +
                        Math.pow(acwBounds.getCenterY() - e.getScreenY(), 2.0));
                if (acwDistance < 10) {
                    // when doing connection, start from the widget connected to speaker first
                    acw.WidgetIamReceivingInputFrom_ = this;
                    this.widgetIamSendingOutputTo_ = acw;
                    acw.getAudioComponent().connectInput(this.getAudioComponent());
                    SynthesizeApplication.widgets_.add(acw);
                    connectedToWidget = true;
                } else {
                    parent_.getChildren().remove(line_);
                    line_ = null;
                }
            }
        }
        Circle speaker = SynthesizeApplication.speaker_;
        Bounds speakerBounds = speaker.localToScreen(speaker.getBoundsInLocal());
        double distance = Math.sqrt(Math.pow(speakerBounds.getCenterX() - e.getScreenX(), 2.0) +
                Math.pow(speakerBounds.getCenterY() - e.getScreenY(), 2.0));
        System.out.println("dist: " + distance);
        if (distance < 10) {
            // handle actually connecting to speaker
            SynthesizeApplication.widgets_.add(this);
        } else if (!connectedToWidget) {
            parent_.getChildren().remove(line_);
            line_ = null;
        }
    }
}
