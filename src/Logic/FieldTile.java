package Logic;

import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Point2D;

public class FieldTile {

    private int heat;
    private Point2D vector;
    private Point2D centre;
    private boolean transversable;

    private int width;
    private int height;

    public FieldTile(Point2D centre, int width, int height) {
        this.centre = centre;
        this.heat = 0;

        this.width = width;
        this.height = height;

        this.vector = new Point2D.Double(0,0);
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

    //Todo: draw method
    public void draw(FXGraphics2D g2d) {
        System.out.println(this.heat);
        g2d.drawString(this.heat + "", (float)this.centre.getX(), (float)this.centre.getY());
    }

}
