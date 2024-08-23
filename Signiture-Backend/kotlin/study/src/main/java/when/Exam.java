package when;

public class Exam {

    public Exam(StoreUser storeUser) {

        String result = "";
        if (storeUser.getRole().equals("MASTER")) {
            result = "master";
        } else if (storeUser.getRole().equals("ADMIN")) {
            result = "admin";
        } else if (storeUser.getRole().equals("USER")) {
            result = "user";

        } else {
            result = "default";
        }

        switch (storeUser.getRole()) {
            case "MASTER":
            case "ADMIN":
                // master
                // admin
                break;
            case "USER":
                // user
                break;
            default:
                // default
        }

        try {

        } catch (Exception e) {
            if(e instanceof NullPointerException){
                NullPointerException exception = (NullPointerException) e;
            } else if(e instanceof IllegalAccessException){
                IllegalArgumentException exception = (IllegalArgumentException) e;
            }
        }
    }

    public static void main(String[] args) {
        StoreUser storeUser = new StoreUser();
        new Exam(storeUser);
    }

    static class StoreUser {

        private String name;

        private String role;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
