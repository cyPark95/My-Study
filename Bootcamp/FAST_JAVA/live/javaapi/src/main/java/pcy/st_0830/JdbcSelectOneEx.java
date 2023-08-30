package pcy.st_0830;

import pcy.model.Book;
import pcy.model.BookDAO;
import pcy.model.BookRepository;

public class JdbcSelectOneEx {

    public static void main(String[] args) {
        // 특정 레코드 1개만 검색
        long id = 1L;
        BookRepository bookRepository = new BookDAO();
        Book book = bookRepository.findById(id);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("검색 실패");
        }
    }
}
