package kr.fc.java.st_0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodayCheck {

    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        // ArrayList에 영화(Movie) 객체 5편을 저장하고 영화제목을 기준으로 오름차순 정렬하여 출력하시오.
        List<Movie> list = new ArrayList<>();

        // 1. 영화 5편은 키보드로 부터 입력을 받는다.
        for (int i = 0; i < 5; i++) {
            String title = inputReader("제목");
            String name = inputReader("주인공");
            String company = inputReader("제작사");
            int price = Integer.parseInt(inputReader("가격"));
            String level = inputReader("등급");

            list.add(new Movie(title, name, company, price, level));
        }

        Collections.sort(list);

        // 제목: 범죄도시3
        // 주인공: 마동석
        // 제작사: CJ시네마
        // 가격: 18000
        // 등급: 15
        System.out.println("******* 영화 목록 *******");
        list.forEach(Movie::print);

        // 영화제목을 입력하여 영화를 검색하시오.
        // 2. 영화제목을 입력하면 해당 영화만 검색하여 출력한다.(이진검색)
        System.out.println("******* 영화 검색 *******");
        String searchTitle = inputReader("영화 제목");
        Movie movie = binarySearch(list, searchTitle);
        // 영화제목:범죄도시3
        // [출력결과]
        // 영화제목  | 주인공 | 제작사  |  가격   | 등급
        // 범죄도시3  마동석  CJ시네마  18000  15
        if (movie == null) {
            System.out.println("검색하신 영화가 없습니다.");
            return;
        }
        System.out.println("영화제목\t| 주인공\t| 제작사\t| 가격\t| 등급");
        System.out.println(
                movie.getTitle() + "\t " +
                movie.getName() + "\t" +
                movie.getCompany() + "\t " +
                movie.getPrice() + "\t " +
                movie.getLevel()
        );
    }

    private static Movie binarySearch(List<Movie> list, String title) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (list.get(mid).getTitle().compareTo(title) < 0) {
                left = mid + 1;
            } else if (list.get(mid).getTitle().compareTo(title) > 0) {
                right = mid - 1;
            } else {
                return list.get(mid);
            }
        }

        return null;
    }

    private static String inputReader(String message) {
        try {
            System.out.print(message + ": ");
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Movie implements Comparable<Movie> {

        private final String title;
        private final String name;
        private final String company;
        private final int price;
        private final String level;

        public Movie(String title, String name, String company, int price, String level) {
            this.title = title;
            this.name = name;
            this.company = company;
            this.price = price;
            this.level = level;
        }

        public void print() {
            System.out.println("영화");
            System.out.println("제목: " + title);
            System.out.println("주인공: " + name);
            System.out.println("제작사: " + company);
            System.out.println("가격: " + price);
            System.out.println("등급: " + level);
        }

        @Override
        public int compareTo(Movie o) {
            return title.compareTo(o.title);
        }

        public String getTitle() {
            return title;
        }

        public String getName() {
            return name;
        }

        public String getCompany() {
            return company;
        }

        public int getPrice() {
            return price;
        }

        public String getLevel() {
            return level;
        }
    }
}
