package Logic;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Particle {

    private FieldGrid fieldGrid;

    private Point2D posistion;
    private Point2D speed;
    private boolean collision;
    private ArrayList<Particle> otherParticles;

    private int width;
    private int height;


    private int targetSpeed;
    private Paint color;
    private BufferedImage image;

    public Particle(FieldGrid fieldGrid) {
        this(fieldGrid, 10, 10);
    }

    public Particle(FieldGrid fieldGrid, int width, int height) {
        this(fieldGrid, new Point2D.Double(100, 100), width, height);
    }

    public Particle(FieldGrid fieldGrid, Point2D posistion, int width, int height) {
        this(fieldGrid, posistion, width, height, 250);
    }

    public Particle(FieldGrid fieldGrid, Point2D posistion, int width, int height, int targetSpeed) {
        this(fieldGrid, posistion, true, width, height, targetSpeed, Color.BLACK);
    }

    public Particle(FieldGrid fieldGrid, Point2D posistion, boolean collision, int width, int height, int targetSpeed, Paint color) {
        this.fieldGrid = fieldGrid;

        this.posistion = posistion;
        this.collision = collision;
        this.width = width;
        this.height = height;
        this.targetSpeed = targetSpeed;
        this.color = color;

        this.otherParticles = new ArrayList<>();

        Point2D vector = this.fieldGrid.getAtPoint(this.posistion).getVector();
        this.speed = new Point2D.Double(vector.getX() * this.targetSpeed, vector.getY() * this.targetSpeed);
        this.image = null;
    }

    public void draw(FXGraphics2D g2d) {
        Shape shape = new Ellipse2D.Double(this.posistion.getX() - this.width / 2f, this.posistion.getY() - this.height / 2f, this.width, this.height);

        g2d.setPaint(this.color);
        g2d.fill(shape);

        g2d.setColor(Color.black);
        g2d.draw(shape);

        if (this.image != null) {
            AffineTransform tx = new AffineTransform();
            tx.translate(this.width, this.height);
            g2d.drawImage(this.image, tx, null);
        }

    }

    public void update(double deltaTime) {
        boolean curCollision = false;
        if(this.collision) {
            curCollision = checkCollision();
        }

        if (!curCollision) {
            this.posistion = new Point2D.Double(
                    this.posistion.getX() + (this.speed.getX() * deltaTime),
                    this.posistion.getY() + (this.speed.getY() * deltaTime)
            );

            FieldTile locationTile = this.fieldGrid.getAtPoint(this.posistion);
            if (locationTile != null && locationTile.getVector() != null) {
                Point2D vector = this.fieldGrid.getAtPoint(this.posistion).getVector();
                this.speed = new Point2D.Double(vector.getX() * this.targetSpeed, vector.getY() * this.targetSpeed);
            } else {
                collision(deltaTime);
            }

        } else {
            collision(deltaTime);
        }
    }

    private void collision(double deltaTime) {
        this.posistion = new Point2D.Double(
                this.posistion.getX() + (Math.random() * 250 * deltaTime),
                this.posistion.getY() + (Math.random() * 250 * deltaTime)
        );
    }

    private boolean checkCollision() {
        for (Particle otherParticle : this.otherParticles) {
            if (this.posistion.distanceSq(otherParticle.posistion) < this.width) {
                return true;
            }
        }
        return false;
    }

    public Point2D getPosistion() {
        return posistion;
    }

    public void setPosistion(Point2D posistion) {
        this.posistion = posistion;
    }

    public Point2D getSpeed() {
        return speed;
    }

    public void setSpeed(Point2D speed) {
        this.speed = speed;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getTargetSpeed() {
        return targetSpeed;
    }

    public void setTargetSpeed(int targetSpeed) {
        this.targetSpeed = targetSpeed;
    }

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setOtherParticles(ArrayList<Particle> otherParticles) {
        this.otherParticles = otherParticles;
        this.otherParticles.remove(this);
    }
}
