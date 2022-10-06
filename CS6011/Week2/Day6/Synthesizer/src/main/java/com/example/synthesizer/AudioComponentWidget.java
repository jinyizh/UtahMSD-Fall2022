//package com.example.synthesizer;
//
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.control.Button;
//import javafx.scene.control.IndexedCell;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//
//public class AudioComponentWidget extends Pane {
//    AudioComponentWidget(AudioComponent ac, AnchorPane parent, String name) {
//        audioComponent_ = ac;
//        parent_ = parent;
//        name_ = name;
//
//        baseLayout = new HBox();
//        baseLayout.setStyle("-fx-border-color: black; -fx-border-image-width: 8; -fx-background-color: white");
//
//        // Right side
//        VBox rightSide = new VBox();
//        Button closeBtn = new Button("x");
//        closeBtn.setOnAction(e -> destroyWidget());
//        Circle output = new Circle(10);
//        output.setFill(Color.BLUE);
//        rightSide.getChildren().add(output);
//        rightSide.setAlignment(Pos.CENTER);
//        rightSide.setPadding(new Insets(5));
//        rightSide.setSpacing(5);
//
//
//
//        baseLayout.getChildren().add(rightSide);
//        this.getChildren().add(baseLayout);
//
//        this.setLayoutX(50);
//        this.setLayoutY(100);
//
//        parent_.getChildren().add(this);
//    }
//
//    private void destroyWidget() {
//    }
//
//    private void closeWidget() {
//        parent_.getChildren().remove(this);
//        HelloApplication.widgets_.remove(this);
//    }
//
//    public AudioComponent getAudioComponent() {
//        return audioComponent_;
//    }
//
//    private AnchorPane parent_;
//    private HBox baseLayout;
//    private AudioComponent audioComponent_;
//    private String name_;
//
//}
