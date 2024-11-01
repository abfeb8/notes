package design_patterns.decorator_pattern.with_pattern;

// Concrete Decorators
public class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double cost() {
        return super.cost() + 1.5;
    }

    @Override
    public String description() {
        return String.format("%s with milk", super.description());
    }

}
