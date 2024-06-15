package solid.open_close.complient;

/**
 * AreaCalculator class with method calculateArea(Shape) can
 * calculate area for any class implementing Shape interface
 */
public class AreaCalculator {

    public double calculateArea(Shape shape){
        return shape.calculateArea();
    }

}
