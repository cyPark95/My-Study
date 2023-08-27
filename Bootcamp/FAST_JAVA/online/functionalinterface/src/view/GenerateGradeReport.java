package view;

import school.School;
import school.Score;
import school.Student;
import school.Subject;

import java.util.List;
import java.util.Objects;

public class GenerateGradeReport {

    private static final String TITLE = " 수강생 학점\n";
    private static final String HEADER = "       이름 |       학번 |   중점과목 | 점수\n";
    private static final String LINE = "----------------------------------------------\n";

    private static final School school = School.getInstance();
    private static StringBuilder sb;

    public static String getReport() {
        sb = new StringBuilder();
        List<Subject> subjects = school.getSubjects();
        for (Subject subject : subjects) {
            makeHeader(subject);
            makeBody(subject);
            makeFooter();
        }

        return sb.toString();
    }

    private static void makeHeader(Subject subject) {
        sb.append(GenerateGradeReport.LINE)
                .append(subject.getName())
                .append(GenerateGradeReport.TITLE)
                .append(GenerateGradeReport.HEADER)
                .append(GenerateGradeReport.LINE);
    }

    private static void makeBody(Subject subject) {
        List<Student> students = school.getStudents();

		for (Student student : students) {
            try {
                String scoreGrade = getScoreGrade(student, subject.getId());

                sb.append(String.format("%10s |%10d |%10s |%s",
                        student.getName(),
                        student.getId(),
                        student.getMajorSubject().getName(),
                        scoreGrade
                )).append("\n").append(LINE);
            } catch (Exception ignored) {
            }
		}
    }

    private static String  getScoreGrade(Student student, long subjectId) {
        List<Score> scores = student.getScores();
        Subject majorSubject = student.getMajorSubject();

        for (Score score : scores) {
            if (score.subject().getId() == subjectId) {
                Subject subject = score.subject();
                String grade;

                if (Objects.equals(subject.getId(), majorSubject.getId())) {
                    grade = majorSubject.getGrade(score.point());
                } else {
                    grade = subject.getGrade(score.point());
                }

                return String.format(
                        "%3d : %s",
                        score.point(),
                        grade
                );
            }
        }

        throw new RuntimeException();
    }

    private static void makeFooter(){
        sb.append("\n");
    }
}
