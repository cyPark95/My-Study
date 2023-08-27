import grade.GradeEvaluationType;
import school.School;
import school.Score;
import school.Student;
import school.Subject;
import view.GenerateGradeReport;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        School school = School.getInstance();

        Subject korean = createSubject("korean", GradeEvaluationType.BASIC_SUBJECT);
        Subject math = createSubject("math", GradeEvaluationType.BASIC_SUBJECT);
        Subject dance = createSubject("dance", GradeEvaluationType.PASS_FAIL_SUBJECT);

        school.addSubject(korean);
        school.addSubject(math);
        school.addSubject(dance);

        school.addStudent(createStudent("John", korean, List.of(korean, math), 95, 56));
        school.addStudent(createStudent("Mike", math, List.of(korean, math, dance), 95, 95, 95));
        school.addStudent(createStudent("Sara", korean, List.of(korean, math, dance), 100, 88, 85));
        school.addStudent(createStudent("Bell", korean, List.of(korean, math), 89, 95));
        school.addStudent(createStudent("Sofia", math, List.of(korean, math, dance), 85, 56, 55));

        System.out.println(GenerateGradeReport.getReport());
    }

    private static Subject createSubject(String name, GradeEvaluationType type) {
        return new Subject(name, type);
    }

    private static Student createStudent(String name, Subject majorSubject, List<Subject> subjects, int... scores) {
        int length = subjects.size();
        if (subjects.size() != scores.length) {
            throw new IllegalArgumentException("과목과 점수가 일치하지 않습니다.");
        }

        Student student = new Student(name, majorSubject);

        for (int i = 0; i < length; i++) {
            student.addScore(createScore(student.getId(), subjects.get(i), scores[i]));
        }

        return student;
    }

    private static Score createScore(long studentId, Subject subject, int score) {
        return new Score(studentId, subject, score);
    }

}
