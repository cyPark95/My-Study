package function;

import java.util.List;
import java.util.function.Predicate;

public class Exam {

    public Exam() {
        var list = List.of(
                "1",
                "2",
                "홍길동",
                "함수",
                "메서드"
        );

        var stringPredicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.equals("?");
            }
        };

        List<String> result = list.stream()
                .filter(it -> it.equals("?"))
                .toList();
    }

    public static void main(String[] args) {
        new Exam();
    }
}
