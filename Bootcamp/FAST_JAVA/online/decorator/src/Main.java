import coffee.*;
import coffee.decorator.Latte;
import coffee.decorator.Mocha;
import coffee.decorator.WhippedCream;

public class Main {

    public static void main(String[] args) {
        System.out.println("*** KenyaAmericano = kenyaAmericano ***");
        Coffee kenyaAmericano = new KenyaAmericano();
        kenyaAmericano.brewing();

        System.out.println("*** KenyaLatte = kenyaAmericano + Milk ***");
        Coffee kenyaLatte = new Latte(kenyaAmericano);
        kenyaLatte.brewing();

        System.out.println("*** kenyaMochaLatte = kenyaAmericano + Milk + Mochasyrup ***");
        Coffee kenyaMochaLatte = new Mocha(kenyaLatte);
        kenyaMochaLatte.brewing();

        System.out.println("*** EtiopiaWhippedMochaLatte = EtiopiaAmericano + Milk + Mochasyrup + WhippedCream ***");
        Coffee etiopiaWhippedMochaLatte =
                new WhippedCream(new Mocha(new Latte( new EtiopiaAmericano())));
        etiopiaWhippedMochaLatte.brewing();
    }
}
