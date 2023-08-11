package kr.fc.java.st_0811;

import kr.fc.model.Book;

import java.util.ArrayList;
import java.util.List;

public class GenericListEx {

    public static void main(String[] args) {
        // Q. ArrayList 를 이용해서 책 여러권을 저장하고 출력하세요.
        // Book[?] - 배열의 크기가 작으면 부족할 수 있고, 배열의 크기가 크면 낭비될 수 있다.
        List<Book> list = new ArrayList<>();
        list.add(new Book("자바", 25000));   // 0
        list.add(new Book("C", 30000));     // 1
        list.add(new Book("코틀린", 21000)); // 2
//        list.add(new Dog());    // 불가능

        list.remove(1);
        list.add(1, new Book("자바 스크립트", 17000));

        for (Book book : list) {
            System.out.println(book);
        }
    }
}

// 파일, 네트워킹 -> 입출력 하는 방법: 스트림(Stream) -> 크롤링(Crawling), Open API