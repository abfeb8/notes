package design_patterns.decorator.with_pattern;

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
        return STR."\{super.description()} with sugar";
    }

}
