package GUI;

import Logic.FieldGrid;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javax.xml.soap.Text;


public class BuildPanel {

    private FieldGrid fieldGrid;
    private VBox mainPane;

    public BuildPanel(FieldGrid fieldGrid) {
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

        Label label = new Label("Grid Design");

        Label widthLabel = new Label("Grid Width:");
        TextField widthField = new TextField(Integer.toString(this.fieldGrid.getWidth()));

        Label heightLabel = new Label("Grid Height:");
        TextField heightField = new TextField(Integer.toString(this.fieldGrid.getHeight()));

        Label tileWidthLabel = new Label("GridTile width:");
        TextField tileWidthField = new TextField(Integer.toString(this.fieldGrid.getTileWidth()));

        Label tileHeightLabel = new Label("GridTile height:");
        TextField tileHeightField = new TextField(Integer.toString(this.fieldGrid.getTileHeight()));


        this.mainPane.getChildren().addAll(
                label, new Label(),
                widthLabel, widthField, new Label(),
                heightLabel, heightField, new Label(),
                tileWidthLabel, tileWidthField, new Label(),
                tileHeightLabel, tileHeightField
        );

    }

    public VBox getMainPane() {
        return mainPane;
    }
}
