package transport;

public class Subway implements Transport {

  private static final int FARE = 1200;

  private int lineNumber;
  private int passengerCount;
  private int money;

  public Subway(int lineNumber) {
    this.lineNumber = lineNumber;
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
    System.out.println(lineNumber + "번 지하철의 승객 수는 " + passengerCount + "명 이고, 수입은 " + money + "원 입니다.");
  }
}
