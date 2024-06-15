package solid.open_close.incomplient;

/**
 * AreaCalculator class can only calculate area for Rectangle
 */
public class AreaCalculator {

    public double calculateRectangleArea(Rectangle rectangle){
        return rectangle.width * rectangle.height;
    }

}
