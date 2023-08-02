package linkedlist;

import queue.MyQueue;

public class MyLinkedList<E> implements MyQueue<E> {

    private final MyNode<E> head;
    private int size;

    public MyLinkedList() {
        head = new MyNode<>();
    }

    public void add(E data) {
        MyNode<E> node = head;
        while (node.next != null) {
            node = node.next;
        }

        node.next = new MyNode<>(data);
        size++;
    }

    @Override
    public void offer(E data) {
        add(data);
    }

    @Override
    public E poll() {
        E element = get(0);
        remove(0);
        return element;
    }

    @Override
    public E peek() {
        return get(0);
    }

    public void insert(int index, E data) {
        indexCheck(index);

        MyNode<E> newNode = new MyNode<>(data);
        MyNode<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        newNode.next = node.next;
        node.next = newNode;
        size++;
    }

    public void removeAll() {
        head.next = null;
        size = 0;
    }

    public MyNode<E> remove(int index) {
        emptyCheck();
        indexCheck(index);

        MyNode<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        MyNode<E> target = node.next;
        if (target.next == null) {
            node.next = null;
        } else {
            node.next = target.next;
        }

        size--;
        return target;
    }

    public E get(int index) {
        return getNode(index).getData();
    }

    public MyNode<E> getNode(int index) {
        indexCheck(index);

        MyNode<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node.next;
    }

    public int size() {
        return size;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("[]");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        MyNode<E> node = head;
        while (true) {
            node = node.next;
            sb.append(node.getData());
            if (node.next == null) {
                sb.append("]");
                break;
            }
            sb.append(" -> ");
        }

        System.out.println(sb);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void indexCheck(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Invalid index");
        }
    }

    private void emptyCheck() {
        if (isEmpty()) {
            throw new IllegalArgumentException("There is no element");
        }
    }
}