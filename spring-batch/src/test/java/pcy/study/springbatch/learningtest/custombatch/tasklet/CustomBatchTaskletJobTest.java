package pcy.study.springbatch.learningtest.custombatch.tasklet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import pcy.study.springbatch.learningtest.batch.job.Job;
import pcy.study.springbatch.learningtest.batch.job.JobExecutionListener;
import pcy.study.springbatch.learningtest.custombatch.AbstractCustomBatchJobTest;

@Import(TaskletJobConfiguration.class)
public class CustomBatchTaskletJobTest extends AbstractCustomBatchJobTest {

    @Autowired
    private TaskletJob dormantBatchJob;

    @Autowired
    private JobExecutionListener jobExecutionListener;

    @Override
    protected Job createJob() {
        return dormantBatchJob;
    }

    @Override
    protected Job createFailedJob() {
        return new TaskletJob(jobExecutionListener, null);
    }
}
