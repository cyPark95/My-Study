package pcy.st_0828;

import pcy.model.Book;
import pcy.model.BookDAO;

import java.util.List;

public class DaoSelectEx {

    public static void main(String[] args) {
        BookDAO dao = new BookDAO();
        List<Book> books = dao.findAll();
        if (books.isEmpty()) {
            System.out.println("데이터가 없습니다.");
            return;
        }

        books.forEach(System.out::println);
    }
}
