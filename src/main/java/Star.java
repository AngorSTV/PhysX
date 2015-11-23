import java.util.List;

/**
 * Created by Angor on 22.11.2015.
 */
public class Star {
    public Vector carent;
    private Vector delta;
    public int m;

    public Star(Vector vector){
        this.carent = vector;
    }

    public void Calculate(List<Star> stars){
        for(Star star:stars){
            if(star != this) {
                //delta = delta.add(star.carent);
            }
        }
    }

    public void Move(){
    this.carent = carent.add(delta);
    }
}
