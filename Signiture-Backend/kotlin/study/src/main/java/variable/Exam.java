package variable;

public class Exam {

    public Exam() {
        String name = "홍길동";

        int age = 10;       // 기본값: 0
        Integer _age = 20;  // 기본값: null

        System.out.println(name + "(" + age + ")");
        String format = "사용자 이름은: %s";
        String result = String.format(format, name);
        System.out.println(result);
    }

    public static void main(String[] args) {
        new Exam();
    }
}
