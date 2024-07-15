package design_patterns.decorator.with_pattern;

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
        return STR."\{super.description()} with milk";
    }

}
