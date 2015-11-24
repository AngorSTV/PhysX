import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Angor on 23.11.2015.
 */
public class DrawPanel extends JPanel implements Runnable {

    private long t = System.nanoTime();
    public java.util.List<Star> stars = new ArrayList<>();

    public DrawPanel(List<Star> stars) {
        super();
        this.stars = stars;
        setBackground(new Color(0));
        setForeground(new Color(255, 255, 255));
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            for(Star star:stars){
                star.Calculate(stars);
                star.Move();
            }
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {}
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int width = getWidth();
        int height = getHeight();

        int ratio = 1;

        int x, y, r;

        for (Star star : stars) {
            x = ratio * (int) star.carent.x;
            y = ratio * (int) star.carent.y;
            r = ratio * (int) star.m;
            //g.drawLine(x, y, x, y);
            g.drawOval(x+300, y+300, r, r);
        }
        /*Graphics2D g2d = (Graphics2D) g;

        long tm = System.nanoTime() - t;
        double angle = tm / 300000000.0;

        double sn = Math.sin(Math.sin(angle) * 0.5);
        double cs = Math.cos(Math.sin(angle) * 0.5);
        int x = (int) (250 * sn + 150);
        int y = (int) (250 * cs);
        g2d.drawLine(150, 0, x, y);
        g2d.drawOval(x - 20, y - 20, 40, 40);*/
    }
}
