package pcy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

// Lombok API
@Getter
@AllArgsConstructor
@ToString
public class Book {

    private String title;
    private String company;
    private String name;
    private int price;
}
