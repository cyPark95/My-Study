package stack;

import array.MyArray;

public class MyStack<E> {

    private MyArray<E> arrStack;
    private int size, top;

    public MyStack() {
        arrStack = new MyArray<>(size);
    }

    public void push(E data) {
        if (top == size) {
            arrStack = arrStack.grow(++size);
        }

        arrStack.add(data);
        top++;
    }

    public E pop() {
        emptyCheck();
        return arrStack.remove(--top);
    }

    public E peek() {
        emptyCheck();
        return arrStack.get(top - 1);
    }

    public void print() {
        arrStack.print();
    }

    public int size() {
        return top;
    }

    private boolean isEmpty() {
        return top == 0;
    }

    private void emptyCheck() {
        if (isEmpty()) {
            throw new IllegalArgumentException("There is no element");
        }
    }
}