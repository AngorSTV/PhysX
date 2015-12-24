/**
 * Created by Angor on 22.11.2015.
 * можно использовать как класический вектор, так и как просто указатель координат
 */
public class Vector2D {
    public double x; // r - в полярной системе
    public double y; // t - угол

    public Vector2D(){
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    // сложение с другим вектором
    public void add(Vector2D other){
        this.x = this.x + other.x;
        this.y = this.y + other.y;
    }

    // вычитание текущего вектора из другого
    public Vector2D sub(Vector2D other){
        Vector2D result = new Vector2D();
        result.x = other.x - this.x;
        result.y = other.y - this.y;
        return result;
    }

    // растояние между двумя точками заданными векторами
    public double distance(Vector2D other){
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

    // умножение вектора на число
    public void mult (double factor){
        this.x = this.x*factor;
        this.y = this.y*factor;
    }

    // конвертация радиального вектора в декартовский
    public void polarToDecart (){
        double r = x;
        x = r * Math.cos(y);
        y = r * Math.sin(y);
    }
    // поворот вектора на 90 градусов
    // true - почасовой стрелке
    // false - против часовой
    public void ortogonale (boolean direct) {
        double newX = x;
        if (direct){
            x = y;
            y = -newX;
        }else{
            x = -y;
            y = newX;
        }
    }
}
