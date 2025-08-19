package pcy.study.springbatch.batch;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleTasklet<I, O> implements Tasklet {

    private final ItemReader<I> itemReader;
    private final ItemProcessor<I, O> itemProcessor;
    private final ItemWriter<O> itemWriter;

    @Override
    public void execute() {
        while (true) {
            I read = itemReader.read();
            if (read == null) {
                break;
            }

            O process = itemProcessor.process(read);
            if (process == null) {
                continue;
            }

            itemWriter.write(process);
        }
    }
}
