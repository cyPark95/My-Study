package kr.fc.java.st_0726;

import kr.fc.model.Book;

public class ObjectArrayEx {

    public static void main(String[] args) {
        // Q. 책 3권을 저장할 배열을 생성하세요.
        // 객체배열
        Book[] books = new Book[3]; // 배열의 단점: 고정길이, 동일한 자료형
        books[0] = new Book("자바", 20000);
        books[1] = new Book("C언어", 32000);
        books[2] = new Book("오라클", 17000);

        for (Book book : books) {
            System.out.println(book);
        }
    }
}
