package coffee.decorator;

import coffee.Coffee;

public abstract class Decorator implements Coffee {

    private final Coffee coffee;

    public Decorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public void brewing() {
        coffee.brewing();
    }
}
