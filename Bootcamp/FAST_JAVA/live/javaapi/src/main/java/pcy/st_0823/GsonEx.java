package pcy.st_0823;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pcy.model.Book;
import pcy.model.BookList;

import java.util.ArrayList;
import java.util.List;

public class GsonEx {

    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("이펙티브 자바 Effective Java", "인사이트(insight)", "Joshua Bloch", 36000));
        books.add(new Book("Real MySQL", "위키북스", "이성욱", 45000));

        BookList bookList = new BookList(books);

        // Gson API
        Gson gson = new Gson();
        String jsonBook = gson.toJson(bookList);
        System.out.println(jsonBook);

        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String prettyjsonBook = prettyGson.toJson(bookList);
        System.out.println(prettyjsonBook);
    }
}
