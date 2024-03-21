package pcy.study.sns.util;

import org.springframework.data.domain.Sort;

import java.util.List;

public class PageHelper {

    public static String orderBy(Sort sort) {
        if (sort.isEmpty()) {
            return "id DESC";
        }

        List<String> orders = sort.toList().stream()
                .map(order -> order.getProperty() + " " + order.getDirection())
                .toList();

        return String.join(", ", orders);
    }
}
