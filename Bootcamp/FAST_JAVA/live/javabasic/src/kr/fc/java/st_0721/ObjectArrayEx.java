package kr.fc.java.st_0721;

import kr.fc.model.Book;

public class ObjectArrayEx {

    public static void main(String[] args) {
        // Q. [책] [3권]을 저장할 [변수를 선언] 하세요.
        Book[] books;

        // Q. 책 3권을 저장할 [객체배열을 생성] 하세요.
        books = new Book[3];

        // Q. 책 3권을 생성해서 배열에 할당하세요.
        books[0] = new Book("자바", 15000);
        books[1] = new Book("코틀린", 20000);
        books[2] = new Book("파이썬", 12000);

        // 책 3권을 출력하세요.
        print(books);
    }

    private static void print(Book[] books) {
        for (Book book : books) {
            System.out.println("책 이름: " + book.getTitle() + " / 가격: " + book.getPrice());
        }
    }
}
