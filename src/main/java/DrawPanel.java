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
            Iterator<Star> iStar = stars.iterator();
            while (iStar.hasNext()){
                if (!iStar.next().isAlive) iStar.remove();
            }

            for(Star star:stars){
                star.Calculate(stars);
                star.Move();
            }
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int width = getWidth();
        int height = getHeight();

        int ratio = 1;

        int x, y, r;

        double totalMass =0;
        for(Star star:stars){
            totalMass = totalMass + star.m;
        }

        for (Star star : stars) {
            x = ratio * (int) star.carent.x;
            y = ratio * (int) star.carent.y;
            r = ratio * (int) (star.m * 0.1);

            g.drawOval(x + 300, y + 300, r, r);
            g.fillOval(x + 300, y + 300, r, r);
        }
        g.drawString("Total stars:"+stars.size(), 1, 20);
        g.drawString("Total mass: "+ String.valueOf(totalMass), 1, 40);
    }
}
