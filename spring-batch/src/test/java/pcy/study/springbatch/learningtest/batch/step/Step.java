package pcy.study.springbatch.learningtest.batch.step;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import pcy.study.springbatch.learningtest.batch.item.ItemProcessor;
import pcy.study.springbatch.learningtest.batch.item.ItemReader;
import pcy.study.springbatch.learningtest.batch.item.ItemWriter;

@RequiredArgsConstructor
public class Step {

    private final Tasklet taskLet;

    @Builder
    public Step(
            ItemReader<?> itemReader,
            ItemProcessor<?, ?> itemProcessor,
            ItemWriter<?> itemWriter
    ) {
        this.taskLet = new SimpleTasklet(itemReader, itemProcessor, itemWriter);
    }

    public void execute() {
        taskLet.execute();
    }
}
