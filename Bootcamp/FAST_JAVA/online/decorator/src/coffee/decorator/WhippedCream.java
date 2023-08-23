package coffee.decorator;

import coffee.Coffee;

public class WhippedCream extends Decorator{

	public WhippedCream(Coffee coffee) {
		super(coffee);
	}

	public void brewing() {
		super.brewing();
		System.out.println("Adding WhippedCream ");
	}
}
