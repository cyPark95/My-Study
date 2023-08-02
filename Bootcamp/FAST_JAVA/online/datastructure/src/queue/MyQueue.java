package queue;

public interface MyQueue<E> {

    void add(E data);

    void offer(E data);

    E poll();

    E peek();

    void print();

    boolean isEmpty();

    int size();
}