package design_patterns.decorator_pattern.with_pattern;

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
