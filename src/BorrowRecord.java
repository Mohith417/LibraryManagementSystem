import java.time.LocalDate;

// This class represents one borrowing record.
// Each record stores the book details and issue date.
public class BorrowRecord {

    String bookId;
    String bookTitle;
    LocalDate issueDate;

    // 'next' points to the next record in our custom linked list.
    BorrowRecord next;

    public BorrowRecord(String bookId, String bookTitle, LocalDate issueDate) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.issueDate = issueDate;
        this.next = null;
    }
}