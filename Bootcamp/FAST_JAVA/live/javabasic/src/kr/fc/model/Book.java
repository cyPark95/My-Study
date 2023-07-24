package kr.fc.model;

public class Book {
    // private => 정보은닉
    private String title;
    private int price;

    // 생략된 생성자: 기본(default) 생성자
    // 생성자의 이름은 클래스 이름과 동일해야 한다.
    // 반환(return) 타입이 없다.
    public Book() {
        // 자신의 모든 멤버들을 메모리에 로딩한다.
    }

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
}