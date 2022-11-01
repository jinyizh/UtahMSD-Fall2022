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
    private AnchorPane parent_;
    protected AudioComponent audioComponent_;
    private AudioComponentWidget widgetIamSendingOutputTo_;
    private AudioComponentWidget WidgetIamReceivingInputFrom_;
    protected HBox baseLayout;
    protected VBox center;
    private Line line_; // for output
    private Line inputLine_;
    protected Label nameLabel_;
    private String name_;
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

        // right side of widget
        VBox rightSide = new VBox();
        rightSide.setAlignment(Pos.CENTER_RIGHT);
        rightSide.setPadding(new Insets(3));
        rightSide.setSpacing(5);
        // close button
        Button closeBtn = new Button("x");
        closeBtn.setOnAction(e -> closeWidget());
        rightSide.getChildren().add(closeBtn);
        // output circle
        Circle output = new Circle(10);
        output.setFill(Color.BLUE);
        output.setOnMousePressed(e -> startConnection(e, output));
        output.setOnMouseDragged(e -> moveConnection(e, output));
        output.setOnMouseReleased(e -> endConnection(e, output));
        rightSide.getChildren().add(output);

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

        // add panels (from left to right) to base layout
        baseLayout.getChildren().add(center);
        baseLayout.getChildren().add(rightSide);

        this.setLayoutX(160);
        this.setLayoutY(100);
        parent_.getChildren().add(this);
    }

    // constructor for in/out widgets (filters, VF Sine Wave)
    public AudioComponentWidget(AnchorPane parent, String name) {
        audioComponent_ = null;
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
        // input circle
        input_ = new Circle(10);
        input_.setFill(Color.GREEN);
        leftSide.getChildren().add(input_);
        baseLayout.getChildren().add(leftSide);

        // right side of widget
        VBox rightSide = new VBox();
        rightSide.setAlignment(Pos.CENTER_RIGHT);
        rightSide.setPadding(new Insets(3));
        rightSide.setSpacing(5);
        // close button
        Button closeBtn = new Button("x");
        closeBtn.setOnAction(e -> closeWidget());
        rightSide.getChildren().add(closeBtn);
        // output circle
        Circle output = new Circle(10);
        output.setFill(Color.BLUE);
        output.setOnMousePressed(e -> startConnection(e, output));
        output.setOnMouseDragged(e -> moveConnection(e, output));
        output.setOnMouseReleased(e -> endConnectionInOut(e, output));
        rightSide.getChildren().add(output);

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

        // add panels (from left to right) to base layout
        baseLayout.getChildren().add(center);
        baseLayout.getChildren().add(rightSide);

        this.setLayoutX(160);
        this.setLayoutY(100);
        parent_.getChildren().add(this);
    }

    // used for output circle connection
    protected void endConnection(MouseEvent e, Circle outputCircle) {
        Circle speaker = SynthesizeApplication.speaker_;
        Bounds speakerBounds = speaker.localToScreen(speaker.getBoundsInLocal());
        double distance = Math.sqrt(Math.pow(speakerBounds.getCenterX() - e.getScreenX(), 2.0) +
                                    Math.pow(speakerBounds.getCenterY() - e.getScreenY(), 2.0));

        // previously created an arraylist of AudioComponentWidget
        // for each comp in components
        //      comp.get circles <- returns no circles if the ac widg has no inputs
        //      do the abov distan cod on this  circle

        System.out.println("dist: " + distance);
        if (distance < 10) {
            // handle actually connecting to speaker
            SynthesizeApplication.widgets_.add(this);
        } else {
            parent_.getChildren().remove(line_);
            line_ = null;
        }

        if (SynthesizeApplication.widgetList_.size() != 0) {
            for (AudioComponentWidget acw : SynthesizeApplication.widgetList_) {
                if (acw.getInput_() != null) {
                    Bounds acwBounds = acw.getInput_().localToScreen(acw.getInput_().getBoundsInLocal());
                    double acwDistance = Math.sqrt(Math.pow(acwBounds.getCenterX() - e.getScreenX(), 2.0) +
                                                   Math.pow(acwBounds.getCenterY() - e.getScreenY(), 2.0));
                    if (acwDistance < 10) {
                        // when doing connection, start from the widget connected to speaker first
                        acw.WidgetIamReceivingInputFrom_ = this;
                        this.widgetIamSendingOutputTo_ = acw;
                        this.widgetIamSendingOutputTo_.getAudioComponent().connectInput(this.getAudioComponent());
                        SynthesizeApplication.widgets_.add(this.widgetIamSendingOutputTo_);
                    } else {
                        parent_.getChildren().remove(line_);
                        line_ = null;
                    }
                }
            }
        }
    }

    protected void endConnectionInOut(MouseEvent e, Circle outputCircle) { // for in/out widgets
        Circle speaker = SynthesizeApplication.speaker_;
        Bounds speakerBounds = speaker.localToScreen(speaker.getBoundsInLocal());
        double distance = Math.sqrt(Math.pow(speakerBounds.getCenterX() - e.getScreenX(), 2.0) +
                Math.pow(speakerBounds.getCenterY() - e.getScreenY(), 2.0));

        // previously created an arraylist of AudioComponentWidget
        // for each comp in components
        //      comp.get circles <- returns no circles if the ac widg has no inputs
        //      do the abov distan cod on this  circle

        System.out.println("dist: " + distance);
        if (distance < 10) {
            // handle actually connecting to speaker
            SynthesizeApplication.widgets_.add(this);
        } else {
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
