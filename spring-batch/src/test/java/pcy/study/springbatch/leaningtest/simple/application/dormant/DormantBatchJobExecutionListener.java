package pcy.study.springbatch.leaningtest.simple.application.dormant;

import org.springframework.stereotype.Component;
import pcy.study.springbatch.email.EmailProvider;
import pcy.study.springbatch.leaningtest.simple.batch.job.JobExecution;
import pcy.study.springbatch.leaningtest.simple.batch.job.JobExecutionListener;

@Component
public class DormantBatchJobExecutionListener implements JobExecutionListener {

    private final EmailProvider emailProvider;

    public DormantBatchJobExecutionListener(EmailProvider emailProvider) {
        this.emailProvider = emailProvider;
    }

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
