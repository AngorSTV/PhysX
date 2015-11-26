import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Angor on 23.11.2015.
 */
public class DrawPanel extends JPanel implements Runnable {

    private static final int maxThreads = 90;
    private long t;
    private static double totalFrame = 0;
    private List<Star> stars;

    public DrawPanel(List<Star> stars) {
        super();
        this.stars = stars;
        setBackground(new Color(0));
        setForeground(new Color(255, 255, 255));
        new Thread(this).start();
    }

    @Override
    public void run() {
        Process th[] = new Process[maxThreads];
        Star star;
        while (true) {
            long t1 = System.nanoTime();
            Iterator<Star> iStar = stars.iterator();
            while (iStar.hasNext()) {
                if (!iStar.next().isAlive) iStar.remove();
            }

            // много поточная обработка звёзд
            iStar = stars.iterator();
            for (int i=0;i<maxThreads;i++){
                star = iStar.next();
                th[i] = new Process(stars,star);
                th[i].start();
            }

            for(int k=0;k<maxThreads;k++){
                try {
                    th[k].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!iStar.hasNext()) {
                    break;
                }
                star = iStar.next();
                th[k] = new Process(stars, star);
                th[k].start();

            }
            // ------------------------

            for (Star star2 : stars) {
                //star.Calculate(stars);
                star2.Move();
            }
            repaint();
            t = System.nanoTime() - t1;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int width = getWidth();
        int height = getHeight();

        double ratio = (double) width / (Star.sizeUniverse * 2);

        int x, y, r;

        totalFrame++;

        double totalMass = 0;
        for (Star star : stars) {
            totalMass = totalMass + star.m;
        }
        /*synchronized (stars) {
            Iterator<Star> iStar = stars.iterator();
            while (iStar.hasNext()) {
                x = (int) (ratio * iStar.next().carent.x);
                y = (int) (ratio * iStar.next().carent.y);
                r = (int) (ratio * Math.sqrt(iStar.next().m));

                g.drawOval(x + width / 2, y + height / 2, r, r);
                g.fillOval(x + width / 2, y + height / 2, r, r);
            }
        }*/

        for (Star star : stars) {
            x = (int) (ratio * star.carent.x);
            y = (int) (ratio * star.carent.y);
            r = (int) (ratio * Math.sqrt(star.m));

            g.drawOval(x + width / 2, y + height / 2, r, r);
            g.fillOval(x + width / 2, y + height / 2, r, r);
        }

        double fps = t;
        fps = fps/1000000000;
        fps = 1/fps;
        g.drawString("Total stars:" + stars.size(), 1, 15);
        g.drawString("Total mass: " + String.valueOf((int) totalMass), 1, 30);
        g.drawString("Total frame:" + String.valueOf((int) totalFrame), 1, 45);
        g.drawString("FPS: " + String.valueOf(fps), 1, 60);
        g.drawString("Width: " + String.valueOf(width), 1, 75);

    }
}
