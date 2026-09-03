// Custom exception used when a book ID is not found.
public class BookNotFoundException extends Exception {

    // Constructor receives the error message.
    public BookNotFoundException(String message) {
        super(message);
    }
}