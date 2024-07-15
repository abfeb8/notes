package design_patterns.decorator.without_pattern;

public class Main {
    public static void main(String[] args) {
        Coffee coffee = new SugarMilkCoffee();
        System.out.println(STR."\{coffee.description()} costs \{coffee.cost()}");
    }
}

class Coffee {
    public double cost() {
        return 5.0;
    }

    public String description() {
        return "Coffee";
    }
}

class MilkCoffee extends Coffee {
    @Override
    public double cost() {
        return super.cost() + 1.5;
    }

    @Override
    public String description() {
        return STR."\{super.description()} with milk";
    }
}

class SugarMilkCoffee extends MilkCoffee {
    @Override
    public double cost() {
        return super.cost() + 0.5;
    }

    @Override
    public String description() {
        return STR."\{super.description()} with sugar";
    }
}