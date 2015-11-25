import java.util.List;

/**
 * Created by Angor on 22.11.2015.
 */
public class Star {
    public Vector carent;
    public Vector delta;
    public double m;
    private static final double G = 0.8; //гравитационная постоянная с учётом масштаба симуляции
    private static final double SH = 1; // радиус сферы Шварцшильда

    public Star(Vector vector){
        this.carent = vector;
    }

    public void Calculate(List<Star> stars){
        double force;
        Vector toForce = new Vector();
        for(Star star:stars){
            if(star != this && star != null) {
                // слияние звёзд
                if (carent.distance(star.carent)< SH){
                    this.m = this.m + star.m;
                    star = null;
                    break;
                }

                toForce = carent.sub(star.carent);
                toForce.normalize();
                force = G *(this.m*star.m)/Math.pow(carent.distance(star.carent),1.4)/this.m;
                toForce.mult(force);

                delta.add(toForce);
            }
        }
    }

    public void Move(){
    carent.add(delta);
        if (Math.abs(carent.x) > 300){
            delta.x = -delta.x*0.99;
        }
        if (Math.abs(carent.y) > 300){
            delta.y = -delta.y*0.99;
        }
    }
}
