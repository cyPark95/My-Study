import java.util.ArrayList;

class Library {

    public ArrayList<String> books = new ArrayList<>();

    public Library() {
        books.add("<Effective Java>");
        books.add("<Real MySQL>");
        books.add("<Clean Code>");
    }

    public synchronized String borrowBook() throws InterruptedException {
        Thread thread = Thread.currentThread();
        while (books.size() == 0) {
            System.out.println("[" + thread.getName() + "] wait for borrow");
            wait();
            System.out.println("[" + thread.getName() + "] possible borrow");
        }

        String book = books.remove(0);
        System.out.println("[" + thread.getName() + "] " + book + " borrow");
        return book;
    }

    public synchronized void returnBook(String book) {
        Thread thread = Thread.currentThread();
        books.add(book);
        notifyAll();
        System.out.println("[" + thread.getName() + "] " + book + " return");
    }
}
