package pcy.st_0828;

import pcy.model.Book;
import pcy.model.BookDAO;

public class DaoInsertEx {

    public static void main(String[] args) {
        // DAO를 사용해서 책 데이터를 DB에 저장하세요.
        BookDAO dao = new BookDAO();
        int count = dao.save(new Book("Java의 정석", "도우 출판사", "남궁성", 30000));

        if (count > 0) {
            System.out.println("저장 성공");
        } else {
            System.out.println("저장 실패");
        }
    }
}
