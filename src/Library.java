import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// This class contains the main library operations.
public class Library {

    // HashMap stores books using Book ID as the key.
    // DSA requirement: HashMap<String, Book>
    private HashMap<String, Book> catalog = new HashMap<>();

    // HashMap stores members using Member ID as the key.
    private HashMap<String, Member> members = new HashMap<>();

    // Queue stores waiting member IDs for each book.
    // DSA requirement: Queue<String>
    private HashMap<String, Queue<String>> waitlists = new HashMap<>();

    // Fixed "today" date as required by the problem statement.
    private final LocalDate today = LocalDate.of(2025, 1, 15);

    // =========================================================
    // ADD BOOK
    // =========================================================
    public void addBook(Book book) {
        catalog.put(book.bookId, book);

        // Create an empty waitlist for this book.
        waitlists.putIfAbsent(book.bookId, new LinkedList<>());

        System.out.println("Book added successfully.");
    }

    // =========================================================
    // REMOVE BOOK
    // =========================================================
    public void removeBook(String bookId) throws BookNotFoundException {

        // Check whether the book exists.
        if (!catalog.containsKey(bookId)) {
            throw new BookNotFoundException(
                    "Book with ID " + bookId + " not found."
            );
        }

        catalog.remove(bookId);
        waitlists.remove(bookId);

        System.out.println("Book removed successfully.");
    }

    // =========================================================
    // SEARCH BY TITLE
    // =========================================================
    public void searchByTitle(String keyword) {

        // ArrayList stores the search results.
        // DSA requirement: ArrayList<Book>
        ArrayList<Book> results = new ArrayList<>();

        // =====================================================
        // STREAM API
        // =====================================================
        // stream() processes the books one by one.
        // filter() keeps books whose title contains the keyword.
        // forEach() adds matching books to the ArrayList.
        catalog.values().stream()
                .filter(book ->
                        book.title.toLowerCase()
                                .contains(keyword.toLowerCase())
                )
                .forEach(results::add);
        // =====================================================
        // END OF STREAM API
        // =====================================================

        printSearchResults(results);
    }

    // =========================================================
    // SEARCH BY AUTHOR
    // =========================================================
    public void searchByAuthor(String keyword) {

        // ArrayList stores the search results.
        ArrayList<Book> results = new ArrayList<>();

        for (Book book : catalog.values()) {

            // String methods are used here:
            // toLowerCase() and contains()
            if (book.author.toLowerCase()
                    .contains(keyword.toLowerCase())) {

                results.add(book);
            }
        }

        printSearchResults(results);
    }

    // =========================================================
    // DISPLAY SEARCH RESULTS
    // =========================================================
    private void printSearchResults(ArrayList<Book> results) {

        if (results.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        System.out.println("\nSearch Results:");

        for (Book book : results) {
            System.out.println(book);
        }
    }

    // =========================================================
    // ADD MEMBER
    // =========================================================
    public void addMember(Member member) {
        members.put(member.memberId, member);

        System.out.println("Member added successfully.");
    }

    // =========================================================
    // ISSUE BOOK
    // =========================================================
    public void issueBook(String bookId, String memberId)
            throws BookNotFoundException,
            MemberNotFoundException,
            BorrowLimitExceededException {

        // Check whether the book exists.
        Book book = catalog.get(bookId);

        if (book == null) {
            throw new BookNotFoundException(
                    "Book with ID " + bookId + " not found."
            );
        }

        // Check whether the member exists.
        Member member = members.get(memberId);

        if (member == null) {
            throw new MemberNotFoundException(
                    "Member with ID " + memberId + " not found."
            );
        }

        // Check the maximum borrowing limit of 3 books.
        if (!member.canBorrow()) {
            throw new BorrowLimitExceededException(
                    "Member already has 3 books."
            );
        }

        // If no copies are available, add the member to the waitlist.
        if (book.availableCopies == 0) {

            Queue<String> queue =
                    waitlists.get(bookId);

            if (!queue.contains(memberId)) {
                queue.offer(memberId);
                System.out.println(
                        "No copies available. Member added to waitlist."
                );
            } else {
                System.out.println(
                        "Member is already in the waitlist."
                );
            }

            return;
        }

        // Reduce the available copies.
        book.availableCopies--;

        // Add the book to the member's currently borrowed list.
        member.borrowBook(bookId);

        // Add the borrowing record to our custom linked list.
        member.history.addRecord(
                bookId,
                book.title,
                today
        );

        System.out.println(
                "Book issued successfully to " + member.name + "."
        );
    }

    // =========================================================
    // RETURN BOOK
    // =========================================================
    public void returnBook(String bookId, String memberId)
            throws BookNotFoundException,
            MemberNotFoundException {

        // Find the book.
        Book book = catalog.get(bookId);

        if (book == null) {
            throw new BookNotFoundException(
                    "Book with ID " + bookId + " not found."
            );
        }

        // Find the member.
        Member member = members.get(memberId);

        if (member == null) {
            throw new MemberNotFoundException(
                    "Member with ID " + memberId + " not found."
            );
        }

        // Check whether this member actually borrowed the book.
        if (!member.currentlyBorrowed.contains(bookId)) {
            System.out.println(
                    "This member has not borrowed this book."
            );
            return;
        }

        // Remove the book from the member's borrowed list.
        member.returnBook(bookId);

        // Increase the available copies.
        book.availableCopies++;

        System.out.println("Book returned successfully.");

        // Check whether someone is waiting for this book.
        Queue<String> queue = waitlists.get(bookId);

        if (queue != null && !queue.isEmpty()) {

            String nextMemberId = queue.poll();

            System.out.println(
                    "Next member in waitlist: " + nextMemberId
            );
        }
    }

    // =========================================================
    // VIEW OVERDUE BOOKS
    // =========================================================
    public void viewOverdueBooks() {

        System.out.println("\nOverdue Books:");

        boolean found = false;

        for (Member member : members.values()) {

            BorrowRecord current = member.history.head;

            while (current != null) {

                long days =
                        ChronoUnit.DAYS.between(
                                current.issueDate,
                                today
                        );

                // A book is overdue when it is issued for
                // more than 14 days.
                if (days > 14) {

                    System.out.println(
                            "Member: " + member.name
                                    + " | Book: " + current.bookTitle
                                    + " | Issue Date: "
                                    + current.issueDate
                                    + " | Days: " + days
                    );

                    found = true;
                }

                current = current.next;
            }
        }

        if (!found) {
            System.out.println("No overdue books.");
        }
    }

    // =========================================================
    // DISPLAY ALL BOOKS
    // =========================================================
    public void displayBooks() {

        System.out.println("\nLibrary Books:");

        if (catalog.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }

        for (Book book : catalog.values()) {
            System.out.println(book);
        }
    }

    // =========================================================
    // DISPLAY ALL MEMBERS
    // =========================================================
    public void displayMembers() {

        System.out.println("\nLibrary Members:");

        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }

        for (Member member : members.values()) {
            System.out.println(member);
        }
    }

    // =========================================================
    // SHOW MEMBER BORROWING HISTORY
    // =========================================================
    public void showBorrowingHistory(String memberId)
            throws MemberNotFoundException {

        Member member = members.get(memberId);

        if (member == null) {
            throw new MemberNotFoundException(
                    "Member with ID " + memberId + " not found."
            );
        }

        System.out.println(
                "\nBorrowing History of " + member.name + ":"
        );

        if (member.history.head == null) {
            System.out.println("No borrowing history.");
            return;
        }

        // Print newest record first.
        member.history.printHistory();
    }
}