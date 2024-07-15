package design_patterns.factory_pattern.with_pattern;

// Factory
public class ShapeFactory {

    public static Shape createShape(String shapeType) {

        return switch (shapeType) {
            case "CIRCLE" -> new Circle();
            case "SQUARE" -> new Square();
            default -> null;
        };

    }
}
