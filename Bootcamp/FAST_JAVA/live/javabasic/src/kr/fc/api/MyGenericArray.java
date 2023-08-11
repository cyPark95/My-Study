package kr.fc.api;

public class MyGenericArray<T> {    // 용량 체크, 용량 늘리기, 삭제 ... -> List(ArrayList), Map, Set ...

    private final Object[] arr;
    private int count;

    public MyGenericArray() {
        this(10);
    }

    public MyGenericArray(int size) {
        arr = new Object[size];
    }

    public void add(Object data) {
        arr[count++] = data;
    }

    public int size() {
        return count;
    }

    public T get(int index) {
        return (T) arr[index];
    }
}
