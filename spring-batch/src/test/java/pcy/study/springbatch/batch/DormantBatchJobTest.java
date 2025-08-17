package pcy.study.springbatch.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.springbatch.application.DormantBatchJobExecutionListener;
import pcy.study.springbatch.email.EmailProvider;
import pcy.study.springbatch.user.Status;
import pcy.study.springbatch.user.User;
import pcy.study.springbatch.user.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DormantBatchJobTest {

    @Autowired
    private Job job;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @DisplayName("로그인 시간이 일년을 경과한 사용자가 3명이고, 일년 이내에 로그인한 사용자가 5명이면 3명의 사용자가 휴면 전환 대상이다.")
    @Test
    void changeDormant() {
        // given
        IntStream.range(361, 369)
                .forEach(this::saveUser);

        // when
        JobExecution result = job.execute();

        // then
        assertThat(result.getStatus()).isEqualTo(BatchStatus.COMPLETED);

        long dormantCount = userRepository.findAll().stream()
                .filter(user -> Status.DORMANT == user.getStatus())
                .count();

        assertThat(dormantCount).isEqualTo(3);
    }

    @DisplayName("사용자 10명 모두 휴먼 전환 대상이 아니면, 휴먼 전환 대상은 0명이다.")
    @Test
    void noChangeDormant() {
        // given
        IntStream.range(0, 10)
                .forEach(this::saveUser);

        // when
        JobExecution result = job.execute();

        // then
        assertThat(result.getStatus()).isEqualTo(BatchStatus.COMPLETED);

        long dormantCount = userRepository.findAll().stream()
                .filter(user -> Status.DORMANT == user.getStatus())
                .count();

        assertThat(dormantCount).isEqualTo(0);
    }

    @DisplayName("사용자가 없는 경우에도 배치는 정상 동작해야 한다.")
    @Test
    void batchRunsWithNoUsers() {
        // when
        JobExecution result = job.execute();

        // then
        assertThat(result.getStatus()).isEqualTo(BatchStatus.COMPLETED);
    }

    @DisplayName("배치가 실패하면 FAILED 상태를 반환해야 한다.")
    @Test
    void batchFailsAndReturnsFailedStatus() {
        // given
        Job failedJob = new Job(null, new DormantBatchJobExecutionListener(new FakeEmailSender()));

        // when
        JobExecution result = failedJob.execute();

        // then
        assertThat(result.getStatus()).isEqualTo(BatchStatus.FAILED);
    }

    private void saveUser(int loginMinusDays) {
        String uuid = UUID.randomUUID().toString();
        User user = new User(uuid, uuid + "@co.kr");
        ReflectionTestUtils.setField(user, "loginAt", LocalDateTime.now().minusDays(loginMinusDays));
        userRepository.save(user);
    }

    private static class FakeEmailSender implements EmailProvider {

        @Override
        public void send(String email, String title, String content) {
        }
    }
}
