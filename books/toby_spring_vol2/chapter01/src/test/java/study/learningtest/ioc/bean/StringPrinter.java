package study.learningtest.ioc.bean;

public class StringPrinter implements Printer {

    private final StringBuffer buffer = new StringBuffer();

    @Override
    public void print(String message) {
        buffer.append(message);
    }

    public String toString(){
        return buffer.toString();
    }
}
