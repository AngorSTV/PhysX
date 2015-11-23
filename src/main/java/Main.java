import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Main extends JFrame{

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
        for (int i=0; i<200; i++){
            Vector v = new Vector(rnd.nextDouble()*600, rnd.nextDouble()*600);
            Star star = new Star(v);
            stars.add(star);
        }
    }

}
