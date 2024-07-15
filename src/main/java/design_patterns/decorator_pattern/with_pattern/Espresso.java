package design_patterns.decorator_pattern.with_pattern;

public class Espresso implements Coffee {

    @Override
    public double cost() {
        return 10.0;
    }

    @Override
    public String description() {
        return "Espresso";
    }
}
