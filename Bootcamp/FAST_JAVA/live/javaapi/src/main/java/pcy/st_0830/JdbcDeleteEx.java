package pcy.st_0830;

import pcy.model.BookDAO;
import pcy.model.BookRepository;

public class JdbcDeleteEx {

    public static void main(String[] args) {
        BookRepository repository = new BookDAO();
        if (repository.delete(1L) > 0) {
            System.out.println("삭제 성공");
        }
    }
}
