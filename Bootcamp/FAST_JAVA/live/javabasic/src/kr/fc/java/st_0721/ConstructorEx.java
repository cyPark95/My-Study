package kr.fc.java.st_0721;

import kr.fc.model.Book;

public class ConstructorEx {

    public static void main(String[] args) {
        // Q. [책] 한권을 저장할 [변수를 선언]하세요.
        Book book;
        // 생성자: 객체를 메모리에 생성(인스턴스)
        // book 에는 객체 메모리의 주소를 저장
        book = new Book();  // 객체를 생성(인스턴스를 만드는 과정)

        // Q. book 에 [자바의 정석, 35000] 저장하세요.
        book.setTitle("자바의 정석");
        book.setPrice(35000);
    }
}
