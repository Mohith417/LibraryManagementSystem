


📚 Library Management System
A Java-based Library Management System developed as a Data
Structures and Algorithms (DSA) project. It is a menu-driven console
application for managing books, members, borrowing, returns, overdue
books, borrowing history, and waitlists.

🎯 Project Overview
The project demonstrates the practical use of:

Custom Singly Linked List

HashMap

ArrayList

Queue

Java Stream API

Custom Exceptions

Object-Oriented Programming

The main DSA requirement is a custom Singly Linked List for borrowing
history.

✨ Features
Option Feature Description

1 Add Book Add a new book with ID,
title, author, and
copies

2 Remove Book Remove a book from the
catalog

3 Search by Title Case-insensitive title
search using Stream API

4 Search by Author Case-insensitive author
search

5 Issue Book Issue an available book
or place the member in
the waitlist

6 Return Book Return a book and
process the waitlist
when applicable

7 Overdue Books Display active books
borrowed for more than
14 days

8 Display All Books Display the complete
book catalog

9 Display All Members Display registered
members and current
borrowing details

10 View Borrowing History Display a member's
history newest-first

0 Exit Exit the application
🧠 Data Structures Used
1. Custom Singly Linked List
Files: BorrowRecord.java, BorrowingHistory.java

BorrowRecord is the node containing:

Book ID

Book title

Issue date

Reference to the next node

New records are inserted at the head, so borrowing history is
displayed newest to oldest.

HEAD
 ↓
Latest Borrow
 ↓
Previous Borrow
 ↓
Older Borrow
 ↓
NULL
The borrowing history does not use java.util.LinkedList.

2. HashMap
File: Library.java

Book ID    → Book Object
Member ID  → Member Object
Book ID    → Waitlist Queue
Average ID lookup is O(1).

3. ArrayList
Files: Member.java, Library.java

Used for:

Currently borrowed book IDs

Search results

Dynamic collection handling

4. Queue
File: Library.java

Used for book waitlists.

The queue follows FIFO (First In, First Out), so the first waiting
member is considered first.

🌊 Stream API
The project uses Java Stream API in Library.java →
searchByTitle().

The title search uses:

stream()

filter()

forEach()

toLowerCase()

contains()

Matching books are stored in an ArrayList<Book>.

Example:

catalog.values().stream()
        .filter(book -> book.title.toLowerCase()
                .contains(keyword.toLowerCase()))
        .forEach(results::add);
⚠️ Custom Exceptions
Three custom exceptions are implemented:

Exception Purpose

BookNotFoundException Invalid or missing book ID

MemberNotFoundException Invalid or missing member ID

Main.java catches these exceptions and displays readable error
messages.

📏 Important Rules
Maximum Books Per Member
A member can have a maximum of 3 books at a time.

Current Books < 3
        ↓
    Can Issue
Waitlist
If all copies are unavailable, a member can be added to the book's FIFO
waitlist.

Overdue Books
A book is overdue when it has been borrowed for more than 14 days.

The current project uses the fixed date:

Today = 2025-01-15
This fixed date makes overdue testing predictable and follows the
project requirement.

📁 Project Structure
LibraryManagementSystem/
│
├── src/
│   ├── Book.java
│   ├── BorrowRecord.java
│   ├── BorrowingHistory.java
│   ├── Member.java
│   ├── Library.java
│   ├── Main.java
│   ├── BookNotFoundException.java
│   ├── MemberNotFoundException.java
│   └── BorrowLimitExceededException.java
│
├── .gitignore
└── LibraryManagementSystem.iml
File Responsibilities
File Purpose

Book.java Stores book information and copy
availability

BorrowRecord.java Represents a node in the custom
linked list

BorrowingHistory.java Implements the custom singly linked
list

Member.java Stores member details, current
borrowing and history

Library.java Contains core library operations
and data structures

Main.java Console menu, input handling and
exception handling

BookNotFoundException.java Custom book-not-found exception

