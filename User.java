import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Represents User of our Library Program
 *
 * @author shubhan
 * @version May 6, 2021
 */
public class User
{
    private String           name;
    private String           pswd;
    private LinkedList<Book> userHolds;
    private LinkedList<Book> userBooks;
    private Queue<String>    mailbox;

    /**
     * Create a new User object.
     *
     * @param n
     *            Name of User
     * @param p
     *            Password of User
     */
    public User(String n, String p)
    {
        name = n;
        pswd = p;
        userHolds = new LinkedList<Book>();
        userBooks = new LinkedList<Book>();
        mailbox = new LinkedList<String>();
    }


    /**
     * Checks out a book. Returns a String that corresponds to whether checkout
     * was successful or not.
     *
     * @param title
     *            Title of book to be checked out by user
     * @return Message indicating if checkout was successful and not
     */
    public String checkout(String title)
    {
        TreeMap<String, Book> books = LibraryRunner.library.getSortTitle();

        if (books.containsKey(title))
        {
            Book b = books.get(title);

            if (userBooks.contains(b))
            {
                return "You already have this book checked out.";
            }

            if (b.getQuantity() == 0)
            {
                return "Not currently available";
            }
            b.setQuantity(b.getQuantity() - 1);
            userBooks.add(b);
            addToMail("Book checkout: " + b.toString());
            return "Checkout succesful!";
        }

        return "Invalid input";
    }


    /**
     * Places a hold for user and if book is available checks the book out.
     *
     * @param title
     *            Title of book to be added.
     * @return Message indicating if hold is placed and whether it has been
     *         checked out if it is currently available
     */
    public String placeHold(String title)
    {
        TreeMap<String, Book> books = LibraryRunner.library.getSortTitle();

        Book b = books.get(title);

        if (b == null)
        {
            return "Invalid input";
        }

        if (userBooks.contains(b))
        {
            return "You already have this book checked out.";
        } else if (userHolds.contains(b))
        {
            return "You already placed a hold on this book.";
        }

        userHolds.add(b);
        addToMail("Book hold: " + b.toString());

        return facilitateHolds(title, false);
    }


    /**
     * Helper for placeHold(). Facilitates holds on books.
     *
     * @param title
     *            String of book
     * @param isReturn
     *            Boolean of whether facilitation occurs when hold is being
     *            placed or when a book is being returned.
     * @return String message of whether hold is checked out or simply placed.
     */
    private String facilitateHolds(String title, boolean isReturn)
    {
        TreeMap<String, Book> books = LibraryRunner.library.getSortTitle();
        Book b = books.get(title);
        Queue<User> holds = null;

        if (isReturn)
        {
            holds = b.getHoldsOnBook();
        }
        else
        {
            holds = placeHoldHelper(title);
        }

        Iterator<User> iterator = holds.iterator();

        while (b.getQuantity() > 0 && iterator.hasNext())
        {
            User u = iterator.next();
            u.checkout(title);
            u.removeHold(b);
            b.removeHold(u);
        }

        if (userHolds.contains(b))
        {
            return "Your hold has been placed";
        }

        return "Your hold has been placed and your book has been checked out";
    }


    /**
     * Helper for placeHold(). Finds book and adds current user to Queue of
     * Users who placed a hold on a particular book.
     *
     * @param title
     *            Book to find and add user to queue of holds on that book
     * @return Queue of users who have a hold on book indicated by title
     */
    private Queue<User> placeHoldHelper(String title)
    {
        TreeMap<String, Book> books = LibraryRunner.library.getSortTitle();
        Book b = null;

        if (books.containsKey(title))
        {
            b = books.get(title);
            b.placeHold(this);
            return b.getHoldsOnBook();
        }

        return null;
    }


    /**
     * Removes a hold from User's list of holds
     *
     * @param b
     *            Book to be removed
     */
    public void removeHold(Book b)
    {
        userHolds.remove(b);
    }


    /**
     * Returns a book back to the library.
     *
     * @param title
     *            Title of book to be returned by User
     * @return Message indicating whether return process was completed or if
     *         there was an input error.
     */
    public String returnBook(String title)
    {
        TreeMap<String, Book> books = LibraryRunner.library.getSortTitle();
        Book b = books.get(title);

        if (books.containsKey(title))
        {
            if (!userBooks.contains(b))
            {
                return "You don't have this book.";
            }
            userBooks.remove(b);
            addToMail("Book returned: " + b.toString());
            b.setQuantity(b.getQuantity() + 1);
            facilitateHolds(title, true);
            return "Return successful";
        }

        return "Invalid input";
    }


