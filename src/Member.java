import java.util.ArrayList;

public class Member {

    String memberId;
    String name;
    ArrayList<String> currentlyBorrowed;
    BorrowingHistory history;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.currentlyBorrowed = new ArrayList<>();
        this.history = new BorrowingHistory();
    }

    public boolean canBorrow() {
        return currentlyBorrowed.size() < 3;
    }

    public void borrowBook(String bookId) {
        currentlyBorrowed.add(bookId);
    }

    public void returnBook(String bookId) {
        currentlyBorrowed.remove(bookId);
    }

    @Override
    public String toString() {
        return "[" + memberId + "] " + name
                + " | Borrowed Books: " + currentlyBorrowed;
    }
}