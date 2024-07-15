package design_patterns.factory_pattern.with_pattern;

// Usage
public class Main {

    public static void main(String[] args) {

        // Create Circle
        Shape shape1 = ShapeFactory.createShape("CIRCLE");
        shape1.draw();

        // Create Square
        Shape shape2 = ShapeFactory.createShape("SQUARE");
        shape2.draw();
    }

}
