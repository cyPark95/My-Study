package compatibility.model;

import java.time.LocalDateTime;

public class JavaUser {

    private final String name;

    private final Integer age;

    private final String email;

    private final String phoneNumber;

    private final LocalDateTime registeredAt;

    public JavaUser(String name, Integer age, String email, String phoneNumber, LocalDateTime registeredAt) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registeredAt = registeredAt;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    @Override
    public String toString() {
        return "JavaDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", registeredAt=" + registeredAt +
                '}';
    }
}
