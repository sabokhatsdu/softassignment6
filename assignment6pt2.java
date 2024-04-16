abstract class Pizza {
    protected double price;

    public double getPrice() {
        return price;
    }
}

class PlainPizza extends Pizza {
    public PlainPizza() {
        this.price = 6.0;
    }
}

abstract class PizzaDecorator extends Pizza {
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    public abstract double getPrice();
}

class PepperoniTopping extends PizzaDecorator {
    public PepperoniTopping(Pizza pizza) {
        super(pizza);
        this.price = 2.0;
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + this.price;
    }
}

class MushroomTopping extends PizzaDecorator {
    public MushroomTopping(Pizza pizza) {
        super(pizza);
        this.price = 1.5;
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + this.price;
    }
}

public class assignment6pt2 {
    public static void main(String[] args) {
        Pizza pizza = new PlainPizza();
        System.out.println("Base Pizza Price: $" + pizza.getPrice());

        pizza = new PepperoniTopping(pizza);
        System.out.println("Pizza with Pepperoni Topping Price: $" + pizza.getPrice());

        pizza = new MushroomTopping(pizza);
        System.out.println("Pizza with Mushroom Topping Price: $" + pizza.getPrice());
    }
}