package GUI;

import Logic.DebugState;
import Logic.FieldGrid;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class DebugPanel {

    private FieldGrid fieldGrid;
    private CanvasGUI canvasGUI;
    private VBox mainPane;

    public DebugPanel(FieldGrid fieldGrid, CanvasGUI canvasGUI) {
        this.canvasGUI = canvasGUI;
        this.fieldGrid = fieldGrid;
        this.mainPane = new VBox();

        this.mainPane.setAlignment(Pos.TOP_CENTER);
        this.mainPane.setSpacing(5);
        this.mainPane.setPadding(new Insets(10,10,10,10));
        this.mainPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5), Insets.EMPTY)));
        this.mainPane.setMinWidth(250);

        buildMainPane();
    }

    private void buildMainPane() {

        Label label = new Label("Debug Settings");

        Label debugLabel = new Label("Canvas Debug types:");
        ComboBox<DebugState> debugStateComboBox = new ComboBox<>();
        debugStateComboBox.setItems(FXCollections.observableArrayList(DebugState.values()));
        debugStateComboBox.getSelectionModel().select(0);

        debugStateComboBox.valueProperty().addListener((o, b, v) -> {
            this.fieldGrid.setDebugState(v);
            this.canvasGUI.draw();
        });

        this.mainPane.getChildren().addAll(
                label, new Label(),
                debugLabel, debugStateComboBox
        );

    }

    public VBox getMainPane() {
        return mainPane;
    }

}
