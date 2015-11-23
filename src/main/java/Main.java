import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static List<Star> stars = new ArrayList<>();

    public static void main(String[] args) {
        initStars();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShow();
            }
        });
    }

    private static void createAndShow(){

        JFrame frame = new JFrame("PhysX Stars");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.add(new MyPanel(stars));
        frame.setVisible(true);
    }

    private static void initStars (){
        Random rnd = new Random();
        for (int i=0; i<100; i++){
            Vector v = new Vector(rnd.nextDouble()*500, rnd.nextDouble()*500);
            Star star = new Star(v);
            stars.add(star);
        }
    }

}
