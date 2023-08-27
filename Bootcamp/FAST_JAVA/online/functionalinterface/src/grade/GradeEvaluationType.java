package grade;

import java.util.function.Function;

public enum GradeEvaluationType {

    MAJOR_SUBJECT(score -> {
        if (score >= 95 && score <= 100) {
            return "S";
        } else if (score >= 90 && score <= 94) {
            return "A";
        } else if (score >= 80 && score <= 89) {
            return "B";
        } else if (score >= 70 && score <= 79) {
            return "C";
        } else if (score >= 60 && score <= 69) {
            return "D";
        }
        return "F";
    }),
    BASIC_SUBJECT(score -> {
        if (score >= 90 && score <= 100) {
            return "A";
        } else if (score >= 80 && score <= 89) {
            return "B";
        } else if (score >= 70 && score <= 79) {
            return "C";
        } else if (score >= 55 && score <= 69) {
            return "D";
        }
        return "F";
    }),
    PASS_FAIL_SUBJECT(score -> {
        if (score >= 70) {
            return "P";
        }
        return "F";
    })
    ;

    private final Function<Integer, String> function;

    GradeEvaluationType(Function<Integer, String> function) {
        this.function = function;
    }

    public String getGrade(int score) {
        return function.apply(score);
    }
}
