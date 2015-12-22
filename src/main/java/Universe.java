/**
 * Created by Angor on 26.11.2015.
 */
public class Universe {
    public static final double G = 0.0007; //гравитационная постоянная с учётом масштаба симуляции
    public static final double SH = 0.05; // радиус сферы Шварцшильда
    public static final double C = 500; // скорость света
    public static final int size = 1000; // размер вселенной

    public static final int starsQuantity = 50;
    public static final int massBand = 100;

    //public static List<Star> stars = new LinkedList<>();

    public static double gravitation(double m2, double r) {
        return G * m2/Math.pow(r, 2);
        //return G * m2 / (r * r);
    }
}
