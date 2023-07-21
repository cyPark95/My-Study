package kr.fc.java.st_0721;

import kr.fc.model.Book;

public class MethodEx {

    public static void main(String[] args) {
        String title = "자바의 정성";
        int price = 35000;
//        print(title, price);

        // Book 을 통해 데이터 묶어주기(new 생성자())
        // 객체 생성
        Book book = new Book(title, price);
        print(book);
    }

    // 메서드(동작, 방법, 기능) 정의: 메서드 이름이 변수의 역할 => 메서드 이름 앞에는 자료형이 와야한다.
    public static int add(int n1, int n2) {
        return n1 + n2;
    }

    // 파라미터가 많이자면 불편하다. => print(title, price, ...)
//    public static void print(String title, int price) {
//        System.out.println("title = " + title + " / price = " + price);
//    }

    private static void print(Book book) {
        System.out.println("title = " + book.getTitle() + " / price = " + book.getPrice());
    }
}
