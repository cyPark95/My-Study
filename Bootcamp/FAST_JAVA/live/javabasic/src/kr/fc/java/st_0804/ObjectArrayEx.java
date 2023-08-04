package kr.fc.java.st_0804;

import kr.fc.poly.Animal;
import kr.fc.poly.Cat;
import kr.fc.poly.Dog;

public class ObjectArrayEx {

    public static void main(String[] args) {
        // Dog, Cat -> Object[] : Object -> DownCasting
        Object[] objects = new Object[2];
        objects[0] = new Dog(); // UpCasting, eat()
        objects[1] = new Cat(); // UpCasting, eat(), night()

        for (Object object : objects) {
            if (object instanceof Animal animal) {
                animal.eat();
                if (animal instanceof Cat cat) {
                    cat.night();
                }
            }
        }

        Dog dog = new Dog();
        Cat cat = new Cat();
        display(dog);
        display(cat);
    }

    private static void display(Object object) {
        if (object instanceof Animal animal) {
            animal.eat();
            if (animal instanceof Cat cat) {
                cat.night();
            }
        }
    }
}
