package pcy.study.springbatch.batch;

import lombok.Builder;

public class Step {

    private final Tasklet taskLet;

    public Step(Tasklet taskLet) {
        this.taskLet = taskLet;
    }

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
