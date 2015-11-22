/**
 * Created by Angor on 22.11.2015.
 * можно использовать как класический вектор, так и как просто указатель координат
 */
public class Vector {
    public double x;
    public double y;

    public Vector(){
        this.x = 0;
        this.y = 0;
    }

    public Vector (double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector (double x1, double y1, double x2, double y2){

    }

    // сложение с другим вектором
    public Vector add(Vector other){
        Vector newVector = new Vector();
        newVector.x = this.x + other.x;
        newVector.y = this.y + other.y;
        return newVector;
    }

    // растояние между двумя точками заданными векторами
    public double length (Vector other){
        return Math.sqrt(Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y),2));
    }
}
