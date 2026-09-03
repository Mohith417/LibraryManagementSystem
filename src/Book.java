public class Book {

    String bookId;
    String title;
    String author;
    int totalCopies;
    int availableCopies;

    public Book(String bookId, String title, String author, int totalCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    @Override
    public String toString() {
        return "[" + bookId + "] " + title + " by " + author
                + " | Copies: " + availableCopies + "/" + totalCopies;
    }
}