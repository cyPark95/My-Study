class Student extends Thread {

    private final Library library;

    public Student(String name, Library library) {
        super(name);
        this.library = library;
    }

    public void run() {
        try {
            String title = library.borrowBook();
            if (title == null) {
                System.out.println("[" + getName() + "] 대여하지 못했습니다.");
                return;
            }

            sleep(2000);
            library.returnBook(title);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
