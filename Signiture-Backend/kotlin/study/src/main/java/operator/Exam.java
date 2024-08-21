package operator;

public class Exam {

    public Exam() {
        var name = "홍길동";   // 타입추론

        Integer num = 10;
        callMethod(num);

        // NullPointerException
        Integer nullNum = null;
        callMethod(nullNum);

        callMethod(null);
    }

    private void callMethod(Integer i) {
        if (i == null) {
            System.out.println("null  값 입니다.");
        } else {
            System.out.println(i);
        }

        var temp = (i == null) ? "null 값 입니다." : i;
        System.out.println(temp);
    }

    public static void main(String[] args) {
        new Exam();
    }
}
