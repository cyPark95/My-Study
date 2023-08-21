import customer.Customer;
import customer.TravelCustomer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        customers.add(new TravelCustomer("Mike", 35, 140));
        customers.add(new TravelCustomer("John", 31, 200));
        customers.add(new TravelCustomer("David", 24, 98));

        System.out.println("== 고객 명단 추가된 순서대로 출력 ==");
        customers.forEach(customer -> System.out.println(customer.name()));

        int total = customers.stream()
                .mapToInt(Customer::price)
                .sum();
        System.out.println("총 여행 비용은: " + total + "$ 입니다.");

        System.out.println("== 30세 이상 고객 명단 정렬하여 출력 ==");
        customers.stream()
                .filter(customer -> customer.age() >= 30)
                .sorted(Comparator.comparing(Customer::name))
                .forEach(customer -> System.out.println(customer.name()));
    }
}
