package GUI;

import Logic.FieldGrid;
import Logic.Particle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javax.swing.colorchooser.ColorSelectionModel;
import java.util.ArrayList;

public class ParticlePanel {

    private ParticleManager particleManager;

    private CanvasGUI canvasGUI;
    private VBox mainPane;

    public ParticlePanel(ParticleManager particleManager, CanvasGUI canvasGUI) {
        this.canvasGUI = canvasGUI;
        this.particleManager = particleManager;
        this.mainPane = new VBox();

        this.mainPane.setAlignment(Pos.TOP_CENTER);
        this.mainPane.setSpacing(5);
        this.mainPane.setPadding(new Insets(10,10,10,10));
        this.mainPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5), Insets.EMPTY)));
        this.mainPane.setMaxWidth(350);

        buildMainPane();
    }

    private void buildMainPane() {

        Label label = new Label("Particle editor");

        Label sizeLabel = new Label("Amount of particles:");
        TextField sizeField = new TextField("100");

        Label colorLabel = new Label("Particle Color:");
        ColorPicker particleColorSelect = new ColorPicker(Color.BLACK);

        Label radiusLabel = new Label("Particle Radius:");
        TextField radiusField = new TextField(this.particleManager.getRadius() + "");

        HBox speedHBox = new HBox();

        VBox minSpeed = new VBox();
        Label minSpeedLabel = new Label("Min Speed:");
        TextField minSpeedField = new TextField(this.particleManager.getMinSpeed() + "");
        minSpeed.getChildren().addAll(minSpeedLabel, minSpeedField);

        VBox maxSpeed = new VBox();
        Label maxSpeedLabel = new Label("Max Speed:");
        TextField maxSpeedField = new TextField(this.particleManager.getMaxSpeed() + "");
        maxSpeed.getChildren().addAll(maxSpeedLabel, maxSpeedField);
        speedHBox.getChildren().addAll(minSpeed, maxSpeed);

        Label opacityLabel = new Label("Particle Opacity(%):");
        Slider opacitySlider = new Slider(0, 100, 100);

        HBox accelHBox = new HBox();

        VBox minAccel = new VBox();
        Label minAccelLabel = new Label("Min Acceleration:");
        TextField minAccelField = new TextField(this.particleManager.getMinAcceleration() + "");
        minAccel.getChildren().addAll(minAccelLabel, minAccelField);

        VBox maxAccel = new VBox();
        Label maxAccelLabel = new Label("Max Acceleration:");
        TextField maxAccelField = new TextField(this.particleManager.getMaxAcceleration() + "");
        maxAccel.getChildren().addAll(maxAccelLabel, maxAccelField);
        accelHBox.getChildren().addAll(maxAccel, minAccel);

        Button applyButton = new Button("Apply");
        applyButton.setOnMouseClicked(e ->{
            try {
                this.particleManager.setsize(Integer.parseInt(sizeField.getText()));

                Color color = particleColorSelect.getValue();
                this.particleManager.setColor(new java.awt.Color((int)(color.getRed() * 255), (int)(color.getGreen() * 255), (int)(color.getBlue() * 255)));

                this.particleManager.setRadius(Integer.parseInt(radiusField.getText()));

                this.particleManager.setMinSpeed(Integer.parseInt(minSpeedField.getText()));
                this.particleManager.setMaxSpeed(Integer.parseInt(maxSpeedField.getText()));

                this.particleManager.setMinAcceleration(Integer.parseInt(minAccelField.getText()));
                this.particleManager.setMaxAcceleration(Integer.parseInt(maxAccelField.getText()));

                this.particleManager.setOpacity(opacitySlider.getValue() / 100);

                this.particleManager.applyParticles();
            } catch (Exception q) {
                q.printStackTrace();
            }
        });

        this.mainPane.getChildren().addAll(
                label, new Label(),
                sizeLabel, sizeField, new Label(),
                colorLabel, particleColorSelect, new Label(),
                radiusLabel, radiusField, new Label(),
                speedHBox, new Label(),
                accelHBox, new Label(),
                opacityLabel, opacitySlider, new Label(),
                applyButton
        );

    }

    public VBox getMainPane() {
        return mainPane;
    }
}
