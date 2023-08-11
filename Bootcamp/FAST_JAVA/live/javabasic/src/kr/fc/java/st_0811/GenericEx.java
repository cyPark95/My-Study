package kr.fc.java.st_0811;

import kr.fc.api.MyGenericArray;
import kr.fc.poly.*;

public class GenericEx {

    public static void main(String[] args) {
        // Dog, Cat -> [] X
        // Animal[]
        MyGenericArray<Animal> genericArray = new MyGenericArray<>(4);  // 초기 사이즈(4)
        genericArray.add(new Tiger());  // UpCasting
        genericArray.add(new Dog());
        genericArray.add(new Cat());
        genericArray.add(new Sparrow("참새"));
//        genericArray.add(new Tiger());  // new ArrayIndexOutOfBoundsException("Index 4 out of bounds for length 4")

        for (int i = 0; i < genericArray.size(); i++) {
            Animal animal = genericArray.get(i);
            animal.eat();

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
