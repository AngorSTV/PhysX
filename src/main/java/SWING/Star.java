package SWING;

import common.Vector2D;

import java.util.List;
import java.util.Random;

/**
 * Created by Angor on 22.11.2015.
 */
public class Star implements Runnable {
    public Vector2D current;
    public Vector2D delta;
    public double m;

    public volatile boolean isAlive = true;
    private List<Star> stars;

    public Star(Vector2D vector, List<Star> stars) {
        current = vector;
        this.stars = stars;
        this.delta = new Vector2D();
    }

    public void run() {
        double force, r;
        Vector2D forceVector;
        for (Star star : stars) {
            if (star != this && star.isAlive) {
                //r = current.distance(star.current);
                r = current.distance2(star.current);
                // поглощение другой звёзды
                if (r < Universe.SH * m/2) {
                    synchronized (star) {
                        if (m >= star.m) {
                            m = m + star.m;
                            star.delta.mult(star.m / (2 * m));
                            this.delta.add(star.delta);
                            star.isAlive = false;
                        }
                    }
                } else {
                    forceVector = current.sub(star.current);
                    forceVector.normalize();
                    force = Universe.gravitation(star.m, r);
                    forceVector.mult(force);
                    delta.add(forceVector);
                }
            }
        }
    }

    public void move() {
        Random rnd = new Random();
        int size = Universe.size * 2;
        current.add(delta);
        if (current.getLength() > Universe.size * 10) {
            Vector2D v = new Vector2D((rnd.nextDouble() * size / 2) + 100, rnd.nextDouble() * Math.PI * 2);
            v.polarToDecart();
            Vector2D speed = new Vector2D(v);
            speed.mult(Math.sqrt(Universe.G * 3 / v.getLength()) * rnd.nextDouble() * 0.4);
            speed.ortogonale(true);
            this.current = v;
            this.delta = speed;
        }
//        if (delta.x > Universe.C) delta.x = Universe.C;
//        if (delta.y > Universe.C) delta.y = Universe.C;
        if (delta.getLength() > Universe.C) {
            this.isAlive = false;
        }
    }
}
