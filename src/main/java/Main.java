import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends JFrame{

    private static final int starsQuantity = 500;
    private static final int massBand = 50;

    //private List<Star> stars = new ArrayList<>();

    Main(String s) {
        super(s);

        initStars();

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
        } catch (Exception e) {}
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
            //Vector2D speed = new Vector2D(rnd.nextDouble()*10- 5, rnd.nextDouble()*10 - 5);
            Vector2D speed = new Vector2D(0, 0);
            Star star = new Star(v);
            star.delta = speed;
            star.m = rnd.nextDouble()*massBand + 1;
            Universe.stars.add(star);
        }
    }

}
