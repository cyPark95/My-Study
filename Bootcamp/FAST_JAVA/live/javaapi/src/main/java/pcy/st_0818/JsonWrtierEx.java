package pcy.st_0818;

import org.json.JSONArray;
import org.json.JSONObject;
import pcy.model.Book;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonWrtierEx {

    /*
     객체(Object)를 [문자열 형태 = text]로 표현하는 방법
      = 책: VO, DTO  - Class
      = 책: text, json, xml
      xml: <태그>를 통해 데이터 구분(시멘틱) -> 용량이 크다
      json: 용량도 적고 시멘틱도 -> 디테일한 자료표현은 불가능하다.
     */
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("책에 대한 정보를 입력해 주세요.(나가려면: exit)");
        while (true) {
            System.out.print("제목: ");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            String title = input;

            System.out.print("출판사: ");
            input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            String company = input;

            System.out.print("저자: ");
            input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            String name = input;

            System.out.print("가격: ");
            input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            int price = Integer.parseInt(input);

            // 묶고(VO) -> 담다.(List)
            books.add(new Book(title, company, name, price));
        }
        scanner.close();

        // List <- JSONArray <- JSONObject({ ... })
        // JSON API
        JSONArray jsonBooks = new JSONArray();
        for (Book book : books) {
            JSONObject jsonBook = new JSONObject();
            jsonBook.put("title", book.getTitle());
            jsonBook.put("company", book.getCompany());
            jsonBook.put("name", book.getName());
            jsonBook.put("price", book.getPrice());
            jsonBooks.put(jsonBook);
        }

        JSONObject root = new JSONObject();
        root.put("books", jsonBooks);

        try (FileWriter fw = new FileWriter(("books.json"))) {
            fw.write(root.toString());
            System.out.println("books.json 파일 생성 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
