package function.extension;

public class Exam {

    public Exam(ExamUser examUser) {
        if (ObjectUtils.isNotNull(examUser) && ObjectUtils.isNotNull(examUser.getName())) {
            if (StringUtils.isNotBlank(examUser.getName())) {
                System.out.println("Exam Name: " + examUser.getName());
            }
        }
    }

    public static void main(String[] args) {
        ExamUser nullUser = new ExamUser();
        new Exam(nullUser);

        ExamUser user = new ExamUser("name");
        new Exam(user);
    }

    static class ExamUser {

        private String name;

        public ExamUser() {
        }

        public ExamUser(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    class ObjectUtils {

        public static boolean isNotNull(Object value) {
            return value != null;
        }
    }

    class StringUtils {

        public static boolean isNotBlank(String value) {
            return !value.isBlank();
        }
    }
}
