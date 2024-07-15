package design_patterns.decorator.with_pattern;

public class Main {

    public static void main(String[] args) {

        // Example 1
        Coffee coffee = new PlainCoffee();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);

        System.out.println(STR."\{coffee.description()} costs \{coffee.cost()}");

        // Example 2
        Coffee espresso = new Espresso();
        espresso = new SugarDecorator(espresso);

        System.out.println(STR."\{espresso.description()} costs \{espresso.cost()}");
    }

}
