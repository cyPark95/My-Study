package pcy.study.springbatch.learningtest.custombatch.tasklet;

import lombok.Builder;
import pcy.study.springbatch.learningtest.batch.AbstractJob;
import pcy.study.springbatch.learningtest.batch.step.SimpleTasklet;
import pcy.study.springbatch.learningtest.batch.item.ItemProcessor;
import pcy.study.springbatch.learningtest.batch.item.ItemReader;
import pcy.study.springbatch.learningtest.batch.item.ItemWriter;
import pcy.study.springbatch.learningtest.batch.job.JobExecutionListener;
import pcy.study.springbatch.learningtest.batch.step.Tasklet;

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
