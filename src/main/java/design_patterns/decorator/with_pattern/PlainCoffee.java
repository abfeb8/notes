package design_patterns.decorator.with_pattern;

// Concrete Component
public class PlainCoffee implements Coffee {

    @Override
    public double cost() {
        return 5.0;
    }

    @Override
    public String description() {
        return "Coffee";
    }
}
