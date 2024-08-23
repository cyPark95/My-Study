package compatibility.service;

import pcy.study.compatibility.model.KotlinUser;

import java.time.LocalDateTime;

public class UserService {

    private static void createKotlinUser(KotlinUser kotlinUser) {
        var user = new KotlinUser(
                "홍길동",
                20,
                kotlinUser.getEmail(),
                kotlinUser.getPhoneNumber(),
                LocalDateTime.now()
        );

        System.out.println(user);
    }

    public static void main(String[] args) {
        var kotlinUser = new KotlinUser(
                "Dummy",
                0,
                "email@gmail.com",
                "000-0000-0000",
                LocalDateTime.of(2000, 8, 23, 16, 25, 30)
        );

        UserService.createKotlinUser(kotlinUser);
    }
}
