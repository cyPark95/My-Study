package pcy.study.springbatch.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private Status status;

    private LocalDateTime loginAt;

    private LocalDateTime createdAt;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.status = Status.NORMAL;
        this.loginAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    public boolean isDormantSince(LocalDate dormantDate) {
        return dormantDate.isAfter(loginAt.toLocalDate());
    }

    public void changeDormant() {
        this.status = Status.DORMANT;
    }
}
