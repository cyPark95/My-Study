package project;

public class Main {

    public static void main(String[] args) {
        Student kim = new Student(1, "Kim");

        kim.addSubject("국어", 70);
        kim.addSubject("수학", 85);
        kim.addSubject("영어", 100);

        kim.showInfo();
    }
}
