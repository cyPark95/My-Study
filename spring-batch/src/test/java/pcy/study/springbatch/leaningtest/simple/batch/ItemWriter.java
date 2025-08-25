package pcy.study.springbatch.leaningtest.simple.batch;

public interface ItemWriter<O> {

    void write(O item);
}
