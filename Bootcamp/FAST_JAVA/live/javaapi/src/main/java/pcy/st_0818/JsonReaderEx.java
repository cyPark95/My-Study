package pcy.st_0818;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;

public class JsonReaderEx {

    public static void main(String[] args) {
        try (FileReader reader = new FileReader("book.json")) {

            StringBuilder jsonString = new StringBuilder();
            int character;
            while ((character = reader.read()) != -1) {
                jsonString.append((char) character);
            }

            JSONObject root = new JSONObject(jsonString.toString());
            JSONArray jsonBooks = root.getJSONArray("books");

            for (int i = 0; i < jsonBooks.length(); i++) {
                JSONObject jsonBook = jsonBooks.getJSONObject(i);
                String title = jsonBook.getString("title");
                String company = jsonBook.getString("company");
                String name = jsonBook.getString("name");
                int price = jsonBook.getInt("price");

                System.out.println("Title: " + title);
                System.out.println("Company: " + company);
                System.out.println("Name: " + name);
                System.out.println("Price: " + price);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
