package study.learningtest.ioc;

public class Hello {

    private String name;
    private study.learningtest.ioc.Printer printer;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrinter(study.learningtest.ioc.Printer printer) {
        this.printer = printer;
    }

    public String sayHello() {
        return "Hello " + name;
    }

    public void print() {
        printer.print(sayHello());
    }
}
