package school;

import grade.GradeEvaluationType;

public class Subject {

    private static long serialNumber = 1L;

    private final Long id;
    private final String name;
    private final GradeEvaluationType type;

    public Subject(String name, GradeEvaluationType type) {
        id = serialNumber++;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGrade(int score) {
        return type.getGrade(score);
    }
}
