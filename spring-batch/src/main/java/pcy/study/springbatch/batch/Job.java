package pcy.study.springbatch.batch;

import lombok.Builder;

public class Job {

    private final TaskLet taskLet;
    private final JobExecutionListener jobExecutionListener;

    public Job(TaskLet taskLet, JobExecutionListener jobExecutionListener) {
        this.taskLet = taskLet;
        this.jobExecutionListener = jobExecutionListener;
    }

    @Builder
    public Job(JobExecutionListener jobExecutionListener, ItemReader<?> itemReader, ItemProcessor<?, ?> itemProcessor, ItemWriter<?> itemWriter) {
        this.taskLet = new SimpleTaskLet(itemReader, itemProcessor, itemWriter);
        this.jobExecutionListener = jobExecutionListener;
    }

    public JobExecution execute() {
        JobExecution jobExecution = new JobExecution();
        jobExecutionListener.beforeJob(jobExecution);

        try {
            taskLet.execute();
            jobExecution.completed();
        } catch (Exception e) {
            jobExecution.failed();
        } finally {
            jobExecution.end();
        }

        jobExecutionListener.afterJob(jobExecution);

        return jobExecution;
    }
}
