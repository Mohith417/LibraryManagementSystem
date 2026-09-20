# 📚 LibraryManagementSystem  

[![Java Version](https://img.shields.io/badge/Java-17%2B-blue.svg)](https://www.oracle.com/java/technologies/javase-downloads.html)  
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)  
[![Stars](https://img.shields.io/github/stars/Mohith417/LibraryManagementSystem)](https://github.com/Mohith417/LibraryManagementSystem/stargazers)  

---  

## Table of Contents  

- [Overview](#overview)  
- [Features](#features)  
- [Architecture & Core Data Structures](#architecture--core-data-structures)  
- [Prerequisites](#prerequisites)  
- [Installation](#installation)  
- [Running the Application](#running-the-application)  
- [Code Highlights](#code-highlights)  
- [Contributing](#contributing)  
- [License](#license)  

---  

## Overview  

**LibraryManagementSystem** is a lightweight, console‑based library management application written in Java. It demonstrates fundamental Data Structures & Algorithms (DSA) concepts while providing core library operations such as:

* Adding/removing books  
* Registering members  
* Borrowing and returning books  
* Keeping a borrowing‑history log using a custom singly linked list  

The project is ideal for students and developers who want a clear, practical example of how classic data structures (HashMap, ArrayList, Queue, and a custom linked list) can be combined to solve real‑world problems.

---  

## Features  

- **Fast look‑ups** – Books and members are stored in `HashMap`s for O(1) retrieval.  
- **Dynamic collections** – `ArrayList` is used for flexible storage of books, members, and pending requests.  
- **Wait‑list handling** – A `Queue` holds members waiting for a book that has no available copies.  
- **Borrowing history** – A custom **Singly Linked List** records each borrow/return action, enabling chronological traversal.  
- **Robust error handling** – Domain‑specific exceptions (`BookNotFoundException`, `MemberNotFoundException`, `BorrowLimitExceededException`).  

---  

## Architecture & Core Data Structures  

| Component | Java Type | Purpose |
|-----------|-----------|---------|
| `Library` | class | Central orchestrator; holds `HashMap<String, Book>` and `HashMap<String, Member>`; provides borrow/return APIs. |
| `Book` | class | Represents a book entity; tracks total and available copies. |
| `Member` | class | Represents a library member; stores borrow limit and current borrowed count. |
| `BorrowingHistory` | class (custom singly linked list) | Stores `BorrowRecord`s in insertion order. |
| `BorrowRecord` | class | Captures details of a single borrow transaction (bookId, memberId, timestamps). |
| `Queue<Member>` | Java `LinkedList` implementation | Wait‑list for a book when all copies are loaned out. |
| `Exception` package | custom exceptions | Clear, domain‑specific error messages. |

---  

## Prerequisites  

| Item | Minimum Version |
|------|-----------------|
| **Java Development Kit (JDK)** | 17+ |
| **Git** | any recent version |
| **OS** | Windows / macOS / Linux (any OS that can run `javac` and `java`) |

---  

## Installation  

```bash
# 1️⃣ Clone the repository
git clone https://github.com/Mohith417/LibraryManagementSystem.git
cd LibraryManagementSystem

# 2️⃣ Compile the source code (no build tool is configured, so we use javac directly)
#    The command below compiles every .java file under src and places the .class files in the out directory.
mkdir -p out
javac -d out src/*.java src/Exception/*.java
```

> **Note** – Because the project does not use Maven, Gradle, or another build system, manual compilation with `javac` is required.

---  

## Running the Application  

```bash
# From the project root:
java -cp out Main
```

The `Main` class contains a simple interactive console menu that lets you:

1. Add books and members  
2. Borrow or return a book  
3. View the borrowing history  

---  

## Code Highlights  

Below is a snippet from **`src/Book.java`** that demonstrates the core data stored for each book and its `toString` implementation:

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
        return "[" + bookId + "] " + title + " by " + author
               + " | Total: " + totalCopies + ", Available: " + availableCopies;
    }
}
```

You will find similar clean implementations for `Member`, `BorrowRecord`, and the custom `BorrowingHistory` linked‑list in the `src` directory.

---  

## Contributing  

Contributions are welcome! Please follow these steps:

1. **Fork** the repository.  
2. **Create a feature branch** (`git checkout -b feature/YourFeature`).  
3. **Make your changes** and ensure the project still compiles with `javac`.  
4. **Write or update tests** (if you add new functionality).  
5. **Commit** with a clear message and **push** to your fork.  
6. Open a **Pull Request** against the `main` branch, describing the changes and why they are beneficial.  

### Coding Style  

- Use **Java 17+** language features only when they add clear value.  
- Follow the existing naming conventions (`CamelCase` for classes, `camelCase` for variables/methods).  
- Keep the code **well‑commented**—especially for new data‑structure implementations.  

---  

## License  

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for full details.  
