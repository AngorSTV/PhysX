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

    // сложение с другим вектором
    public void add(Vector other){
        this.x = this.x + other.x;
        this.y = this.y + other.y;
    }

    // вычитание текущего вектора из другого
    public Vector sub(Vector other){
        Vector result = new Vector();
        result.x = other.x - this.x;
        result.y = other.y - this.y;
        return result;
    }

    // растояние между двумя точками заданными векторами
    public double distance(Vector other){
        return Math.sqrt(Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y),2));
    }

    // длина вектора
    public double getLength (){
        return Math.sqrt(Math.pow((this.x), 2) + Math.pow((this.y),2));
    }

    // нормализация вектора
    public void normalize (){
        double length = this.getLength();
        this.x = this.x/length;
        this.y = this.y/length;
    }
}
