package GUI;

import Logic.FieldGrid;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainGUI extends Application {

    private BorderPane mainPane;
    private CanvasGUI canvasGUI;

    private FieldGrid fieldGrid;

    @Override
    public void start(Stage stage) throws Exception {
        this.mainPane = new BorderPane();
        this.fieldGrid = new FieldGrid(100,30, 40, 40);

        this.canvasGUI = new CanvasGUI(this.fieldGrid, 20*50+10, 20*50);

        this.mainPane.setCenter(this.canvasGUI.getMainPane());

        stage.setScene(new Scene(mainPane));
        stage.show();
    }



}
