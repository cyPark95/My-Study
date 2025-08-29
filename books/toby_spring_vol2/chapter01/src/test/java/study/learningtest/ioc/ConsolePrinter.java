package study.learningtest.ioc;

public class ConsolePrinter implements study.learningtest.ioc.Printer {

    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
