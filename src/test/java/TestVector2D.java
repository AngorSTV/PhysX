import org.junit.Test;

/**
 * Created by Андрей on 24.12.2015.
 */
public class TestVector2D {
    @Test
    public void testOrto(){
    Vector2D v = new Vector2D(300,400);
        v.normalize();
        v.mult(0.01);
        System.out.println("x=" + v.x);
        System.out.println("y=" + v.y);
    }
}
