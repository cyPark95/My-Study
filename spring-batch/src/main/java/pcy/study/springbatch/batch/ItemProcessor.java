package pcy.study.springbatch.batch;

public interface ItemProcessor<I, O> {

    O process(I item);
}
