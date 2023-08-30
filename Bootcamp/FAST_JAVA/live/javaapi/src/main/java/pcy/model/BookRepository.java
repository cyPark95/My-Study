package pcy.model;

import java.util.List;

// Spring Data JPA -> CrudRepository, JPARepository
//                    delete(), save(): insert, update, fandAll() ...
public interface BookRepository {

    // CRUD method 정의
    int save(Book book);

    List<Book> findAll();

    Book findById(Long id);

    List<Book> findByTitleLike(String title);

    int update(Book book);

    int delete(long id);
}
