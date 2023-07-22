package project;

import java.util.ArrayList;

public class Student {

    private int id;
    private String name;
    private ArrayList<Subject> subjects;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        subjects = new ArrayList<>();
    }

    public void addSubject(String name, int score) {
        subjects.add(new Subject(name, score));
    }

    public void showInfo() {
        int totalScore = subjects.stream()
                .mapToInt(subject -> {
                    System.out.println(name + "학생의 " + subject.getName() + " 과목 성적은 " + subject.getScore() + "입니다.");
                    return subject.getScore();
                }).sum();

        System.out.println(name + " 학생의 총점은 " + totalScore + "입니다.");
    }
}
