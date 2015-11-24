import java.util.List;

/**
 * Created by Angor on 22.11.2015.
 */
public class Star {
    public Vector carent;
    public Vector delta;
    public double m;
    private static double G = 6.673848080e-11;

    public Star(Vector vector){
        this.carent = vector;
    }

    public void Calculate(List<Star> stars){
        double force;
        Vector toForce = new Vector();
        for(Star star:stars){
            if(star != this && star != null) {
                toForce = carent.sub(star.carent);
                toForce.normalize();
                force = G * (this.m*star.m)/Math.pow(carent.distance(star.carent),2);
                toForce.mult(force);

                delta.add(toForce);
            }
        }
    }

    public void Move(){
    carent.add(delta);
    }
}
