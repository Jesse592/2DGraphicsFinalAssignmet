package Logic;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.color.*;
import java.awt.geom.Rectangle2D;

enum DebugState {NONE, HEAT, VECTOR};

public class FieldTile {

    private int heat;
    private Point2D vector;
    private Point2D centre;
    private Point2D index;
    private DebugState debug;
    private boolean transversable;

    private int width;
    private int height;

    public FieldTile(Point2D centre, int width, int height, int indexX, int indexY) {
        this.centre = centre;
        this.index = new Point2D.Double(indexX, indexY);

        this.debug = DebugState.VECTOR;

        this.heat = -1;

        this.width = width;
        this.height = height;

        this.vector = new Point2D.Double(0, 0);
        this.transversable = true;
    }

    public boolean isTransversable() {
        return transversable;
    }

    public void setTransversable(boolean transversable) {
        this.transversable = transversable;
    }

    public int getHeat() {
        return heat;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

    public Point2D getVector() {
        return vector;
    }

    public void setVector(Point2D vector) {
        this.vector = vector;
    }

    public Point2D getCentre() {
        return centre;
    }

    public Point2D getIndex() {
        return index;
    }

    public void setDebug(DebugState debug) {
        this.debug = debug;
    }


    public void draw(FXGraphics2D g2d) {

        if (!this.isTransversable()) {
            g2d.setColor(Color.black);
        } else {
            g2d.setColor(Color.getHSBColor(this.heat * 2 / 360f, 1f, 1f));
        }

        g2d.fill(new Rectangle2D.Double(
                this.centre.getX() - this.width / 2f,
                this.centre.getY() - this.height / 2f,
                this.width,
                this.height));

        g2d.setColor(Color.black);

        switch (this.debug) {
            case HEAT:
                g2d.drawString(this.heat + "", (float) this.centre.getX(), (float) this.centre.getY());
                break;
            case VECTOR:
                g2d.drawLine((int)centre.getX(),
                        (int)centre.getY(),
                        (int)(centre.getX() + vector.getX() * this.width / 2f),
                        (int)(centre.getY() + vector.getY() * this.height / 2f)
                );
        }


    }

}
