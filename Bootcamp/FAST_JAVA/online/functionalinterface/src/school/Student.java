package school;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private static long serialNumber = 1L;

    private final Long id;
    private final String name;
    private final Subject majorSubject;

    private final List<Score> scores;

    public Student(String name, Subject subject) {
        id = serialNumber++;
        this.name = name;
        majorSubject = subject;

        scores = new ArrayList<>();
    }

    public void addScore(Score score) {
        scores.add(score);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Subject getMajorSubject() {
        return majorSubject;
    }

    public List<Score> getScores() {
        return scores;
    }
}
