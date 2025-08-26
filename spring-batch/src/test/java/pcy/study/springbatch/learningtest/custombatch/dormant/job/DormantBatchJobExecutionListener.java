package pcy.study.springbatch.learningtest.custombatch.dormant.job;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pcy.study.springbatch.email.EmailProvider;
import pcy.study.springbatch.learningtest.batch.job.JobExecution;
import pcy.study.springbatch.learningtest.batch.job.JobExecutionListener;

@Component
@RequiredArgsConstructor
public class DormantBatchJobExecutionListener implements JobExecutionListener {

    private final EmailProvider emailProvider;

    @Override
    public void beforeJob(JobExecution jobExecution) {
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        emailProvider.send(
                "admin@co.kr",
                "배치 완료 알림",
                String.format("DormantBatchJob 수행이 완료되었습니다. Status : %s", jobExecution.getStatus())
        );
    }
}
