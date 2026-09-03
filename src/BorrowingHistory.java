// This class is our own singly linked list.
// We are NOT using java.util.LinkedList.
public class BorrowingHistory {

    // 'head' points to the newest borrowing record.
    BorrowRecord head;

    // Adds a new borrowing record to the beginning of the list.
    // This makes the newest record appear first.
    public void addRecord(String bookId, String bookTitle,
                          java.time.LocalDate issueDate) {

        // Create a new node/record.
        BorrowRecord newRecord =
                new BorrowRecord(bookId, bookTitle, issueDate);

        // Connect the new record to the old first record.
        newRecord.next = head;

        // Make the new record the first record.
        head = newRecord;
    }

    // Prints the borrowing history from newest to oldest.
    public void printHistory() {

        // Start from the newest record.
        BorrowRecord current = head;

        // Continue until the end of the linked list.
        while (current != null) {

            System.out.println(
                    "Book ID: " + current.bookId
                            + " | Title: " + current.bookTitle
                            + " | Issue Date: " + current.issueDate
            );

            // Move to the next record.
            current = current.next;
        }
    }
}