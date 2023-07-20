import transport.Transport;

public class Student {

    private final String studentName;
    private int money;

    public Student(String studentName, int money) {
        this.studentName = studentName;
        this.money = money;
    }

    public void take(Transport transport) {
        int fare = transport.getFare();
        if (pay(fare)) {
            transport.take(fare);
        }
    }

    public void showInfo() {
        System.out.println(studentName + "님의 남은 돈은 " + money + "원 입니다.");
    }

    private boolean pay(int money) {
        if (this.money < money) {
            System.out.println("잔액이 부족합니다.");
            return false;
        }

        this.money -= money;
        return true;
    }
}
