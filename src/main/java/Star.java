import java.util.List;

/**
 * Created by Angor on 22.11.2015.
 */
public class Star {
    public Vector carent;
    public Vector delta;
    public double m;

    public boolean isAlive = true;

    private static final double G = 0.1; //гравитационная постоянная с учётом масштаба симуляции
    private static final double SH = 0.1; // радиус сферы Шварцшильда
    private static final double C = 100; // скорость света
    public static final int sizeUniverse = 5000; // размер вселенной

    public Star(Vector vector){
        this.carent = vector;
    }

    public void Calculate(List<Star> stars){
        double force;
        Vector toForce = new Vector();

        for(Star star:stars){
            if(star != this && star.isAlive) {
                // слияние звёзд
                if (carent.distance(star.carent)< SH*Math.sqrt(m)){
                    this.m = this.m + star.m;
                    star.isAlive = false;
                    break;
                }

                toForce = carent.sub(star.carent);
                toForce.normalize();
                force = G *(this.m*star.m)/Math.pow(carent.distance(star.carent),1.75)/(this.m*2);
                toForce.mult(force);

                delta.add(toForce);
            }
        }
    }

    public void Move(){
    carent.add(delta);
        if (carent.x > sizeUniverse) carent.x = -sizeUniverse;
        if (carent.x < -sizeUniverse) carent.x = sizeUniverse;
        if (carent.y > sizeUniverse) carent.y = -sizeUniverse;
        if (carent.y < -sizeUniverse) carent.y = sizeUniverse;
        if (delta.x > C) delta.x = C;
        if (delta.y > C) delta.y = C;
    }
}
