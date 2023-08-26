package pcy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

// Lombok API
@Getter
@AllArgsConstructor
@ToString
public class Book {

    private Long id;
    private String title;
    private String company;
    private String name;
    private int price;

    public Book(String title, String company, String name, int price) {
        this.title = title;
        this.company = company;
        this.name = name;
        this.price = price;
    }
}
