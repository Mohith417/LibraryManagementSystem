# 📚 LibraryManagementSystem  

[![Java Version](https://img.shields.io/badge/Java-17%2B-blue.svg)](https://www.oracle.com/java/technologies/javase-downloads.html)  
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)  
[![Stars](https://img.shields.io/github/stars/yourusername/LibraryManagementSystem)](https://github.com/yourusername/LibraryManagementSystem/stargazers)  

---  

## Table of Contents  

- [Overview](#overview)  
- [Features](#features)  
- [Architecture & Core Data Structures](#architecture--core-data-structures)  
- [Prerequisites](#prerequisites)  
- [Installation & Build](#installation--build)  
- [Running the Application](#running-the-application)  
- [Code Walk‑through (Real Snippets)](#code-walk-through-real-snippets)  
- [Contributing](#contributing)  
- [License](#license)  

---  

## Overview  

**LibraryManagementSystem** is a console‑based Java application that demonstrates the practical use of fundamental Data Structures and Algorithms (DSA) concepts while managing a small library.  

It allows librarians (or any user) to:  

- Store and search books (by title or author).  
- Register members and keep track of how many books each member has borrowed.  
- Issue and return books, automatically handling wait‑lists when all copies are loaned out.  
- Detect overdue books.  
- Record every borrowing event in a **custom singly‑linked list** (`BorrowingHistory`).  

The project was created as a learning exercise for OOP, collections (`HashMap`, `ArrayList`, `Queue`), Java Stream API, and custom exception handling.  

---  

## Features  

| # | Feature | Description |
|---|---------|-------------|
| 1 | **Add Book** | Insert a new book with a unique ID, title, author, and total copies. |
| 2 | **Remove Book** | Delete a book from the catalog (throws `BookNotFoundException` if the ID does not exist). |
| 3 | **Search by Title** | Case‑insensitive search using the Stream API. |
| 4 | **Search by Author** | Case‑insensitive search using the Stream API. |
| 5 | **Issue Book** | Loan an available copy to a member or place the member on a FIFO wait‑list if none are free. |
| 6 | **Return Book** | Process a return, update copy counts, and automatically allocate the book to the next member on the wait‑list. |
| 7 | **Overdue Books** | List all books that have been borrowed longer than the allowed period (default 14 days). |
| 8 | **Borrowing History** | Each member has a personal borrowing history stored in a custom singly‑linked list (`BorrowingHistory`). |
| 9 | **Custom Exceptions** | `BookNotFoundException`, `MemberNotFoundException`, `BorrowLimitExceededException` provide clear error handling. |
|10| **Object‑Oriented Design** | Classes are separated by responsibility (`Book`, `Member`, `Library`, etc.). |

---  

## Architecture & Core Data Structures  

| Component | Purpose | Primary Java Structure |
|-----------|---------|------------------------|
| `Library` | Central manager for books, members, wait‑lists and overall business logic. | `HashMap<String, Book>` for catalog, `HashMap<String, Member>` for members, `Queue<String>` for per‑book wait‑lists |
| `Member` | Holds member details, list of currently borrowed books and a `BorrowingHistory`. | `ArrayList<String>` for borrowed‑book IDs, custom `BorrowingHistory` (singly‑linked list) |
| `BorrowingHistory` | Custom singly‑linked list that stores `BorrowRecord` nodes, newest record first. | Hand‑rolled linked list (no `java.util.LinkedList`) |
| `BorrowRecord` | Immutable node containing book ID, title and issue date. | Simple POJO with a `next` reference |
| `Exception` package | Domain‑specific checked exceptions to make error handling explicit. | N/A |

---  

## Prerequisites  

| Requirement | Version / Details |
|-------------|-------------------|
| **JDK** | Java 17 or newer (the source uses `var`‑style local inference, which requires JDK 10+). |
| **Git** | For cloning the repository. |
| **Optional – Build Tool** | The project compiles with plain `javac`. If you prefer Maven/Gradle you can wrap the source, but it is not required. |

---  

## Installation & Build  

```bash
# 1️⃣ Clone the repository
git clone https://github.com/yourusername/LibraryManagementSystem.git
cd LibraryManagementSystem

# 2️⃣ Compile all source files (the `src` folder contains the code)
javac -d out $(find src -name "*.java")

# 3️⃣ (Optional) Package into a runnable JAR
jar --create --file LibraryManagementSystem.jar -C out .
```

> **Note**: The compiled classes are placed in the `out/` directory. Adjust the `-classpath` if you add external libraries later.

---  

## Running the Application  

```bash
# From the project root
java -cp out Main
```

You will be presented with a simple numbered menu, e.g.:

```
=== Library Management System ===
1. Add Book
2. Remove Book
3. Search Book By Title
4. Search Book By Author
5. Issue Book
6. Return Book
7. Show Overdue Books
8. Exit
Enter choice:
```

Enter the number corresponding to the desired operation and follow the prompts.

---  

## Code Walk‑through (Real Snippets)  

### `Book.java` – Core entity

```java
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
        return "[" + bookId + "] " + title + " by " + author +
               " (Available: " + availableCopies + "/" + totalCopies + ")";
    }
}
```

### `BorrowRecord.java` – Node of the custom linked list

```java
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
    }

    @Override
    public String toString() {
        return "[" + bookId + "] " + bookTitle + " (Issued: " + issueDate + ")";
    }
}
```

### `BorrowingHistory.java` – Hand‑rolled singly linked list

```java
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
        BorrowRecord newRecord = new BorrowRecord(bookId, bookTitle, issueDate);

        // Insert at the front.
        newRecord.next = head;
        head = newRecord;
    }

    // Simple iterator that prints the history.
    public void printHistory() {
        BorrowRecord cur = head;
        while (cur != null) {
            System.out.println(cur);
            cur = cur.next;
        }
    }
}
```

### `Library.java` – Business logic (excerpt)

```java
public class Library {

    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, Member> members = new HashMap<>();
    // Wait‑list per book (FIFO)
    private final Map<String, Queue<String>> waitLists = new HashMap<>();

    // Issue a book, or enqueue the member if none are free.
    public void issueBook(String bookId, String memberId) 
            throws BookNotFoundException, MemberNotFoundException, BorrowLimitExceededException {

        Book book = books.get(bookId);
        if (book == null) throw new BookNotFoundException("Book ID " + bookId + " not found.");

        Member member = members.get(memberId);
        if (member == null) throw new MemberNotFoundException("Member ID " + memberId + " not found.");

        if (member.getBorrowedCount() >= Member.MAX_BORROW_LIMIT)
            throw new BorrowLimitExceededException("Member has reached borrow limit.");

        if (book.availableCopies > 0) {
            book.availableCopies--;
            member.addBorrowedBook(bookId);
            member.getHistory().addRecord(bookId, book.title, LocalDate.now());
            System.out.println("Book issued successfully.");
        } else {
            // enqueue for wait‑list
            waitLists.computeIfAbsent(bookId, k -> new LinkedList<>()).add(memberId);
            System.out.println("No copies available – member added to wait‑list.");
        }
    }
}
```

### `Exception` examples  

```java
package Exception;

// Custom exception used when a book ID is not found.
public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}
```

```java
package Exception;

// Thrown when a member tries to borrow more books than allowed.
public class BorrowLimitExceededException extends Exception {
    public BorrowLimitExceededException(String message) {
        super(message);
    }
}
```

---  

## Contributing  

Contributions are welcome! Follow these steps to propose improvements:  

1. **Fork** the repository.  
2. **Clone** your fork locally.  
3. Create a **feature branch**: `git checkout -b feature/YourFeature`.  
4. Make your changes, add tests if applicable, and **commit** with a clear message.  
5. Push to your fork: `git push origin feature/YourFeature`.  
6. Open a **Pull Request** against the `main` branch.  

Please adhere to the following guidelines:  

- Keep the coding style consistent (use `java -Xlint:all` warnings as a guide).  
- Write Javadoc for any new public classes or methods.  
- Update the README (or add a `docs/` file) if you introduce new functionality.  
- Ensure the project still compiles and runs with `javac`/`java` commands described above.  

---  

## License  

This project is licensed under the **MIT License** – see the [LICENSE](LICENSE) file for details.  

---  

*Happy coding! 🎉*
