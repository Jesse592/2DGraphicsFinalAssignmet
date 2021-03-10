package GUI;

import Logic.FieldGrid;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class CanvasGUI {

    private ResizableCanvas canvas;
    private BorderPane mainPane;

    private FieldGrid fieldGrid;

    public CanvasGUI() {
        this(new FieldGrid(0,0), 1920/4, 1080/2);
    }

    public CanvasGUI(int width, int height) {
        this(new FieldGrid(0,0), width, height);
    }

    public CanvasGUI(FieldGrid fieldGrid, int width, int height) {
        this.fieldGrid = fieldGrid;
        this.mainPane = new BorderPane();

        init();

        this.canvas.setHeight(height);
        this.canvas.setWidth(width);

    }

    private void init(){

        this.canvas = new ResizableCanvas(this::draw, this.mainPane);
        this.mainPane.setCenter(this.canvas);

    }


    private void draw(FXGraphics2D g2d) {
        this.fieldGrid.draw(g2d);
    }

    public BorderPane getMainPane() {
        return mainPane;
    }
}
 