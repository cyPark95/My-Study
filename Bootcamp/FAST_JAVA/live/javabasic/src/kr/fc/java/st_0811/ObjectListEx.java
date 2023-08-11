package kr.fc.java.st_0811;

import kr.fc.model.Book;
import kr.fc.poly.*;

import java.util.ArrayList;
import java.util.List;

public class ObjectListEx {

    public static void main(String[] args) {
        // 1. 내가 직접 만든 API = MyGenericArray
        // 2. Java 에서 제공해주는 API(Package) = ArrayList
        // 3. 외부 API = ?
        // 크기에 제한이 없다.
        List list = new ArrayList();  // Object[]
        list.add(new Tiger());  // 0
        list.add(new Dog());    // 자동으로 늘어난다.
        list.add(new Cat());    // UpCasting
        list.add(new Sparrow("참새"));
        list.add(new Book("자바", 25000));

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Animal animal) {
                animal.eat();

                // DownCasting
                if (animal instanceof Pet pet) {
                    pet.play();
                }

                if (animal instanceof Bird bird) {
                    bird.fly();
                    System.out.println(bird.getName());
                    if (bird instanceof Sparrow sparrow) {
                        sparrow.sound();
                    }
                }
            }
        }
    }
}
