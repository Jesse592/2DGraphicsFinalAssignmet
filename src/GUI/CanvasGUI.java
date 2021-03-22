package GUI;

import Logic.FieldGrid;
import Logic.FieldTile;
import Logic.Particle;
import javafx.animation.AnimationTimer;
import javafx.scene.Camera;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CanvasGUI {

    private ResizableCanvas canvas;
    private BorderPane mainPane;

    private ParticleManager particleManager;

    private FieldGrid fieldGrid;

    public CanvasGUI() {
        this(new FieldGrid(0, 0), 1920 / 4, 1080 / 2);
    }

    public CanvasGUI(FieldGrid fieldGrid) {
        this(fieldGrid, fieldGrid.getWidth() * fieldGrid.getTileWidth(), fieldGrid.getHeight() * fieldGrid.getTileHeight());
    }

    public CanvasGUI(FieldGrid fieldGrid, int width, int height) {
        this.fieldGrid = fieldGrid;
        this.mainPane = new BorderPane();

        init();

        this.canvas.setHeight(height);
        this.canvas.setWidth(width);

        FXGraphics2D g2d = new FXGraphics2D(this.canvas.getGraphicsContext2D());
        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();
    }

    private void init() {

        this.canvas = new ResizableCanvas(this::draw, this.mainPane);
        this.mainPane.setCenter(this.canvas);
        this.particleManager = new ParticleManager(this.fieldGrid, 100);

        this.canvas.setOnMouseClicked(this::onMouseClicked);
        this.canvas.setOnMouseDragged(this::onMouseClicked);
    }

    private void onMouseClicked(MouseEvent e) {
        FieldTile selectedTile = this.fieldGrid.getAtPoint(new Point2D.Double(e.getX(), e.getY()));

        if (selectedTile != null) {
            if (e.getButton() == MouseButton.PRIMARY) {
                this.fieldGrid.generateHeatMap(selectedTile);
                this.fieldGrid.generateVectorField();
            } else if (e.getButton() == MouseButton.SECONDARY) {
                selectedTile.setTransversable(false);
                this.fieldGrid.generateHeatMap();
                this.fieldGrid.generateVectorField();
            }
        }


        draw(new FXGraphics2D(this.canvas.getGraphicsContext2D()));
    }

    public void draw(){
        draw(new FXGraphics2D(this.canvas.getGraphicsContext2D()));
    }

    private void draw(FXGraphics2D g2d) {
        g2d.setTransform(new AffineTransform());
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        this.fieldGrid.draw(g2d);

        this.particleManager.draw(g2d);
    }

    private void update(double deltaTime) {
        this.particleManager.update(deltaTime);
    }

    public BorderPane getMainPane() {
        return mainPane;
    }

    public ParticleManager getParticleManager(){
        return this.particleManager;
    }
}
 