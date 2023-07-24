package kr.fc.java.st_0724;

import kr.fc.model.Book;

public class InstanceEx {

    public static void main(String[] args) {
        // 데이터: 기본자료 -> 객체자료
        // Q. 책 1권을 저장할 [변수를 선언]하세요.
        Book book;  // book(객체)
        // Q. 책 한권의 [객체를 생성]하세요.
        book = new Book("자바", 35000);   // 인스턴스 변수
        System.out.println("title: " + book.getTitle() + " / price: " + book.getPrice());

        // 클래스(Class): 객체를 설게하는 도구
        // 객체(Object): 선언된 객체 변수
        // 인스턴스(Instance): 생성되고 값이 있는 객체
    }
}
