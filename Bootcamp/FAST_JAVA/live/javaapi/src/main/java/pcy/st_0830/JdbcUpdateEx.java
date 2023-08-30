package pcy.st_0830;

import pcy.model.Book;
import pcy.model.BookDAO;
import pcy.model.BookRepository;

public class JdbcUpdateEx {

    public static void main(String[] args) {
        // 수정기능
        long id = 1L;
        String company = "";
        String name = "";
        int price = 43000;

        // 파라미터 수집
        // 묶어서(VO, DTO) -> DAO
        Book book = new Book(id, null, company, name, price);
        BookRepository bookRepository = new BookDAO();

        if (bookRepository.update(book) > 0) {
            System.out.println("수정 성공");
        }
    }
}
