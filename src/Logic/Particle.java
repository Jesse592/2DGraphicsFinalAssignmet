package Logic;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Particle {

    private FieldGrid fieldGrid;

    private Point2D position;
    private Point2D direction;
    private Point2D actualSpeed;

    private int targetSpeed;
    private int acceleration;

    private ArrayList<Particle> otherParticles;

    private int radius;

    private double opacity;
    private Color color;
    private BufferedImage image;

    public Particle(FieldGrid fieldGrid) {
        this(fieldGrid, 3);
    }

    public Particle(FieldGrid fieldGrid, int radius) {
        this(fieldGrid, new Point2D.Double(100, 100), radius);
    }

    public Particle(FieldGrid fieldGrid, Point2D position, int radius) {
        this(fieldGrid, position, radius, 250);
    }

    public Particle(FieldGrid fieldGrid, Point2D position, int radius, int targetSpeed) {
        this(fieldGrid, position, radius, targetSpeed, Color.BLACK);
    }

    public Particle(FieldGrid fieldGrid, Point2D posistion, int radius, int targetSpeed, Color color) {
        this.fieldGrid = fieldGrid;

        this.position = posistion;
        this.radius = radius;

        this.targetSpeed = targetSpeed;
        this.actualSpeed = new Point2D.Double(0,0);
        this.acceleration = 5;

        this.otherParticles = new ArrayList<>();

        this.opacity = 1;
        this.color = color;

        Point2D vector = this.fieldGrid.getAtPoint(this.position).getVector();
        if (vector != null) {
            this.direction = new Point2D.Double(vector.getX() * this.targetSpeed, vector.getY() * this.targetSpeed);
        } else {
            this.direction = new Point2D.Double(0, 0);
        }
        this.image = null;
    }

    public void draw(FXGraphics2D g2d) {
        Shape shape = new Ellipse2D.Double(this.position.getX() - this.radius, this.position.getY() - this.radius, this.radius * 2f, this.radius * 2f);

        g2d.setPaint(this.color);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) this.opacity));
        g2d.fill(shape);


        if (this.image != null) {
            AffineTransform tx = new AffineTransform();
            tx.translate(this.radius * 2, this.radius * 2);
            g2d.drawImage(this.image, tx, null);
        }

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

    }

    private FieldTile currentTile;
    private FieldTile oldTile;
    private Point2D oldPosition;

    public void update(double deltaTime) {

        this.oldTile = this.currentTile;
        this.currentTile = this.fieldGrid.getAtPoint(this.position);

        boolean collision = checkCollision(this.currentTile) && false;
        //noinspection ConstantConditions
        if (!collision && (this.currentTile != null)) {
            Point2D vector = this.currentTile.getVector();

            //Checking if the pace is legal
            if (vector != null) {
                this.direction = new Point2D.Double(vector.getX() * this.targetSpeed, vector.getY() * this.targetSpeed);

                //Accelerating
                handleAcceleration();
            } else {
                blockCollision();
            }




            //Setting te position
            this.oldPosition = this.position;
            this.position = new Point2D.Double(
                    this.position.getX() + (actualSpeed.getX() * deltaTime),
                    this.position.getY() + (actualSpeed.getY() * deltaTime)
            );

        }
    }

    private void blockCollision() {
        if(!this.currentTile.equals(this.oldTile)) {
               
        }
    }

    private boolean checkCollision(FieldTile locationTile) {
        if (locationTile == null || locationTile.getVector() == null) {
            return true;
        }

        for (Particle otherParticle : this.otherParticles) {
            if (this.position.distanceSq(otherParticle.position) < this.radius * 2 * this.radius) {
                return true;
            }
        }
        return false;
    }


    private void handleAcceleration() {
        if (this.direction.getX() < this.actualSpeed.getX()) {
            this.actualSpeed = new Point2D.Double(this.actualSpeed.getX() - this.acceleration, this.actualSpeed.getY());
        } else if (this.direction.getX() > this.actualSpeed.getX()) {
            this.actualSpeed = new Point2D.Double(this.actualSpeed.getX() + this.acceleration, this.actualSpeed.getY());
        }

        if (this.direction.getY() < this.actualSpeed.getY()) {
            this.actualSpeed = new Point2D.Double(this.actualSpeed.getX(), this.actualSpeed.getY() - this.acceleration);
        } else if (this.direction.getY() > this.actualSpeed.getY()) {
            this.actualSpeed = new Point2D.Double(this.actualSpeed.getX(), this.actualSpeed.getY() + this.acceleration);
        }
    }

    public void setOtherParticles(ArrayList<Particle> otherParticles) {
        this.otherParticles = otherParticles;
        this.otherParticles.remove(this);
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setTargetSpeed(int targetSpeed) {
        this.targetSpeed = targetSpeed;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
