package pcy.study.springbatch.learningtest.batch;

import lombok.RequiredArgsConstructor;
import pcy.study.springbatch.learningtest.batch.job.Job;
import pcy.study.springbatch.learningtest.batch.job.JobExecution;
import pcy.study.springbatch.learningtest.batch.job.JobExecutionListener;

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
