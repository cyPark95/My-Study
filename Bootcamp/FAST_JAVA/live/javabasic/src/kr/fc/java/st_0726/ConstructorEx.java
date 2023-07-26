package kr.fc.java.st_0726;

import kr.fc.model.Book;

public class ConstructorEx {

    public static void main(String[] args) {
        Book book = new Book();
        book.setTitle("자바");
        book.setPrice(34000);
        System.out.println("book = " + book);

        // 생성자: 초기화 => 매개변수로 받아서 멤버 필드에 주입(DI)
        Book initBook = new Book("자바", 34000);
        System.out.println("initBook = " + initBook);
    }
}
