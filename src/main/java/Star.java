import java.util.List;

/**
 * Created by Angor on 22.11.2015.
 */
public class Star {
    public Vector carent;
    public Vector delta;
    public int m;

    public Star(Vector vector){
        this.carent = vector;
    }

    public void Calculate(List<Star> stars){
        double force;
        for(Star star:stars){
            if(star != this && star != null) {
                force = 2/carent.length(star.carent);

                //delta = delta.add(star.carent);
            }
        }
    }

    public void Move(){
    carent.add(delta);
    }
}
