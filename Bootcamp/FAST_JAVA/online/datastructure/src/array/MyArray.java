package array;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class MyArray<E> {

    private final Object[] arr;
    private final int size;
    private int count;

    public MyArray(int size) {
        this.size = size;
        arr = new Object[size];
    }

    public MyArray(Object[] original, int newSize) {
        arr = Arrays.copyOf(original, newSize);
        size = newSize;
        count = original.length;
    }

    public void add(E element) {
        sizeCheck();
        arr[count++] = element;
    }

    public void insert(int index, E element) {
        sizeCheck();
        indexCheck(index);

        if (count - index >= 0)
            System.arraycopy(arr, index, arr, index + 1, count - index);

        arr[index] = element;
        count++;
    }

    public void removeAll() {
        for (int i = 0; i < count; i++) {
            arr[i] = null;
        }
        count = 0;
    }

    public E remove(int index) {
        emptyCheck();
        indexCheck(index);

        E target = (E) arr[index];
        if (count - (index + 1) >= 0)
            System.arraycopy(arr, index + 1, arr, index + 1 - 1, count - (index + 1));

        arr[count - 1] = null;
        count--;
        return target;
    }

    public E get(int index) {
        indexCheck(index);
        return (E) arr[index];
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("[]");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<E> iter = Arrays.stream(arr)
                .filter(Objects::nonNull)
                .map(e -> (E) e)
                .iterator();

        while (true) {
            E e = iter.next();
            sb.append(e);
            if (!iter.hasNext()) {
                sb.append("]");
                break;
            }
            sb.append(",").append(" ");
        }

        System.out.println(sb);
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public MyArray<E> grow(int newSize) {
        return new MyArray<>(arr, newSize);
    }

    private void sizeCheck() {
        if (count >= size) {
            throw new IllegalArgumentException("Not enough memory");
        }
    }

    private void indexCheck(int index) {
        if (index < 0 || index > count) {
            throw new IllegalArgumentException("Invalid Index");
        }
    }

    private void emptyCheck() {
        if (isEmpty()) {
            throw new IllegalArgumentException("There is no element");
        }
    }
}