package linkedlist;

public class MyNode<E> {

    private final Object data;
    public MyNode<E> next;

    public MyNode() {
        this(null);
    }

    public MyNode(Object data) {
        this.data = data;
    }

    public E getData() {
        return (E) data;
    }
}