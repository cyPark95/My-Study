package pcy.study.api.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pcy.study.db.acount.AccountRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JpaConfigTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void repositoryConfig() {
        assertThat(accountRepository).isNotNull();
    }
}
