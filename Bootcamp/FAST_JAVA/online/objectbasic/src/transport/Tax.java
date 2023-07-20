package transport;

public class Tax implements Transport {

  private static final int FARE = 10000;

  private String company;
  private int money;

  public Tax(String company) {
    this.company = company;
  }

  @Override
  public int getFare() {
    return FARE;
  }

  @Override
  public void take(int money) {
    this.money += money;
  }

  @Override
  public void showInfo() {
    System.out.println(company + " 택시의 수입은 " + money + "입니다.");
  }
}
