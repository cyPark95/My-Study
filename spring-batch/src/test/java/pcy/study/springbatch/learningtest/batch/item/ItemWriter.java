package pcy.study.springbatch.learningtest.batch.item;

public interface ItemWriter<O> {

    void write(O item);
}
