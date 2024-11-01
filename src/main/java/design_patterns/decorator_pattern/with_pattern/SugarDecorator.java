package design_patterns.decorator_pattern.with_pattern;

// Concrete Decorator
public class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double cost() {
        return super.cost() + 0.5;
    }

    @Override
    public String description() {
        return String.format("%s with sugar", super.description());
    }

}
