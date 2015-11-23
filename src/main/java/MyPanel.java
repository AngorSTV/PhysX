import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Angor on 22.11.2015.
 */
public class MyPanel extends JPanel {

    public List<Star> stars = new ArrayList<>();

    public MyPanel(List<Star> stars) {
        this.stars = stars;
        setBackground(new Color(0));
        setForeground(new Color(255, 255, 255));
        //setBorder();
    }

    public void paint(Graphics g) {
        super.paint(g);
        // очистка
        int width = getWidth();
        int height = getHeight();

        int ratio = 1;

        int x, y;

        for (Star star : stars) {
            x = ratio * (int) star.carent.x;
            y = ratio * (int) star.carent.y;
            g.drawLine(x, y, x, y);
        }
    }
}
