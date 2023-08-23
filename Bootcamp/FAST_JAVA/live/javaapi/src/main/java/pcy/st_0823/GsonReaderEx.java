package pcy.st_0823;

import com.google.gson.Gson;
import pcy.model.Book;
import pcy.model.BookList;

import java.io.FileReader;
import java.util.List;

public class GsonReaderEx {

    public static void main(String[] args) {
        try (FileReader reader = new FileReader("book.json")) {
            Gson gson = new Gson();
            BookList bookList = gson.fromJson(reader, BookList.class);

            List<Book> books = bookList.books();
            books.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
