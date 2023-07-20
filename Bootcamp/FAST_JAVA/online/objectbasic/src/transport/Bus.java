package transport;

public class Bus implements Transport {

    private static final int FARE = 1000;

    private int busNumber;
    private int passengerCount;
    private int money;

    public Bus(int busNumber) {
        this.busNumber = busNumber;
    }

    @Override
    public int getFare() {
        return FARE;
    }

    @Override
    public void take(int money) {
        this.money += money;
        passengerCount++;
    }

    @Override
    public void showInfo() {
        System.out.println(busNumber + "번 버스의 승객 수는 " + passengerCount + "명 이고, 수입은 " + money + "원 입니다.");
    }
}
