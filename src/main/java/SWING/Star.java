package SWING;

import common.Vector2D;

import java.util.List;
import java.util.Random;

/**
 * Created by Angor on 22.11.2015.
 */
public class Star implements Runnable{
    public Vector2D current;
    public Vector2D delta;
    public double m;

    public  volatile boolean isAlive = true;
    private List<Star> stars;

    public Star(Vector2D vector, List <Star> stars) {
        current = vector;
        this.stars = stars;
        this.delta = new Vector2D();
    }

    public void run() {
        double force, r;
        Vector2D forceVector;
        //synchronized (this) {
            for (Star star : stars) {
                if (star != this && star.isAlive) {
                    //r = current.distance(star.current);
                    r = current.distance2(star.current);
                    // поглощение другой звёзды
                    if (r < Universe.SH * Universe.SH) {
                        synchronized (star){
                            if (m >= star.m) {
                                m = m + star.m;
                                star.delta.mult(star.m / (2*m));
                                this.delta.add(star.delta);
                                star.isAlive = false;
                            }
                        }
                    }

                    forceVector = current.sub(star.current);
                    forceVector.normalize();
                    force = Universe.gravitation(star.m, r);
                    forceVector.mult(force);
                    delta.add(forceVector);
                }
            }
        //}
    }

    public void Move() {
        Random rnd = new Random();
        int size = Universe.size * 2;
        //if (this.m < 5000) {
            current.add(delta);
        //}
        /*if (current.x > SWING.Universe.size) {
            current.x = -SWING.Universe.size;
            delta.x = delta.x * 0.9;
        }
        if (current.x < -SWING.Universe.size) {
            current.x = SWING.Universe.size;
            delta.x = delta.x * 0.9;
        }
        if (current.y > SWING.Universe.size) {
            current.y = -SWING.Universe.size;
            delta.y = delta.y * 0.9;
        }
        if (current.y < -SWING.Universe.size) {
            current.y = SWING.Universe.size;
            delta.y = delta.y * 0.9;
        }*/
        if (current.getLength() > Universe.size * 4) {
            //this.isAlive = false;
            /*this.current.x = rnd.nextDouble() * size / 2 - size / 4;
            this.current.y = rnd.nextDouble() * 200 - 100;
            this.delta.x = 0;
            if (this.current.x > 0) {
                this.delta.y = rnd.nextDouble()*0.1;
            } else {
                this.delta.y = -rnd.nextDouble()*0.1;
            }*/
            /*current.x = 0;
            current.y = 0;
            delta.x = 0;
            delta.y =0;*/
            Vector2D v = new Vector2D((rnd.nextDouble() * size / 2) + 100, rnd.nextDouble() * Math.PI * 2);
            v.polarToDecart();
            Vector2D speed = new Vector2D(v);
            speed.mult(Math.sqrt(Universe.G*3/v.getLength())*rnd.nextDouble()*0.4);
            speed.ortogonale(true);
            this.current = v;
            this.delta = speed;
        }
        if (delta.x > Universe.C) delta.x = Universe.C;
        if (delta.y > Universe.C) delta.y = Universe.C;
    }
}
