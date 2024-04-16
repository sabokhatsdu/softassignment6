import java.util.HashMap;
import java.util.Map;

interface LibraryFacade {
    String borrowBook(String bookTitle, int userId);
    String returnBook(String bookTitle, int userId);
    String searchBook(String searchTerm);
    boolean checkAvailability(String bookTitle);
}

class BookInventorySystem {
    private Map<String, Boolean> books;

    public BookInventorySystem() {
        this.books = new HashMap<>();
    }

    public void addBook(String bookTitle) {
        books.put(bookTitle, true);
    }

    public void removeBook(String bookTitle) {
        books.remove(bookTitle);
    }

    public void updateAvailability(String bookTitle, boolean available) {
        books.put(bookTitle, available);
    }

    public boolean checkAvailability(String bookTitle) {
        return books.getOrDefault(bookTitle, false);
    }

    public String searchBook(String searchTerm) {
        StringBuilder result = new StringBuilder();
        for (String title : books.keySet()) {
            if (title.contains(searchTerm)) {
                result.append(title).append("\n");
            }
        }
        return result.toString();
    }
}

class UserManagementSystem {
    private Map<Integer, String> users;

    public UserManagementSystem() {
        this.users = new HashMap<>();
    }

    public void addUser(int userId, String name) {
        users.put(userId, name);
    }

    public void removeUser(int userId) {
        users.remove(userId);
    }

    public String getUser(int userId) {
        return users.getOrDefault(userId, "User not found");
    }
}

class LibraryFacadeImpl implements LibraryFacade {
    private BookInventorySystem bookInventory;
    private UserManagementSystem userManagement;

    public LibraryFacadeImpl(BookInventorySystem bookInventory, UserManagementSystem userManagement) {
        this.bookInventory = bookInventory;
        this.userManagement = userManagement;
    }

    @Override
    public String borrowBook(String bookTitle, int userId) {
        if (bookInventory.checkAvailability(bookTitle)) {
            bookInventory.updateAvailability(bookTitle, false);
            return "Book '" + bookTitle + "' successfully borrowed by user " + userManagement.getUser(userId);
        } else {
            return "Book '" + bookTitle + "' is not available.";
        }
    }

    @Override
    public String returnBook(String bookTitle, int userId) {
        bookInventory.updateAvailability(bookTitle, true);
        return "Book '" + bookTitle + "' successfully returned by user " + userManagement.getUser(userId);
    }

    @Override
    public String searchBook(String searchTerm) {
        return bookInventory.searchBook(searchTerm);
    }

    @Override
    public boolean checkAvailability(String bookTitle) {
        return bookInventory.checkAvailability(bookTitle);
    }
}

public class assignment6pt1 {
    public static void main(String[] args) {
        BookInventorySystem bookInventory = new BookInventorySystem();
        bookInventory.addBook("Harry Potter");
        UserManagementSystem userManagement = new UserManagementSystem();
        userManagement.addUser(1, "John Doe");

        LibraryFacade libraryFacade = new LibraryFacadeImpl(bookInventory, userManagement);

        System.out.println(libraryFacade.borrowBook("Harry Potter", 1));
        System.out.println(libraryFacade.returnBook("Harry Potter", 1));
        System.out.println(libraryFacade.searchBook("Harry Potter"));
        System.out.println(libraryFacade.checkAvailability("Harry Potter"));
    }
}
