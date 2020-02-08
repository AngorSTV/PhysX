package SWING;

/**
 * Created by Angor on 26.11.2015.
 */
public class Universe {
    public static final double G = 0.0007; //гравитационная постоянная с учётом масштаба симуляции
    public static final double SH = 0.03; // радиус сферы Шварцшильда
    public static final double C = 1000000; // скорость света
    public static final int size =3000; // размер вселенной

    public static final int starsQuantity = 5000;
    public static final int massBand = 100;

    //public static List<SWING.Star> stars = new LinkedList<>();

    public static double gravitation(double m, double r) {
        return G * m/r;
    }
}
