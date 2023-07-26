package kr.fc.model;

// Object Class(Root Class): 자동으로 상속
public class Book extends Object {
    // private => 정보은닉
    private String title;
    private int price;

    // 생략된 생성자: 기본(default) 생성자
    // 생성자의 이름은 클래스 이름과 동일해야 한다.
    // 반환(return) 타입이 없다.
    public Book() {
        // 자신의 모든 멤버들을 메모리에 로딩한다.
    }

    // 메서드 이름 중복 => 오버로딩(Overloading): 메서드 매개변수 순서/타입/개수 중 하나라도 다르다면 같은 이름의 메서드 지정 가능
    public Book(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // 객체가 가지고 있는 모든 값의 확인을 위한 메서드(디버깅)
    // Object.toString(): 인스턴스의 주소값을 출력하도록 정의
    // 재정의(Override): 부모의 클래스에 정의된 메서드의 구현을 변경하여 제공하는 기능
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}