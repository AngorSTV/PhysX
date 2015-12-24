import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Angor on 23.11.2015.
 */
public class DrawPanel extends JPanel implements Runnable {

    private int maxThreads;
    private long t;
    private double fps;
    private double totalMass = 0;
    private static double totalFrame = 0;
    private List<Star> stars;
    private Thread th[];
    private Star star;


    public DrawPanel(List<Star> stars) {
        super();
        this.stars = stars;
        int processors = Runtime.getRuntime().availableProcessors();
        maxThreads = processors * 4;
        this.th = new Thread[maxThreads];
        // подсчёт общей массы
        for (Star star : stars) {
            totalMass = totalMass + star.m;
        }

        setBackground(new Color(0));
        setForeground(new Color(255, 255, 255));
        new Thread(this).start();
    }

    @Override
    public void run() {
        long t1;

        while (true) {

            t1 = System.currentTimeMillis();

            //Чистка масива от мёртвых объектов
            Iterator<Star> iStar = stars.iterator();
            while (iStar.hasNext()) {
                if (!iStar.next().isAlive) iStar.remove();
            }

            //multiOne();
            multiTwo();

            for (Star star2 : stars) {
                //single(star2);
                star2.Move();
            }

            try {
                fps = t;
                fps = 1000 / fps;
                long pause = 16 - t;
                if (pause < 0) pause = 0;
                if (pause > 16) pause =16;
                //System.out.println(pause);
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
            t = System.currentTimeMillis() - t1;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int width = getWidth();
        int height = getHeight();

        double ratio = (double) height / (Universe.size * 2);

        int x, y, r;

        totalFrame++;

        double carentTotalMass = 0;
        for (Star star : stars) {
            carentTotalMass = carentTotalMass + star.m;
        }

        for (Star star : stars) {
            x = (int) (ratio * star.current.x);
            y = (int) (ratio * star.current.y);
            r = (int) (ratio * Math.sqrt(star.m) * 0.5);
            if (star.m < 5001) {
                //g.drawOval((x + width / 2) - r / 2, (y + height / 2) - r / 2, r, r);
                g.fillOval((x + width / 2) - r / 2, (y + height / 2) - r / 2, r, r);
            }
            g.drawOval((x + width / 2) - r / 2, (y + height / 2) - r / 2, r, r);
        }

        g.drawString("Total stars:" + stars.size(), 1, 15);
        g.drawString("Change mass: " + String.valueOf((int) (totalMass - carentTotalMass)), 1, 30);
        g.drawString("Total frame:" + String.valueOf((int) totalFrame), 1, 45);
        g.drawString("FPS: " + String.valueOf((int) fps), 1, 60);
        g.drawString("Width: " + String.valueOf(width), 1, 75);

    }

    private void multiOne() {
        Iterator<Star> iStar = stars.iterator();
        iStar = stars.iterator();

        for (int i = 0; i < maxThreads; i++) {
            star = iStar.next();
            th[i] = new Thread(star);
            th[i].start();
        }

        while (iStar.hasNext()) {
            for (int k = 0; k < maxThreads; k++) {
                try {
                    th[k].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!iStar.hasNext()) {
                    break;
                }
                star = iStar.next();
                th[k] = new Thread(star);
                th[k].start();

            }
        }
    }

    private void multiTwo() {
        int band = stars.size() / maxThreads;

        for (int i = 0; i < maxThreads; i++) {
            Domen domen = new Domen(stars.subList(i * band, i * band + band));
            th[i] = new Thread(domen);
            th[i].start();
        }

        for (int i = 0; i < maxThreads; i++) {
            try {
                th[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Domen domen = new Domen(stars.subList(band * maxThreads, stars.size()));
        th[0] = new Thread(domen);
        th[0].start();
        try {
            th[0].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void single(Star star) {
        try {
            Thread th = new Thread(star);
            th.run();
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class Domen implements Runnable {
        private List<Star> stars;

        public Domen(List<Star> stars) {
            this.stars = stars;
        }

        public void run() {
            for (Star star : stars) {
                single(star);
            }
        }
    }
}
