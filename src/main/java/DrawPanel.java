import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * Created by Angor on 23.11.2015.
 */
public class DrawPanel extends JPanel implements Runnable {

    private static final int maxThreads = 90;
    private long t;
    private static double totalFrame = 0;
    //private List<Star> stars;

    public DrawPanel() {
        super();
        setBackground(new Color(0));
        setForeground(new Color(255, 255, 255));
        new Thread(this).start();
    }

    @Override
    public void run() {
        //Process th[] = new Process[maxThreads];
        //Star star;
        while (true) {
            long t1 = System.nanoTime();
            Iterator<Star> iStar = Universe.stars.iterator();
            while (iStar.hasNext()) {
                if (!iStar.next().isAlive) iStar.remove();
            }

            /*/ много поточная обработка звёзд
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
            */// ------------------------

            for (Star star : Universe.stars) {
                /*try {
                    Thread th = new Thread(star);
                    th.start();
                    th.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                star.run();
                star.Move();
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
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

        double ratio = (double) width / (Universe.size * 2);

        int x, y, r;

        totalFrame++;

        double totalMass = 0;
        for (Star star : Universe.stars) {
            totalMass = totalMass + star.m;
        }
        /*synchronized (stars) {
            Iterator<Star> iStar = stars.iterator();
            while (iStar.hasNext()) {
                x = (int) (ratio * iStar.next().current.x);
                y = (int) (ratio * iStar.next().current.y);
                r = (int) (ratio * Math.sqrt(iStar.next().m));

                g.drawOval(x + width / 2, y + height / 2, r, r);
                g.fillOval(x + width / 2, y + height / 2, r, r);
            }
        }*/

        for (Star star : Universe.stars) {
            x = (int) (ratio * star.current.x);
            y = (int) (ratio * star.current.y);
            r = (int) (ratio * Math.sqrt(star.m));
            if (star.m < 5000){
            g.drawOval((x + width / 2)-r/2, (y + height / 2)-r/2, r, r);
            g.fillOval((x + width / 2)-r/2, (y + height / 2)-r/2, r, r);}
        }

        double fps = t;
        fps = fps/1000000000;
        fps = 1/fps;
        g.drawString("Total stars:" + Universe.stars.size(), 1, 15);
        g.drawString("Total mass: " + String.valueOf((int) totalMass), 1, 30);
        g.drawString("Total frame:" + String.valueOf((int) totalFrame), 1, 45);
        g.drawString("FPS: " + String.valueOf((int)fps), 1, 60);
        g.drawString("Width: " + String.valueOf(width), 1, 75);

    }
}
