import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angor on 26.11.2015.
 */
public class Universe {
    public static final double G = 0.1; //гравитационная постоянная с учётом масштаба симуляции
    public static final double SH = 0.25; // радиус сферы Шварцшильда
    public static final double C = 50; // скорость света
    public static final int size = 5000; // размер вселенной

    public static List<Star> stars = new ArrayList<>();

    public static double gravitation (double m1, double m2, double r){
        return G *(m1 * m2)/Math.pow(r, 2);
    }
}