MemberNotFoundException.java Custom member-not-found exception

BorrowLimitExceededException.java Custom 3-book-limit exception
📦 Sample Data
Books
Book ID Title Author Copies

B001 Data Structures Mark Allen 2
B002 Clean Code Robert Martin 1
B003 Java Programming Herbert Schildt 3

Members
Member ID Name

M001 Alice
M002 Bob
M003 Charlie

▶️ How to Run
Requirements
Java JDK 17.0.6

IntelliJ IDEA or another Java IDE

Git (optional)

IntelliJ IDEA
Open the LibraryManagementSystem project.

Make sure JDK 17.0.6 is selected.

Open src/Main.java.

Run Main.java.

Use the console menu.

Terminal
If the Java files are in the same directory:

javac *.java
java Main
🖥️ Example Usage
Issue a Book
Enter choice: 5

Book ID: B001
Member ID: M001

→ Book issued successfully
Borrowing History
Enter choice: 10

Member ID: M001

Borrowing History:
1. Java Programming
2. Clean Code
3. Data Structures
The newest record appears first because new nodes are inserted at the
head.

Waitlist
Book: Clean Code
Available Copies: 0

Member M002 requests the book.

→ M002 added to waitlist.
When the book is returned, the next waiting member can receive the
available copy.

🔄 Basic System Flow
                ┌──────────────┐
                │    Main.java │
                └──────┬───────┘
                       ↓
                User selects option
                       ↓
                ┌──────────────┐
                │ Library.java │
                └──────┬───────┘
                       ↓
        ┌──────────────┼──────────────┐
        ↓              ↓              ↓
     Books          Members        Waitlists
   HashMap          HashMap          Queue
                       ↓
              Borrowing History
                       ↓
              Custom Linked List
📊 Complexity Overview
Operation Data Structure Complexity

Find Book by ID HashMap O(1) average
Find Member by ID HashMap O(1) average
Add History Record Singly Linked List O(1)
Display History Singly Linked List O(n)
Add to Waitlist Queue O(1)
Remove from Waitlist Queue O(1)
Search Books Stream / collection traversal O(n)

🧪 Testing
The following scenarios are included:

Add a new book

Remove a book

Search by title

Search by author

Search with no matching result

Issue an available book

Return a book

Issue three books to one member

Attempt to issue a fourth book

Handle an invalid book ID

Handle an invalid member ID

Add a member to a waitlist

Return a book with a waiting member

Display borrowing history

Check overdue books

Display all books

Display all members

Tests Already Performed
The project has been run successfully from IntelliJ.

Key cases already tested include:

Title search with no matching result

Invalid book ID handling

The 3-book borrowing limit

Custom exception output

Remaining demonstration cases should be captured as screenshots for the
final report.

🎓 DSA Concepts Demonstrated
Singly Linked List

Nodes and references

Head pointer

HashMap

ArrayList

Queue

FIFO

Searching

Insertion

Deletion

Traversal

Stream API

Exception handling

Time complexity

Object-oriented programming

🚀 Future Improvements
Possible enhancements:

GUI

Database integration

Login and authentication

Book reservation system

Fine calculation

Persistent data storage

Advanced search and filtering

Admin and member roles

👨‍💻 Technologies Used
Java 17.0.6

IntelliJ IDEA

Git / GitHub

Object-Oriented Programming

Data Structures & Algorithms

HashMap

ArrayList

Queue

Custom Singly Linked List

Java Stream API

LocalDate

Custom Exceptions

📌 Project Status
The project was rebuilt as a fresh IntelliJ IDEA project using Java
17.0.6.

Current status:

✅ Java source files implemented

✅ DSA structures implemented

✅ Stream API implemented

✅ Three custom exceptions implemented

✅ Application run successfully

✅ Key test cases performed

✅ Project pushed to GitHub master branch

⏳ Final screenshots pending

⏳ Final printed report pending

📄 License
This project is intended for educational and academic purposes.
