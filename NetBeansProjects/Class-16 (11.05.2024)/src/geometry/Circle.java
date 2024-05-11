package geometry;

/**
 *
 * @author Shabab-1281539
 */
public class Circle extends GeometricObject {

    private int radius;

    public Circle() {
    }

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

}
