package pcy.st_0823;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlingEx {

    public static void main(String[] args) {
        // 특정 서버에 있는 자원(리소스)을 네트워킹을 이용해서 획득하기 - 크롤링
        String url = "https://www.billboard.com/charts/hot-100";

        // 요청(Request) --> 응답(Response): HTML(tag) API
        // GET, POST ...
        // Jsoup API(Connection + Parsing)
        try {
            Document document = Jsoup.connect(url).get();
            Element element = document.selectFirst("a.c-title__link");
            if (element != null) {
                System.out.println("#1 Song On The Billboard Charts [" + element.text() + "]");
            }

            Element billboardList = document.selectFirst("div.chart-results-list");
            if (billboardList != null) {
                Elements songs = billboardList.select("div.o-chart-results-list-row-container");
                for (int i = 1; i < 5; i++) {
                    Element title = songs.get(i).getElementById("title-of-a-story");
                    System.out.println("#" + (i + 1) + " " + title.text());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
