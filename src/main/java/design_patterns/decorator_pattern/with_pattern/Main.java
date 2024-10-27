package design_patterns.decorator_pattern.with_pattern;

public class Main {

    public static void main(String[] args) {

        // Example 1
        Coffee coffee = new PlainCoffee();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);

        System.out.printf("%s costs %s%n", coffee.description(), coffee.cost());

        // Example 2
        Coffee espresso = new Espresso();
        espresso = new SugarDecorator(espresso);

        System.out.printf("%s costs %s%n", espresso.description(), espresso.cost());
    }

}
