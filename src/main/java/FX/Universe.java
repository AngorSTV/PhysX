package FX;

import common.Vector2D;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Андрей on 12.02.2016.
 */
public class Universe {
    private double G = 0.0007; //гравитационная постоянная с учётом масштаба симуляции
    private double SH = 0.03; // радиус сферы Шварцшильда
    private double C = 100000; // скорость света
    private int size = 1000; // размер вселенной
    private double distortionSpace = 2;

    private int starsQuantity = 1000;
    private int massBand = 100;

    private List<Star> stars = new LinkedList<>();

    public Universe() {
        Random rnd = new Random();
        for (int i = 0; i < starsQuantity; i++) {
            Vector2D v = new Vector2D((rnd.nextDouble() * size) + 100, rnd.nextDouble() * Math.PI * 2);
            v.polarToDecart();
            Vector2D speed = new Vector2D(v);
            speed.mult(Math.sqrt(G * 3 / v.getLength()) * rnd.nextDouble() * 0.3);
            speed.ortogonale(true);
            Star star = new Star(v, this);
            star.delta = speed;
            star.m = rnd.nextDouble() * massBand + 1;
            stars.add(star);
        }
    }

    public double getG() {
        return G;
    }

    public void setG(double g) {
        G = g;
    }

    public double getSH() {
        return SH;
    }

    public void setSH(double SH) {
        this.SH = SH;
    }

    public double getC() {
        return C;
    }

    public void setC(double c) {
        C = c;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStarsQuantity() {
        return stars.size();
    }

    public void setStarsQuantity(int starsQuantity) {
        this.starsQuantity = starsQuantity;
    }

    public int getMassBand() {
        return massBand;
    }

    public void setMassBand(int massBand) {
        this.massBand = massBand;
    }

    public List<Star> getStars() {
        return stars;
    }

    public void setStars(List<Star> stars) {
        this.stars = stars;
    }

    public double getDistortionSpace() {
        return distortionSpace;
    }

    public void setDistortionSpace(double distortionSpace) {
        this.distortionSpace = distortionSpace;
    }

    public double gravitation(double m2, double r) {
        return G * m2 / Math.pow(r, distortionSpace);
    }
}
