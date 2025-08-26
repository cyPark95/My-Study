package pcy.study.springbatch.learningtest.custombatch.step;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import pcy.study.springbatch.email.EmailProvider;
import pcy.study.springbatch.learningtest.batch.StepJob;
import pcy.study.springbatch.learningtest.batch.job.Job;
import pcy.study.springbatch.learningtest.batch.job.JobExecutionListener;
import pcy.study.springbatch.learningtest.custombatch.AbstractCustomBatchJobTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@Import(StepJobConfiguration.class)
public class CustomBatchStepJobTest extends AbstractCustomBatchJobTest {

    @Autowired
    private StepJob dormantBatchJob;

    @Autowired
    private JobExecutionListener jobExecutionListener;

    @MockitoSpyBean
    private EmailProvider emailProvider;

    @DisplayName("358일 전에 로그인한 사용자에게 휴먼 계정 예정 공지 메일을 발송해야 한다.")
    @Test
    void sendDormantBatchEmail() {
        // given
        super.saveUser(358);

        // when
        dormantBatchJob.execute();

        // then
        verify(emailProvider, atLeastOnce()).send(any(), any(), any());
    }

    @Override
    protected Job createJob() {
        return dormantBatchJob;
    }

    @Override
    protected Job createFailedJob() {
        return new StepJob(jobExecutionListener, null);
    }
}
