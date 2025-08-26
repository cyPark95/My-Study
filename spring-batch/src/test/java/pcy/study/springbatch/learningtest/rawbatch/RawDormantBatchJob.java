package pcy.study.springbatch.learningtest.rawbatch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import pcy.study.springbatch.email.EmailProvider;
import pcy.study.springbatch.learningtest.batch.job.JobExecution;
import pcy.study.springbatch.user.User;
import pcy.study.springbatch.user.UserRepository;

import java.time.LocalDate;

public class RawDormantBatchJob {

    private static final int DORMANT_PERIOD_DAYS = 365;

    private final UserRepository userRepository;
    private final EmailProvider emailProvider;

    public RawDormantBatchJob(UserRepository userRepository, EmailProvider emailProvider) {
        this.userRepository = userRepository;
        this.emailProvider = emailProvider;
    }

    public JobExecution execute() {
        JobExecution jobExecution = new JobExecution();

        try {
            int pageNo = 0;

            while (true) {
                PageRequest pageRequest = PageRequest.of(pageNo++, 1, Sort.by("id").ascending());
                Page<User> page = userRepository.findAll(pageRequest);

                if (page.isEmpty()) {
                    break;
                }

                User user = page.getContent().getFirst();

                LocalDate dormantDate = LocalDate.now()
                        .minusDays(DORMANT_PERIOD_DAYS);

                if (!user.isDormantSince(dormantDate)) {
                    continue;
                }

                user.changeDormant();

                userRepository.save(user);

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
