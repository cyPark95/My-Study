public class Main {

    public static void main(String[] args) {
        Library library = new Library();
        Student john = new Student("john", library);
        Student mike = new Student("mike", library);
        Student bell = new Student("bell", library);
        Student sara = new Student("sara", library);
        Student sofia = new Student("sofia", library);
        Student tom = new Student("tom", library);

        john.start();
        mike.start();
        bell.start();
        sara.start();
        sofia.start();
        tom.start();
    }
}
