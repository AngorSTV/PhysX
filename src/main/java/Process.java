import java.util.List;

/**
 * Created by Angor on 26.11.2015.
 */
public class Process extends Thread{

    private Star star;
    private List<Star> stars;

    public Process (List <Star> stars, Star star){
        this.star = star;
        this.stars = stars;
    }

    public void run() {
        star.Calculate(stars);
        //star.Move();
    }
}
