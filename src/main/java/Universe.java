import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angor on 26.11.2015.
 */
public class Universe {
    public static final double G = 0.0009; //гравитационная постоянная с учётом масштаба симуляции
    public static final double SH = 0.03; // радиус сферы Шварцшильда
    public static final double C = 500; // скорость света
    public static final int size = 1000; // размер вселенной

    public static List<Star> stars = new ArrayList<>();

    public static double gravitation(double m1, double m2, double r) {
        return G * m2/Math.pow(r, 1.75);
        //return G * m2 / (r * r);
    }
}
