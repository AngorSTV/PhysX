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
                force = G*1000000*(this.m*star.m)/Math.pow(carent.distance(star.carent),1.5);
                toForce.mult(force);

                delta.add(toForce);
            }
        }
    }

    public void Move(){
    carent.add(delta);
        if (Math.abs(carent.x) > 310){
            delta.x = -delta.x*0.9;
        }
        if (Math.abs(carent.y) > 310){
            delta.y = -delta.y*0.9;
        }
    }
}
