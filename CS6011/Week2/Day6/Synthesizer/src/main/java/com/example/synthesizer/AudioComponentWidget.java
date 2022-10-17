package com.example.synthesizer;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.IndexedCell;
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
    private AnchorPane parent_;
    private HBox baseLayout;
    private AudioComponent audioComponent_;
    private  AudioComponentWidget widgetIamSendingOutputTo_;
    private String name_;
    private Line line_;
    private Label nameLable_;
    double mouseStartDragX_, mouseStartDragY_, widgetStartDragX_, widgetStartDragY_;

    AudioComponentWidget(AudioComponent ac, AnchorPane parent, String name) {
        audioComponent_ = ac;
        parent_ = parent;
        name_ = name;
        widgetIamSendingOutputTo_ = null;
        baseLayout = new HBox();
        baseLayout.setStyle("-fx-border-color: black; -fx-border-image-width: 8; -fx-background-color: white");

        this.getChildren().add(baseLayout);

        // Right side
        VBox rightSide = new VBox();
        rightSide.setAlignment(Pos.CENTER);
        rightSide.setPadding(new Insets(5));
        rightSide.setSpacing(5);
        
        Button closeBtn = new Button("x");
        closeBtn.setOnAction(e -> closeWidget());
        Circle outputCircle = new Circle(10);
        outputCircle.setFill(Color.BLUE);
        outputCircle.setOnMousePressed(e -> startConnection(e, outputCircle));
        outputCircle.setOnMouseDragged(e -> moveConnection(e, outputCircle));
        outputCircle.setOnMouseReleased(e -> endConnection(e));
        
        rightSide.getChildren().add(outputCircle);
        rightSide.getChildren().add(closeBtn);

        // Center portion of widget
        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);

        nameLable_ = new Label();
        nameLable_.setMouseTransparent(true);
        nameLable_.setText(name_);

        Slider slider = new Slider(220, 880, 440);
        slider.setOnMouseDragged(e -> handleSlider(e, slider));

        center.getChildren().add(nameLable_);
        center.getChildren().add(slider);
        
        center.setOnMousePressed(e -> startDrag(e));
        center.setOnMouseDragged(e -> handleDrag(e));

        baseLayout.getChildren().add(rightSide);
        baseLayout.getChildren().add(center);

        this.setLayoutX(300);
        this.setLayoutY(100);

        parent_.getChildren().add(this);
    }

    private void endConnection(MouseEvent e) {
        Circle speaker = SynthesizeApplication.speaker_;
        Bounds speakerBounds = speaker.localToScreen(speaker.getBoundsInLocal());
        double distance = Math.sqrt(Math.pow(speakerBounds.getCenterX() - e.getScreenX(), 2.0) +
                Math.pow(speakerBounds.getCenterY() - e.getSceneY(), 2.0));
        if (distance < 10) {
            // handle actually connecting to speaker
            SynthesizeApplication.widgets_.add(this);
        } else {
            parent_.getChildren().remove(line_);
            line_ = null;
        }
    }

    private void moveConnection(MouseEvent e, Circle outputCircle) {
        Bounds parentBounds = parent_.getBoundsInParent();
        line_.setEndX(e.getSceneX() - parentBounds.getMinX());
        line_.setEndY(e.getSceneY() - parentBounds.getMinY());
    }

    private void handleDrag(MouseEvent e) {
        double mouseDelX = e.getSceneX() - mouseStartDragX_;
        double mouseDelY = e.getSceneY() - mouseStartDragY_;
        this.relocate(widgetStartDragX_ + mouseDelX, widgetStartDragY_ + mouseDelY);
    }

    private void startDrag(MouseEvent e) {
        mouseStartDragX_ = e.getSceneX();
        mouseStartDragY_ = e.getSceneY();

        widgetStartDragX_ = this.getLayoutX();
        widgetStartDragY_ = this.getLayoutY();
    }

    private void handleSlider(MouseEvent e, Slider slider) {
    }

    private void startConnection(MouseEvent e, Circle outputCircle) {
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

    private void closeWidget() {
        parent_.getChildren().remove(this);
        SynthesizeApplication.widgets_.remove(this);
    }

    public AudioComponent getAudioComponent() {
        return audioComponent_;
    }
}
