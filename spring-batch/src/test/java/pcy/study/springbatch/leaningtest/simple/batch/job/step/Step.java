package pcy.study.springbatch.leaningtest.simple.batch.job.step;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import pcy.study.springbatch.leaningtest.simple.batch.*;

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
