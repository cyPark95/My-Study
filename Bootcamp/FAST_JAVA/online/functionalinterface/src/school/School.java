package school;

import java.util.ArrayList;
import java.util.List;

public class School {

    private static final String SCHOOL_NAME = "Good School";

    private static School instance;

    private final String name;
    private final List<Student> students;
    private final List<Subject> subjects;

    private School() {
        name = SCHOOL_NAME;
        students = new ArrayList<>();
        subjects = new ArrayList<>();
    }

    public static School getInstance(){
        if (instance == null) {
            instance = new School();
        }

        return instance;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }
}
