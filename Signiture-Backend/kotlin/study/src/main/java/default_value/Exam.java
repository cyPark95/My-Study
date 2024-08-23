package default_value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Exam {

    public Exam(Store store) {
        var storeRegisterAt = toLocalDateTimeString(store.getRegisterAt());
        System.out.println(storeRegisterAt);
    }

    private String toLocalDateTimeString(LocalDateTime localDateTime) {
        LocalDateTime temp = Optional.ofNullable(localDateTime).orElse(LocalDateTime.now());
        return temp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static void main(String[] args) {
        var store = new Store();
        new Exam(store);
    }

    static class Store {

        private LocalDateTime registerAt;

        public LocalDateTime getRegisterAt() {
            return registerAt;
        }

        public void setRegisterAt(LocalDateTime registerAt) {
            this.registerAt = registerAt;
        }
    }
}
