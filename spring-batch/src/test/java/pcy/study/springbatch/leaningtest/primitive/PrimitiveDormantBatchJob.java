package pcy.study.springbatch.leaningtest.primitive;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import pcy.study.springbatch.batch.JobExecution;
import pcy.study.springbatch.email.EmailProvider;
import pcy.study.springbatch.user.User;
import pcy.study.springbatch.user.UserRepository;

import java.time.LocalDate;

public class PrimitiveDormantBatchJob {

    private final UserRepository userRepository;
    private final EmailProvider emailProvider;

    public PrimitiveDormantBatchJob(UserRepository userRepository, EmailProvider emailProvider) {
        this.userRepository = userRepository;
        this.emailProvider = emailProvider;
    }

    public JobExecution execute() {
        JobExecution jobExecution = new JobExecution();

        try {
            int pageNo = 0;
            LocalDate dormantDate = LocalDate.now().minusDays(365);

            while (true) {
                // 1. 사용자 조회
                PageRequest pageRequest = PageRequest.of(pageNo++, 1, Sort.by("id").ascending());
                Page<User> page = userRepository.findAll(pageRequest);

                if (page.isEmpty()) {
                    break;
                }

                User user = page.getContent().getFirst();

                // 2. 휴면 사용자 추출/변환
                boolean isDormant = dormantDate.isAfter(user.getLoginAt().toLocalDate());
                if (!isDormant) {
                    continue;
                }

                user.changeDormant();

                // 3. 휴먼 사용자 상태 저장
                userRepository.save(user);

                // 4. 이메일 발송
                emailProvider.send(user.getEmail(), "휴먼 전환 안내 메일입니다.", "내용");
            }

            jobExecution.completed();
        } catch (Exception e) {
            jobExecution.failed();
        } finally {
            jobExecution.end();
        }

        emailProvider.send(
                "admin@co.kr",
                "배치 완료 알림",
                String.format("DormantBatchJob 수행이 완료되었습니다. Status : %s", jobExecution.getStatus())
        );

        return jobExecution;
    }
}
