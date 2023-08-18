package kr.fc.java.st_0818;

import kr.fc.model.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileReaderEx {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("myBookList.txt"))) {
            List<Book> list = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] split = line.split(",");
                list.add(new Book(split[0], Integer.parseInt(split[1])));
            }

            bookPrint(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void bookPrint(List<Book> list) {
        list.forEach(System.out::println);
    }
}
