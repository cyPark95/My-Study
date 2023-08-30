package pcy.st_0830;

import pcy.model.Book;
import pcy.model.BookDAO;
import pcy.model.BookRepository;

import java.util.List;

public class JdbcSelectLikeEx {

    public static void main(String[] args) {
        // 책 제목에 "자바"가 포함된 모든 책 검색
        String search = "자바";
        BookRepository repository = new BookDAO();
        List<Book> list = repository.findByTitleLike(search);
        if (list.isEmpty()) {
            System.out.println("검색 실패");
        }

        list.forEach(System.out::println);
    }
}
