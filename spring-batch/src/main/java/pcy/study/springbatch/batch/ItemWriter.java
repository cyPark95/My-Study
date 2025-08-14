package pcy.study.springbatch.batch;

public interface ItemWriter<T> {

    void write(T item);
}
