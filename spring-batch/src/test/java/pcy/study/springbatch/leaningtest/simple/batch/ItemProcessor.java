package pcy.study.springbatch.leaningtest.simple.batch;

public interface ItemProcessor<I, O> {

    O process(I item);
}
