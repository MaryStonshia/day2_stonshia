class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;  // By default, book is available
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println(title + " has been borrowed.");
        } else {
            System.out.println(title + " is currently not available.");
        }
    }

    public void returnBook() {
        isAvailable = true;
        System.out.println(title + " has been returned.");
    }

    public void displayInfo() {
        System.out.println("Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: " + isAvailable);
    }
}

// Subclass for Fiction books
class FictionBook extends Book {
    private String genre;

    public FictionBook(String title, String author, String isbn, String genre) {
        super(title, author, isbn);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Genre: " + genre);
    }
}

// Subclass for Non-Fiction books
class NonFictionBook extends Book {
    private String subject;

    public NonFictionBook(String title, String author, String isbn, String subject) {
        super(title, author, isbn);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Subject: " + subject);
    }
}

// Main class to test the system
public class LibraryManagementSystem {
    public static void main(String[] args) {
        FictionBook fiction = new FictionBook("The Hobbit", "J.R.R. Tolkien", "12345", "Fantasy");
        NonFictionBook nonFiction = new NonFictionBook("Sapiens", "Yuval Noah Harari", "67890", "History");

        fiction.displayInfo();
        fiction.borrowBook();
        fiction.borrowBook();  // Trying to borrow again to test availability
        fiction.returnBook();

        System.out.println();

        nonFiction.displayInfo();
        nonFiction.borrowBook();
        nonFiction.returnBook();
    }
}