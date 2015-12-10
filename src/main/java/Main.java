import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends JFrame{

    private static final int starsQuantity = 300;
    private static final int massBand = 30;


    Main(String s) {
        super(s);

        initStars();
        //initSolarSystem();
        initAlfaSystem();

        DrawPanel panel = new DrawPanel();
        panel.setPreferredSize(new Dimension(600, 600));
        add(panel);
        pack();
        setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Main("Star Sim");
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });
    }

    private void initStars (){
        Random rnd = new Random();
        int size = Universe.size*2;
        for (int i=0; i<starsQuantity; i++){
            Vector2D v = new Vector2D(rnd.nextDouble()*size/2-size/4, rnd.nextDouble()*size/2-size/4);
            Vector2D speed = new Vector2D((rnd.nextDouble() - 0.5)*0.1, (rnd.nextDouble() - 0.5)*0.1);
            //Vector2D speed = new Vector2D(0, 0);
            Star star = new Star(v);
            star.delta = speed;
            star.m = rnd.nextDouble()*massBand + 1;
            Universe.stars.add(star);
        }
    }
    private void initSolarSystem(){

        //Sol
        Vector2D point = new Vector2D(0,0);
        Vector2D speed = new Vector2D(0, 0);
        Star star = new Star(point);
        star.delta = speed;
        star.m = 5000;
        Universe.stars.add(star);

        //Venus
        point = new Vector2D(130,0);
        speed = new Vector2D(0, 0.15);
        star = new Star(point);
        star.delta = speed;
        star.m = 7;
        Universe.stars.add(star);

        //Earth
        point = new Vector2D(185,0);
        speed = new Vector2D(0, 0.128);
        star = new Star(point);
        star.delta = speed;
        star.m = 10;
        Universe.stars.add(star);

        //Moon
        point = new Vector2D(193,0);
        speed = new Vector2D(0, 0.15);
        star = new Star(point);
        star.delta = speed;
        star.m = 0.5;
        Universe.stars.add(star);

        //Jupiter
        point = new Vector2D(380,0);
        speed = new Vector2D(0, 0.11);
        star = new Star(point);
        star.delta = speed;
        star.m = 50;
        Universe.stars.add(star);

        //Io
        point = new Vector2D(390,0);
        speed = new Vector2D(0, 0.16);
        star = new Star(point);
        star.delta = speed;
        star.m = 0.1;
        Universe.stars.add(star);

        //Saturn
        point = new Vector2D(480,0);
        speed = new Vector2D(0, 0.075);
        star = new Star(point);
        star.delta = speed;
        star.m = 30;
        Universe.stars.add(star);

    }

    private void initAlfaSystem(){
        Random rnd = new Random();
        int size = Universe.size*2;
        for (int i=0; i<starsQuantity; i++){
            Vector2D v = new Vector2D(-rnd.nextDouble()*size/2, rnd.nextDouble()*200-100);
            Vector2D speed = new Vector2D(0, -rnd.nextDouble()*0.5);
            //Vector2D speed = new Vector2D(0, 0);
            Star star = new Star(v);
            star.delta = speed;
            star.m = rnd.nextDouble()*massBand;
            Universe.stars.add(star);
        }
        for (int i=0; i<starsQuantity; i++){
            Vector2D v = new Vector2D(rnd.nextDouble()*size/2, rnd.nextDouble()*200-100);
            Vector2D speed = new Vector2D(0, rnd.nextDouble()*0.5);
            //Vector2D speed = new Vector2D(0, 0);
            Star star = new Star(v);
            star.delta = speed;
            star.m = rnd.nextDouble()*massBand;
            Universe.stars.add(star);
        }
        //BlackHole
        Vector2D point = new Vector2D(0,0);
        Vector2D speed = new Vector2D(0, 0);
        Star star = new Star(point);
        star.delta = speed;
        star.m = 5000;
        Universe.stars.add(star);
    }

}
