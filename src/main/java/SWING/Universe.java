package SWING;

/**
 * Created by Angor on 26.11.2015.
 */
public class Universe {
    public static final double G = 0.0007; //гравитационная постоянная с учётом масштаба симуляции
    public static final double SH = 0.03; // радиус сферы Шварцшильда
    public static final double C = 100000; // скорость света
    public static final int size = 10000; // размер вселенной

    public static final int starsQuantity = 1000;
    public static final int massBand = 100;

    //public static List<SWING.Star> stars = new LinkedList<>();

    public static double gravitation(double m2, double r) {
        return G * m2/Math.pow(r, 1);
    }
}