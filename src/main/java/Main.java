import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Main extends JFrame{

    private static final int starsQuantity = 2;
    private static final int massBand = 15;

    public static List<Star> stars = new ArrayList<>();

    Main(String s) {
        super(s);

        initStars();

        DrawPanel panel = new DrawPanel(stars);
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
        for (int i=0; i<starsQuantity; i++){
            Vector v = new Vector(rnd.nextDouble()*600-300, rnd.nextDouble()*600-300);
            Vector speed = new Vector(rnd.nextDouble()- 0.5, rnd.nextDouble() - 0.5);
            Star star = new Star(v);
            star.delta = speed;
            star.m = rnd.nextDouble()*massBand;
            stars.add(star);
        }
    }

}
