package pcy.study.springbatch.learningtest.batch.item;

public interface ItemProcessor<I, O> {

    O process(I item);
}
