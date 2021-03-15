package GUI;

import Logic.FieldGrid;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainGUI extends Application {

    private BorderPane mainPane;
    private CanvasGUI canvasGUI;
    private BuildPanel buildPanel;
    private VBox leftPanel;
    private DebugPanel debugPanel;

    private FieldGrid fieldGrid;

    @Override
    public void start(Stage stage) throws Exception {


        this.mainPane.setCenter(this.canvasGUI.getMainPane());
        this.mainPane.setRight(this.buildPanel.getMainPane());
        this.leftPanel.getChildren().add(this.debugPanel.getMainPane());
        this.mainPane.setLeft(this.leftPanel);

        stage.setScene(new Scene(mainPane));
        stage.show();
    }

    public void init() {
        this.mainPane = new BorderPane();
        this.fieldGrid = new FieldGrid(50,30, 40, 40);

        this.canvasGUI = new CanvasGUI(this.fieldGrid);
        this.buildPanel = new BuildPanel(this.fieldGrid, this.canvasGUI);
        this.leftPanel = new VBox();
        this.debugPanel = new DebugPanel(this.fieldGrid, this.canvasGUI);

        this.mainPane.setPadding(new Insets(5,5,5,5));

    }



}
