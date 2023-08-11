package kr.fc.java.st_0811;

import kr.fc.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScannerEx {

    public static void main(String[] args) {
        // 키보드로 부터 데이터를 입력받는 방법: I/O = Scanner API
        // 키보드(외부 장치): System.in / 모니터(콘솔): System.out
        Scanner scanner = new Scanner(System.in);
        List<Book> books = new ArrayList<>();

        while (true) {
            System.out.println("종료(exit): ");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            }

            System.out.println("책 제목: ");
            String title = scanner.nextLine();  // 블럭킹(blocking)

            System.out.println("책 가격: ");
            int price = Integer.parseInt(scanner.nextLine());  // "30000" -- Integer.parseInt("30000") --> 30000

            // 객체(Object): title, price 를 하나의 구조로 묶어주는 것
            // 묶고(객체) -> 담고(Collection)
            Book book = new Book(title, price);
            books.add(book);
        }

        bookPrint(books);
    }

    private static void bookPrint(List<Book> books) {
        books.forEach(System.out::println);
    }
}
