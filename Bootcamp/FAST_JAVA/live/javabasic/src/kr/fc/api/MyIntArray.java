package kr.fc.api;

public class MyIntArray {

    private final int[] arr;
    private int count;

    public MyIntArray() {
        this(10);
    }

    public MyIntArray(int size) {
        arr = new int[size];
    }

    public void add(int data) {
        arr[count++] = data;
    }

    public int size() {
        return count;
    }

    public int get(int index) {
        return arr[index];
    }
}
