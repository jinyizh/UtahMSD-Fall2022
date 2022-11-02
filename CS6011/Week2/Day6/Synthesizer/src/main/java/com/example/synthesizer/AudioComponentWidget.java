package com.example.synthesizer;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class AudioComponentWidget extends Pane {
    protected AnchorPane parent_;
    protected AudioComponent audioComponent_;
    protected AudioComponentWidget widgetIamSendingOutputTo_;
    protected AudioComponentWidget WidgetIamReceivingInputFrom_;
    protected HBox baseLayout;
    protected VBox center;
    protected Line line_; // for output
    protected Label nameLabel_;
    public String name_;
    protected Circle input_ = null; // for widgets with input circle (2nd constructor)
    double mouseStartDragX_, mouseStartDragY_, widgetStartDragX_, widgetStartDragY_;

    public AudioComponentWidget(AudioComponent ac, AnchorPane parent, String name) {
        audioComponent_ = ac;
        parent_ = parent;
        name_ = name;
        widgetIamSendingOutputTo_ = null;
        WidgetIamReceivingInputFrom_ = null;

        // base layout of widget
        baseLayout = new HBox();
        baseLayout.setStyle("-fx-border-color: black; -fx-border-image-width: 8; -fx-background-color: white");
        this.getChildren().add(baseLayout);

        // left side of widget
        VBox leftSide = new VBox();
        leftSide.setAlignment(Pos.CENTER_LEFT);
        leftSide.setPadding(new Insets(3));
        leftSide.setSpacing(5);
        // close button
        Button closeBtn = new Button("x");
        closeBtn.setOnAction(e -> closeWidget());
        leftSide.getChildren().add(closeBtn);

        // center portion of widget
        center = new VBox();
        center.setAlignment(Pos.CENTER);
        // name label
        nameLabel_ = new Label();
        nameLabel_.setMouseTransparent(true);
        nameLabel_.setText(name_);
        center.getChildren().add(nameLabel_);
        // dragging
        center.setOnMousePressed(e -> startDrag(e));
        center.setOnMouseDragged(e -> handleDrag(e));

        // add panels (from left to right) to base layout and add widget to canvas
        baseLayout.getChildren().add(leftSide);
        baseLayout.getChildren().add(center);
        this.setLayoutX(160);
        this.setLayoutY(100);
        parent_.getChildren().add(this);
    }

    // used for output circle connection
    protected void endConnection(MouseEvent e, Circle outputCircle) {
        boolean connectedToWidget = false;
        System.out.println("here");
        // FIXME: DELETE LINE #78
        SynthesizeApplication.widgetList_.remove(this);
        if (SynthesizeApplication.widgetList_.size() != 0) {
            System.out.println("looking at # of wid: " + SynthesizeApplication.widgetList_);
            for (AudioComponentWidget acw : SynthesizeApplication.widgetList_) {
                Circle inputCircle = acw.input_;
                Bounds acwBounds = inputCircle.localToScreen(inputCircle.getBoundsInLocal());
                double acwDistance = Math.sqrt(Math.pow(acwBounds.getCenterX() - e.getScreenX(), 2.0) +
                        Math.pow(acwBounds.getCenterY() - e.getScreenY(), 2.0));

                System.out.println("distance is " + acwDistance + " to " + acw.name_);
                if (acwDistance < 10) {
                    // when doing connection, start from the widget connected to speaker first
                    acw.WidgetIamReceivingInputFrom_ = this;
                    this.widgetIamSendingOutputTo_ = acw;
                    acw.getAudioComponent().connectInput(this.getAudioComponent());
                    connectedToWidget = true;
                } else if (!connectedToWidget){
                    parent_.getChildren().remove(line_);
                    line_ = null;
                }
            }
        }
        // handle connection to speaker
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

    protected void moveConnection(MouseEvent e, Circle outputCircle) {
        Bounds parentBounds = parent_.getBoundsInParent();
        line_.setEndX(e.getSceneX() - parentBounds.getMinX());
        line_.setEndY(e.getSceneY() - parentBounds.getMinY());
    }

    protected void handleDrag(MouseEvent e) {
        double mouseDelX = e.getSceneX() - mouseStartDragX_;
        double mouseDelY = e.getSceneY() - mouseStartDragY_;
        this.relocate(widgetStartDragX_ + mouseDelX, widgetStartDragY_ + mouseDelY);
    }

    protected void startDrag(MouseEvent e) {
        mouseStartDragX_ = e.getSceneX();
        mouseStartDragY_ = e.getSceneY();

        widgetStartDragX_ = this.getLayoutX();
        widgetStartDragY_ = this.getLayoutY();
    }

    protected void handleSlider(MouseEvent e, Slider slider) {}

    protected void startConnection(MouseEvent e, Circle outputCircle) {
        // if line exists (connected to others), remove that line
        if (line_ != null) {
            parent_.getChildren().remove(line_);
        }

        Bounds parentBounds = parent_.getBoundsInParent();
        Bounds bounds = outputCircle.localToScene(outputCircle.getBoundsInLocal());

        line_ = new Line();
        line_.setStrokeWidth(4);
        line_.setStartX(bounds.getCenterX() - parentBounds.getMinX());
        line_.setStartY(bounds.getCenterY() - parentBounds.getMinY());
        line_.setEndX(e.getSceneX());
        line_.setEndY(e.getSceneY());
        // for any widget we have to add it to the parent
        parent_.getChildren().add(line_);
    }

    protected void closeWidget() {
        parent_.getChildren().remove(this);
        SynthesizeApplication.widgets_.remove(this);
        SynthesizeApplication.widgetList_.remove(this);
        if (widgetIamSendingOutputTo_ != null) {
            widgetIamSendingOutputTo_.getAudioComponent().removeInput(this.getAudioComponent());
        }
        if (line_ != null) {
            parent_.getChildren().remove(line_);
        }
    }

    public AudioComponent getAudioComponent() {
        return audioComponent_;
    }

    // used to get the input circle for filters, VF Sine waves, etc.
    public Circle getInput_() {
        return input_;
    }
}
