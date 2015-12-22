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
    }

    public void run() {
        double force, r;
        Vector2D forceVector;
        //synchronized (this) {
            for (Star star : stars) {
                if (star != this && star.isAlive) {
                    r = current.distance(star.current);
                    // поглощение другой звёзды
                    if (r < Universe.SH * Math.sqrt(m)) {
                        synchronized (star){
                            if (m >= star.m) {
                                m = m + star.m;
                                star.delta.mult(star.m / m);
                                this.delta.add(star.delta);
                                star.isAlive = false;
                                //break;
                            } /*else {
                                star.m = m + star.m;
                                delta.mult(m / star.m);
                                star.delta.add(delta);
                                this.isAlive = false;
                                break;
                            }*/
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
        if (this.m < 5000) {
            current.add(delta);
        }
        /*if (current.x > Universe.size) {
            current.x = -Universe.size;
            delta.x = delta.x * 0.9;
        }
        if (current.x < -Universe.size) {
            current.x = Universe.size;
            delta.x = delta.x * 0.9;
        }
        if (current.y > Universe.size) {
            current.y = -Universe.size;
            delta.y = delta.y * 0.9;
        }
        if (current.y < -Universe.size) {
            current.y = Universe.size;
            delta.y = delta.y * 0.9;
        }*/
        if (current.getLength() > Universe.size * 2) {
            //this.isAlive = false;
            this.current.x = rnd.nextDouble() * size / 2 - size / 4;
            this.current.y = rnd.nextDouble() * 200 - 100;
            this.delta.x = 0;
            if (this.current.x > 0) {
                this.delta.y = rnd.nextDouble()*0.1;
            } else {
                this.delta.y = -rnd.nextDouble()*0.1;
            }
            /*current.x = 0;
            current.y = 0;
            delta.x = 0;
            delta.y =0;*/
        }
        /*if (delta.x > Universe.C) delta.x = Universe.C;
        if (delta.y > Universe.C) delta.y = Universe.C;*/
    }
}
