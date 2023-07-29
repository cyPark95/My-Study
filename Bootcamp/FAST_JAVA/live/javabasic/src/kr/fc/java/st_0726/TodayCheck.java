package kr.fc.java.st_0726;

import java.util.Arrays;
import java.util.Comparator;

public class TodayCheck {

    public static void main(String[] args) {
        Movie[] movies = Question01();
        Question02(movies);
        Question03(movies);
        Question04(movies);
    }

    private static Movie[] Question01() {
        // 1. 영화(Movie)객체를 모델링 하고, 영화 3편의 객체를 생성하고 출력하는 프로그램을 작성하시오.
        System.out.println("========== Save Object");
        Movie[] movies = new Movie[3];
        movies[0] = new Movie("인셉션", "레오나르도 디카프리오", "Syncopy Films Inc", 12000, "12세");
        movies[1] = new Movie("타이타닉", "레오나르도 디카프리오", "20th Century Studios", 8000, "15세");
        movies[2] = new Movie("식스센스", "브루스 윌리스", "Hollywood Pictures", 14000, "12세");
        print(movies);
        return movies;
    }

    private static void Question02(Movie[] movies) {
        // 2. 1번에서 생성한 데이터를 활용하여 영화제목을 기준으로 오름차순 정렬하여 출력하는 프로그램을 작성하시오.
        System.out.println("========== Sorting Title ASC");
        Arrays.sort(movies, Comparator.comparing(Movie::title));
        print(movies);
    }

    private static void Question03(Movie[] movies) {
        // 3. 1번에서 생성한 데이터를 활용하여 등급을 기준으로 내림차순 정렬하여 출력하는 프로그램을 작성하시오.
        System.out.println("========== Sorting Grade DESC");
        Arrays.sort(movies, (m1, m2) -> m2.level().compareTo(m1.level()));
        print(movies);
    }

    private static void Question04(Movie[] movies) {
        // 4. 1번에서 생성한 데이터를 활용하여 가격이 가장 높은 영화정보를 출력하는 프로그램을 작성하시오.
        System.out.println("========== Select Maximum Price Movie");
        int max = Arrays.stream(movies)
                .mapToInt(Movie::price)
                .max()
                .orElse(0);
        Arrays.stream(movies)
                .filter(movie -> max == movie.price())
                .findFirst()
                .ifPresent(System.out::println);
    }

    private static void print(Movie[] movies) {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    private record Movie(
            String title,
            String name,
            String company,
            int price,
            String level
    ) {
        @Override
        public String toString() {
            return "Movie{" +
                    "title='" + title + '\'' +
                    ", name='" + name + '\'' +
                    ", company='" + company + '\'' +
                    ", price=" + price +
                    ", level='" + level + '\'' +
                    '}';
        }
    }
}
