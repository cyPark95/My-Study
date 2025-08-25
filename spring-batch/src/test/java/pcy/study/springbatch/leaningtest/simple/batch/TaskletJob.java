package pcy.study.springbatch.leaningtest.simple.batch;

import lombok.Builder;
import pcy.study.springbatch.leaningtest.simple.batch.job.AbstractJob;
import pcy.study.springbatch.leaningtest.simple.batch.job.JobExecutionListener;

public class TaskletJob extends AbstractJob {

    private final Tasklet taskLet;

    public TaskletJob(JobExecutionListener jobExecutionListener, Tasklet taskLet) {
        super(jobExecutionListener);
        this.taskLet = taskLet;
    }

    @Builder
    public TaskletJob(
            JobExecutionListener jobExecutionListener,
            ItemReader<?> itemReader,
            ItemProcessor<?, ?> itemProcessor,
            ItemWriter<?> itemWriter
    ) {
        super(jobExecutionListener);
        this.taskLet = new SimpleTasklet(itemReader, itemProcessor, itemWriter);
    }

    @Override
    public void doExecute() {
        taskLet.execute();
    }
}
