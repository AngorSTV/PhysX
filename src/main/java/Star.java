/**
 * Created by Angor on 22.11.2015.
 */
public class Star  implements Runnable{
    public Vector2D current;
    public Vector2D delta;
    public double m;

    public boolean isAlive = true;

    public Star(Vector2D vector){
        current = vector;
    }

    public void run(){
        double force, r;
        Vector2D forceVector;

        for(Star star:Universe.stars){
            if(star != this && star.isAlive) {
                r = current.distance(star.current);
                // слияние звёзд
                if (r < Universe.SH*Math.sqrt(m)){
                    m = m + star.m;
                    star.isAlive = false;
                    break;
                }

                forceVector = current.sub(star.current);
                forceVector.normalize();
                force = Universe.gravitation(m, star.m, r)/2*m;
                forceVector.mult(force);
                delta.add(forceVector);
            }
        }
    }

    public void Move(){
    current.add(delta);
        if (current.x > Universe.size) current.x = -Universe.size;
        if (current.x < -Universe.size) current.x = Universe.size;
        if (current.y > Universe.size) current.y = -Universe.size;
        if (current.y < -Universe.size) current.y = Universe.size;
        if (delta.x > Universe.C) delta.x = Universe.C;
        if (delta.y > Universe.C) delta.y = Universe.C;
    }
}
