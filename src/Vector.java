/**
 * Created by Angor on 22.11.2015.
 */
public class Vector {
    public double x;
    public double y;

    public Vector(){
        this.x = 0;
        this.y = 0;
    }

    public Vector (double x1, double y1, double x2, double y2){

    }

    public Vector add(Vector other){
        Vector newVector = new Vector();
        newVector.x = this.x + other.x;
        newVector.y = this.y + other.y;
        return newVector;
    }
}
