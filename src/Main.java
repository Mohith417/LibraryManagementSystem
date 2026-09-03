import java.util.Scanner;

// Main class starts the Library Management System.
public class Main {

    public static void main(String[] args) {

        // Scanner is used to take input from the user.
        Scanner scanner = new Scanner(System.in);

        // Create the library object.
        Library library = new Library();

        // Add some sample books.
        library.addBook(
                new Book("B001", "Data Structures", "Mark Allen", 2)
        );

        library.addBook(
                new Book("B002", "Clean Code", "Robert Martin", 1)
        );

        library.addBook(
                new Book("B003", "Java Programming", "Herbert Schildt", 3)
        );

        // Add some sample members.
        library.addMember(
                new Member("M001", "Alice")
        );

        library.addMember(
                new Member("M002", "Bob")
        );

        library.addMember(
                new Member("M003", "Charlie")
        );

        int choice;

        // Keep showing the menu until the user chooses 0.
        do {

            System.out.println("\n=================================");
            System.out.println("     LIBRARY MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Search Book by Author");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. View Overdue Books");
            System.out.println("8. Display All Books");
            System.out.println("9. Display All Members");
            System.out.println("10. View Borrowing History");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {

                    // =========================================
                    // ADD BOOK
                    // =========================================
                    case 1:

                        System.out.print("Enter Book ID: ");
                        String bookId = scanner.nextLine();

                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine();

                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine();

                        System.out.print("Enter Total Copies: ");
                        int copies = scanner.nextInt();
                        scanner.nextLine();

                        library.addBook(
                                new Book(
                                        bookId,
                                        title,
                                        author,
                                        copies
                                )
                        );

                        break;

                    // =========================================
                    // REMOVE BOOK
                    // =========================================
                    case 2:

                        System.out.print("Enter Book ID: ");
                        bookId = scanner.nextLine();

                        library.removeBook(bookId);

                        break;

                    // =========================================
                    // SEARCH BY TITLE
                    // =========================================
                    case 3:

                        System.out.print("Enter title keyword: ");
                        String titleKeyword = scanner.nextLine();

                        // Stream API is executed inside
                        // Library.searchByTitle().
                        library.searchByTitle(titleKeyword);

                        break;

                    // =========================================
                    // SEARCH BY AUTHOR
                    // =========================================
                    case 4:

                        System.out.print("Enter author keyword: ");
                        String authorKeyword = scanner.nextLine();

                        library.searchByAuthor(authorKeyword);

                        break;

                    // =========================================
                    // ISSUE BOOK
                    // =========================================
                    case 5:

                        System.out.print("Enter Book ID: ");
                        bookId = scanner.nextLine();

                        System.out.print("Enter Member ID: ");
                        String memberId = scanner.nextLine();

                        library.issueBook(bookId, memberId);

                        break;

                    // =========================================
                    // RETURN BOOK
                    // =========================================
                    case 6:

                        System.out.print("Enter Book ID: ");
                        bookId = scanner.nextLine();

                        System.out.print("Enter Member ID: ");
                        memberId = scanner.nextLine();

                        library.returnBook(bookId, memberId);

                        break;

                    // =========================================
                    // OVERDUE BOOKS
                    // =========================================
                    case 7:

                        library.viewOverdueBooks();

                        break;

                    // =========================================
                    // DISPLAY BOOKS
                    // =========================================
                    case 8:

                        library.displayBooks();

                        break;

                    // =========================================
                    // DISPLAY MEMBERS
                    // =========================================
                    case 9:

                        library.displayMembers();

                        break;

                    // =========================================
                    // BORROWING HISTORY
                    // =========================================
                    case 10:

                        System.out.print("Enter Member ID: ");
                        memberId = scanner.nextLine();

                        library.showBorrowingHistory(memberId);

                        break;

                    // =========================================
                    // EXIT
                    // =========================================
                    case 0:

                        System.out.println(
                                "Thank you for using the Library Management System!"
                        );

                        break;

                    default:

                        System.out.println(
                                "Invalid choice. Please try again."
                        );
                }

            } catch (BookNotFoundException |
                     MemberNotFoundException |
                     BorrowLimitExceededException e) {

                // Handle our three custom exceptions.
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 0);

        // Close the scanner.
        scanner.close();
    }
}