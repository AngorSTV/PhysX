import java.util.List;

/**
 * Created by Angor on 22.11.2015.
 */
public class Star {
    public Vector2D carent;
    public Vector2D delta;
    public double m;

    public boolean isAlive = true;

    private static final double G = 0.7; //гравитационная постоянная с учётом масштаба симуляции
    private static final double SH = 0.1; // радиус сферы Шварцшильда
    private static final double C = 1000; // скорость света
    public static final int sizeUniverse = 50000; // размер вселенной

    public Star(Vector2D vector2D){
        carent = vector2D;
    }

    public void Calculate(List<Star> stars){
        double force;
        Vector2D toForce = new Vector2D();

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
                force = G *(this.m*star.m)/Math.pow(carent.distance(star.carent),1.4)/(this.m*2);
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
