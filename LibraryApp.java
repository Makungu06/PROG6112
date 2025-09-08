package section.b;

import java.util.Scanner;

public class LibraryApp {

    // ======================== Book Class (Parent) ========================
    static class Book {
        private String title;
        private String author;
        private boolean isBorrowed;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.isBorrowed = false;
        }

        public String getTitle() {
            return title;
        }

        public boolean isBorrowed() {
            return isBorrowed;
        }

        public void borrow() {
            isBorrowed = true;
        }

        public void returnBook() {
            isBorrowed = false;
        }

        public String getDetails() {
            return title + " by " + author + " | Status: " + (isBorrowed ? "Borrowed" : "Available");
        }
    }

    // ======================== EBook (Child) ========================
    static class EBook extends Book {
        private double fileSizeMB;

        public EBook(String title, String author, double fileSizeMB) {
            super(title, author);
            this.fileSizeMB = fileSizeMB;
        }

        @Override
        public String getDetails() {
            return super.getDetails() + " | EBook Size: " + fileSizeMB + "MB";
        }
    }

    // ======================== PrintedBook (Child) ========================
    static class PrintedBook extends Book {
        private int pageCount;

        public PrintedBook(String title, String author, int pageCount) {
            super(title, author);
            this.pageCount = pageCount;
        }

        @Override
        public String getDetails() {
            return super.getDetails() + " | Pages: " + pageCount;
        }
    }

    // ======================== Library Class ========================
    static class Library {
        private Book[] books;
        private int count;

        public Library(int size) {
            books = new Book[size];
            count = 0;
        }

        public void addBook(Book book) {
            if (count < books.length) {
                books[count++] = book;
            } else {
                System.out.println("Library is full!");
            }
        }

        public void borrowBook(String title) {
            for (Book b : books) {
                if (b != null && b.getTitle().equalsIgnoreCase(title)) {
                    if (!b.isBorrowed()) {
                        b.borrow();
                        System.out.println("Book borrowed successfully.");
                    } else {
                        System.out.println("Book is already borrowed.");
                    }
                    return;
                }
            }
            System.out.println("Book not found.");
        }

        public void returnBook(String title) {
            for (Book b : books) {
                if (b != null && b.getTitle().equalsIgnoreCase(title)) {
                    if (b.isBorrowed()) {
                        b.returnBook();
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Book was not borrowed.");
                    }
                    return;
                }
            }
            System.out.println("Book not found.");
        }

        public void printReport() {
            System.out.println("-------- Library Report --------");
            for (Book b : books) {
                if (b != null) {
                    System.out.println(b.getDetails());
                }
            }
            System.out.println("--------------------------------");
        }
    }

    // ======================== Unit Test Simulation ========================
    static void runTests() {
        Library lib = new Library(3);
        Book testBook = new PrintedBook("Test Book", "Tester", 100);
        lib.addBook(testBook);

        assert !testBook.isBorrowed() : "Should not be borrowed initially";

        lib.borrowBook("Test Book");
        assert testBook.isBorrowed() : "Should be borrowed after borrowing";

        lib.returnBook("Test Book");
        assert !testBook.isBorrowed() : "Should not be borrowed after return";

        System.out.println("✅ All unit tests passed!");
    }

    // ======================== Main Method ========================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library(10);

        // Add some books
        library.addBook(new PrintedBook("Java Basics", "Alice", 200));
        library.addBook(new EBook("Advanced Java", "Bob", 1.5));

        // Run unit test
        runTests();

        // Console Menu
        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. Show Report");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt(); sc.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter book title to borrow: ");
                    String borrowTitle = sc.nextLine();
                    library.borrowBook(borrowTitle);
                }
                case 2 -> {
                    System.out.print("Enter book title to return: ");
                    String returnTitle = sc.nextLine();
                    library.returnBook(returnTitle);
                }
                case 3 -> library.printReport();
                case 4 -> {
                    System.out.println("Exiting application.");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
