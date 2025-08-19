package pcy.study.springbatch.batch;

public interface ItemWriter<O> {

    void write(O item);
}
