package study.learningtest.ioc;

public class StringPrinter implements study.learningtest.ioc.Printer {

    private StringBuffer buffer = new StringBuffer();

    @Override
    public void print(String message) {
        buffer.append(message);
    }

    public String toString(){
        return buffer.toString();
    }
}
