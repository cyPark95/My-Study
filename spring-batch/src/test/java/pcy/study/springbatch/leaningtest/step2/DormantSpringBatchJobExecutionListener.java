package pcy.study.springbatch.leaningtest.step2;

import pcy.study.springbatch.batch.JobExecution;
import pcy.study.springbatch.batch.JobExecutionListener;
import pcy.study.springbatch.email.EmailProvider;

public class DormantSpringBatchJobExecutionListener implements JobExecutionListener {

    private final EmailProvider emailProvider;

    public DormantSpringBatchJobExecutionListener(EmailProvider emailProvider) {
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
