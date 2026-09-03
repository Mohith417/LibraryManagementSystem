// Custom exception used when a member tries to borrow more than 3 books.
public class BorrowLimitExceededException extends Exception {

    // Constructor receives the error message.
    public BorrowLimitExceededException(String message) {
        super(message);
    }
}