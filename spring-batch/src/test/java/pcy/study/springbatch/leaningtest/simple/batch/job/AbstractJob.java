package pcy.study.springbatch.leaningtest.simple.batch.job;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractJob implements Job {

    private final JobExecutionListener jobExecutionListener;

    @Override
    public JobExecution execute() {
        JobExecution jobExecution = new JobExecution();
        jobExecutionListener.beforeJob(jobExecution);

        try {
            doExecute();
            jobExecution.completed();
        } catch (Exception e) {
            jobExecution.failed();
        } finally {
            jobExecution.end();
        }

        jobExecutionListener.afterJob(jobExecution);

        return jobExecution;
    }

    public abstract void doExecute();
}
