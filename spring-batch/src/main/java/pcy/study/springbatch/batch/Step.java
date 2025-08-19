package pcy.study.springbatch.batch;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

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
