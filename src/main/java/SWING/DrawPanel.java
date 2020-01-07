package SWING;

import common.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Angor on 23.11.2015.
 */
public class DrawPanel extends JPanel implements Runnable {

    private long t;
    private double fps;
    private double zoom = 1;
    private double ratio;
    private double totalMass = 0;
    private static double totalFrame = 0;
    private List<Star> stars;
    private List<Star> newStars = new ArrayList<>();

    public DrawPanel(List<Star> stars) {
        super();
        this.stars = stars;
        // подсчёт общей массы
        for (Star star : stars) {
            totalMass = totalMass + star.m;
        }

        setBackground(new Color(0));
        setForeground(new Color(255, 255, 255));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Random rnd = new Random();
                Vector2D vector = new Vector2D((e.getX() - getWidth() / 2) / ratio, (e.getY() - getHeight() / 2) / ratio);
                Star star = new Star(vector, stars);
                star.m = rnd.nextDouble() * Universe.massBand + 1;
                totalMass = totalMass + star.m;
                newStars.add(star);
            }
        });
        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                zoom = zoom - (double) e.getWheelRotation() / 2;
            }
        });
        new Thread(this).start();
    }

    @Override
    public void run() {
        long t1;

        while (true) {

            t1 = System.currentTimeMillis();

            //добавление новых звёзд
            stars.addAll(newStars);
            newStars.clear();

            //расчёт сил гравитации на каждую звезду
            stars.parallelStream().unordered().forEach(star -> star.run());

            //Чистка масива от мёртвых объектов
            Iterator<Star> iStar = stars.iterator();
            while (iStar.hasNext()) {
                if (!iStar.next().isAlive) iStar.remove();
            }

            //перемещение звёзд
            stars.parallelStream().unordered().forEach(star -> star.move());

            try {
                fps = t;
                fps = 1000 / fps;
                long pause = 16 - t;
                if (pause < 0) pause = 0;
                if (pause > 16) pause = 16;
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

        ratio = (double) height / (Universe.size * 2);
        ratio = ratio + zoom / 20;

        //int x, y, r;

        totalFrame++;

        double carentTotalMass = 0;
        for (Star star : stars) {
            carentTotalMass = carentTotalMass + star.m;
        }

        stars.forEach(star -> {
            int x, y, r;
            x = (int) (ratio * star.current.x);
            y = (int) (ratio * star.current.y);
            r = (int) (ratio * Math.sqrt(star.m) * 0.5);
            if (star.m < 5001) {
                g.fillOval((x + width / 2) - r / 2, (y + height / 2) - r / 2, r, r);
            }
            g.drawOval((x + width / 2) - r / 2, (y + height / 2) - r / 2, r, r);
        });

        g.drawString("Total stars:" + stars.size(), 1, 15);
        g.drawString("Change mass: " + String.valueOf((int) (carentTotalMass - totalMass)), 1, 30);
        g.drawString("Total frame:" + String.valueOf((int) totalFrame), 1, 45);
        g.drawString("FPS: " + String.valueOf((int) fps), 1, 60);
        g.drawString("Ratio: " + String.valueOf(ratio), 1, 75);
        g.drawString("Time: " + String.valueOf(t), 1, 90);

    }
}