    /**
     * Returns String representation of User's mailbox of messages.
     *
     * @return String representing User's mailbox
     */
    public String viewMailbox()
    {
        String s = "";

        while (!mailbox.isEmpty())
        {
            s += mailbox.remove() + "\n";
        }

        return s;
    }


    /**
     * Add's a String message to User's mailbox
     *
     * @param s
     *            Message to be added to User's mailbox
     */
    public void addToMail(String s)
    {
        mailbox.add(s);
    }


    /**
     * Returns name of User
     *
     * @return name of User
     */
    public String getName()
    {
        return name;
    }


    /**
     * Returns password of User
     *
     * @return password of User
     */
    public String getPassword()
    {
        return pswd;
    }


    /**
     * Returns list of books currently checked out by User
     *
     * @return LinkedList of Books currently checked out by User
     */
    public LinkedList<Book> getBooks()
    {
        return userBooks;
    }


    /**
     * Returns list of holds currently placed by User.
     *
     * @return LinkedList of Holds currently placed by User
     */
    public LinkedList<Book> getHolds()
    {
        return userHolds;
    }


    /**
     * Returns String representation of books checked out by User
     *
     * @return String of books checked out by User
     */
    public String viewBooks()
    {
        String result = "";
        for (Book b : userBooks)
        {
            result += b.toString() + "\n";
        }
        return result;
    }


    /**
     * Returns String representation of books currently on hold by User
     *
     * @return String of books currently on hold by User
     */
    public String viewHolds()
    {
        String result = "";
        for (Book b : userHolds)
        {
            result += b.toString() + "\n";
        }
        return result;
    }

    //Test
    /*public static void main(String [] args)
    {
        User u = new User("Shubhan", "1234");

        // Test Add to Mail & View Mail
        u.addToMail("HELLO");
        u.addToMail("WELCOME");
        System.out.println("Before: " + "\n" + u.viewMailbox());
        System.out.println("After: " + u.viewMailbox());
        System.out.println();

        // Test Checkout & viewBooks()
        LibraryRunner.library.addBook( new Book("Harry Potter and the Sorcerer's Stone", new Author("J.K Rowling"), 1997, 1));
        LibraryRunner.library.addBook( new Book("Harry Potter and the Chamber of Secrets", new Author("J.K Rowling"), 1997, 0));
        System.out.println(u.checkout("Harry Potter and the Sorcerer's"));
        System.out.println(u.checkout("Harry Potter and the Sorcerer's Stone"));
        System.out.println(u.checkout("Harry Potter and the Sorcerer's Stone"));
        System.out.println(u.checkout("Harry Potter and the Chamber of Secrets"));
        System.out.println(u.viewBooks());

        // Test placeHold() & viewHolds()
        User u1 = new User("Andrew", "123");
        System.out.println();
        System.out.println(u1.placeHold("Harry Potter and the Sorcerer's"));
        System.out.println(u1.placeHold("Harry Potter and the Sorcerer's Stone"));
        System.out.println(u1.placeHold("Harry Potter and the Sorcerer's Stone"));
        System.out.println(u.placeHold("Harry Potter and the Sorcerer's Stone"));
        LibraryRunner.library.addBook(new Book("Becoming", new Author("Michelle Obama"), 2018, 13));
        System.out.println(u.placeHold("Becoming"));
        System.out.println("User Holds: " + u.viewHolds());
        System.out.println("User Books: " + u.viewBooks());
        System.out.println("User 1 Holds: " + u1.viewHolds());
        System.out.println("User 1 Books: " + u1.viewBooks());

        // Test returnBook
        System.out.println();
        System.out.println(u1.returnBook("Random Book"));
        System.out.println(u1.returnBook("Becoming"));
        System.out.println(u.returnBook("Harry Potter and the Sorcerer's Stone"));
        System.out.println(u.returnBook("Becoming"));
        System.out.println("User 1 Books: " + u1.viewBooks());
        System.out.println("User 1 Holds: " + u1.viewHolds());
        System.out.println("User Books: " + u.viewBooks());
        System.out.println("User Holds: " + u.viewHolds());

        // Test getName, getPassword, getBooks(), getHolds()
        System.out.println();
        System.out.println(u.getName());
        System.out.println(u.getPassword());
        System.out.println("User 1 Books: " + u1.getBooks());
        u.placeHold("Harry Potter and the Sorcerer's Stone");
        System.out.println("User Holds : " + u.getHolds());
    }*/
}
