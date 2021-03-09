package GUI;

import Logic.FieldGrid;
import javafx.scene.layout.Pane;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Canvas {

    private ResizableCanvas canvas;
    private Pane mainPane;

    private FieldGrid fieldGrid;

    public Canvas() {
        this(new FieldGrid(0,0), 1920/4, 1080/2);
    }

    public Canvas(int width, int height) {
        this(new FieldGrid(0,0), width, height);
    }

    public Canvas(FieldGrid fieldGrid, int width, int height) {
        this.fieldGrid = fieldGrid;
        this.mainPane = new Pane();

        init();

        this.canvas.setHeight(height);
        this.canvas.setWidth(width);

    }

    public void init(){
        this.canvas = new ResizableCanvas(this::draw, this.mainPane);
        this.mainPane.getChildren().add(this.canvas);
    }

    private void draw(FXGraphics2D fxGraphics2D) {
    }


}
 