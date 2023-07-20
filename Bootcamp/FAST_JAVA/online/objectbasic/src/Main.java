import transport.Bus;
import transport.Subway;
import transport.Tax;
import transport.Transport;

public class Main {

    public static void main(String[] args) {
        // 인스턴스 생성
        Student james = new Student("James", 5000);
        Student tomas = new Student("Tomas", 10000);
        Student edward = new Student("Edward", 20000);

        Transport bus = new Bus(100);
        Transport subway = new Subway(2);
        Transport tax = new Tax("잘나간다 운수");

        // 교통 이용
        james.take(bus);
        james.take(subway);

        tomas.take(subway);

        edward.take(tax);

        // 정보 출력
        james.showInfo();
        tomas.showInfo();
        edward.showInfo();

        bus.showInfo();
        subway.showInfo();
        tax.showInfo();
    }
}
