package GUI;

import Logic.FieldGrid;
import Logic.FieldTile;
import Logic.Particle;
import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class ParticleManager {

    private ArrayList<Particle> particles;
    private FieldGrid fieldGrid;

    private int radius;
    private double opacity;

    private int minSpeed;
    private int maxSpeed;

    private int minAcceleration;
    private int maxAcceleration;

    private Color color;
    private BufferedImage image;


    public ParticleManager(FieldGrid fieldGrid, int size) {
        this.fieldGrid = fieldGrid;
        this.particles = new ArrayList<>();

        this.radius = 10;
        this.opacity = 0.6;

        this.minSpeed = 150;
        this.maxSpeed = 300;
        this.minAcceleration = 7;
        this.maxAcceleration = 10;

        this.color = Color.black;

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/Images/Particle1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.image = null;
    }


    private void buildParticles(int size) {
        this.particles = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            Particle particle = new Particle(this.fieldGrid, new Point2D.Double(Math.random()*500+100, Math.random()*500+100), this.radius, (int)(this.minSpeed + (Math.random() * (this.maxSpeed - this.minSpeed))), this.color);
            this.particles.add(particle);
        }

        for (Particle particle : this.particles) {
            particle.setOtherParticles(new ArrayList<>(this.particles));
        }
    }

    public void applyParticles(){
        for (Particle particle : this.particles) {
            particle.setRadius(this.radius);
            particle.setOpacity(this.opacity);
            particle.setColor(this.color);
            particle.setTargetSpeed((int)(this.minSpeed + (Math.random() * (this.maxSpeed - this.minSpeed))));
            particle.setAcceleration((int)(this.minAcceleration + (Math.random() * (this.maxAcceleration - this.minAcceleration))));
            particle.setImage(this.image);
        }
    }

    public void draw(FXGraphics2D g2d) {
        for (Particle particle : this.particles) {
            particle.draw(g2d);
        }
    }

    public void update(double deltaTime) {
       for (Particle particle : this.particles) {
           particle.update(deltaTime);
       }
    }

    public void setsize(int size) {
        buildParticles(size);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    public int getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(int minSpeed) {
        this.minSpeed = minSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMinAcceleration() {
        return minAcceleration;
    }

    public void setMinAcceleration(int minAcceleration) {
        this.minAcceleration = minAcceleration;
    }

    public int getMaxAcceleration() {
        return maxAcceleration;
    }

    public void setMaxAcceleration(int maxAcceleration) {
        this.maxAcceleration = maxAcceleration;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
