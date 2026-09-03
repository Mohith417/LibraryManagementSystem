// Custom exception used when a member ID is not found.
public class MemberNotFoundException extends Exception {

    // Constructor receives the error message.
    public MemberNotFoundException(String message) {
        super(message);
    }
}