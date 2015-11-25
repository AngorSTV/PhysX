import java.util.Iterator;
import java.util.List;

/**
 * Created by Angor on 22.11.2015.
 */
public class Star {
    public Vector carent;
    public Vector delta;
    public double m;

    public boolean isAlive = true;

    private static final double G = 0.5; //гравитационная постоянная с учётом масштаба симуляции
    private static final double SH = 1; // радиус сферы Шварцшильда

    public Star(Vector vector){
        this.carent = vector;
    }

    public void Calculate(List<Star> stars){
        double force;
        Vector toForce = new Vector();

        /*Iterator<Star> iStar = stars.iterator();
        while (iStar.hasNext()){
            if (carent.distance(iStar.next().carent)< SH){
                this.m = this.m + iStar.next().m;
                iStar.remove();
                break;
            }
            toForce = carent.sub(iStar.next().carent);
            toForce.normalize();
            force = G *(this.m*iStar.next().m)/Math.pow(carent.distance(iStar.next().carent),1.4)/this.m;
            toForce.mult(force);

            delta.add(toForce);
        }*/
        for(Star star:stars){
            if(star != this && star.isAlive) {
                // слияние звёзд
                if (carent.distance(star.carent)< SH){
                    this.m = this.m + star.m;
                    star.isAlive = false;
                    break;
                }

                toForce = carent.sub(star.carent);
                toForce.normalize();
                force = G *(this.m*star.m)/Math.pow(carent.distance(star.carent),1.4)/(this.m*2);
                toForce.mult(force);

                delta.add(toForce);
            }
        }
    }

    public void Move(){
    carent.add(delta);
        if (Math.abs(carent.x) > 300){
            delta.x = -delta.x*0.9;
            carent.x = 299;
        }
        if (Math.abs(carent.y) > 300){
            delta.y = -delta.y*0.9;
            carent.y = 299;
        }
        if (Math.abs(carent.x) > 1000 && Math.abs(carent.y) > 1000) this.isAlive = false;
    }
}
