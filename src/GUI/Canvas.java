package GUI;

import Logic.FieldGrid;
import javafx.scene.layout.Pane;
import org.jfree.fx.ResizableCanvas;

public class Canvas {

    private ResizableCanvas canvas;
    private Pane mainPane;

    private FieldGrid fieldGrid;

    public Canvas() {
        this(new FieldGrid(0,0));
    }

    public Canvas(FieldGrid fieldGrid) {
        this.fieldGrid = fieldGrid;
    }


}
 