// import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

/**
 * Represents a Library
 *
 * @author shubhan
 * @version May 6, 2021
 */
public class Library
{
    private TreeMap<String, Book>          sortTitle;
    private TreeMap<String, TreeSet<Book>> sortAuthor;

    /**
     * Create a new Library object.
     *
     * @param log
     *            Login object used
     */
    public Library(Login log)
    {
        sortTitle = new TreeMap<String, Book>();
        sortAuthor = new TreeMap<>();
        @SuppressWarnings("unused")
        LoginWindow w = new LoginWindow(log);
    }


    /**
     * Create a new Library object.
     *
     * @param b
     *            Collection of Books in a Library object
     */
    public Library(Collection<Book> b)
    {
        sortTitle = new TreeMap<String, Book>();
        sortAuthor = new TreeMap<>();

        for (Book c : b)
        {
            addBook(c);
        }
    }


    /**
     * Adds a book to Library.
     *
     * @param b
     *            Book to be added
     */
    public void addBook(Book b)
    {

        if (sortTitle.containsKey(b.getTitle()))
        {
            Book book = sortTitle.get(b.getTitle());
            book.setQuantity(book.getQuantity() + b.getQuantity());
        }
        else
        {
            sortTitle.put(b.getTitle(), b);
            b.getAuthor().setAuthorBooks(b);
            sortAuthor.put(b.getAuthorString(), b.getAuthor().getAuthorBooks());
        }

    }


    /**
     * Returns toString() representation of book of a particular title
     *
     * @param title
     *            Title of book to be returned
     * @return toString representation of Book with title indicated by parameter
     */
    public String getByTitle(String title)
    {
        if (sortTitle.get(title).getQuantity() >= 0)
        {
            return sortTitle.get(title).toString();
        }
        return "";
    }


    /**
     * Returns String of Books of a particular Author
     *
     * @param author
     *            Name of Author
     * @return String of Books of an Author indicated by parameter
     */
    public String getByAuthor(String author)
    {
        TreeSet<Book> list = sortAuthor.get(author);
        String result = "";

        for (Book b : list)
        {
            if (b.getQuantity() >= 0)
            {
                result += b.toString() + "\r";
            }
        }
        return result;
    }


    /**
     * Returns String representation of all Books of Library ordered
     * alphabetically by Author's name
     *
     * @return String representation of books ordered by Author's name
     */
    public String getByAuthor()
    {
        String result = "";

        for (Entry<String, TreeSet<Book>> entry : sortAuthor.entrySet())
        {
            String key = entry.getKey();
            result += getByAuthor(key) + "\n";
        }

        return result;
    }


    /**
     * Returns String representation of all Books of Library ordered
     * alphabetically by Title of Book
     *
     * @return String representation of books ordered by Title
     */
    public String getByTitle()
    {
        String result = "";

        for (Entry<String, Book> entry : sortTitle.entrySet())
        {
            String key = entry.getKey();
            result += getByTitle(key) + "\n";
        }

        return result;
    }


    /**
     * Returns map of books sorted by Title
     *
     * @return TreeMap of Books sorted by Title
     */
    public TreeMap<String, Book> getSortTitle()
    {
        return sortTitle;
    }


    /**
     * Returns map of books sorted by Author's name
     *
     * @return TreeMap of books sorted by Author's name
     */
    public TreeMap<String, TreeSet<Book>> getSortAuthor()
    {
        return sortAuthor;
    }


    /*public static void main(String args[])
    {
        // Test Constructor w/one parameter & getByTitle() & getByAuthor() no parameters
        ArrayList<Book> bList = new ArrayList<Book>();
        bList.add(new Book("Of Mice and Men", new Author("John Steinbeck"), 1937, 2));
        Author r = new Author("J.K Rowling");
        bList.add(new Book("Harry Potter and the Sorcerer's Stone", r, 1997, 1));
        bList.add(new Book("Becoming", new Author("Michelle Obama"), 2018, 13));

        Library l = new Library(bList);
        System.out.println("By Title: " + "\n" +  l.getByTitle());
        System.out.println("By Author: " + "\n"+ l.getByAuthor());

        // Test Constructor w/login param
        Library l1 = new Library(new Login());
        System.out.println("By Title: " + "\n" +  l1.getByTitle());
        System.out.println("By Author: " + "\n" + l1.getByAuthor());

        // Test addBook()
        l.addBook(new Book("Of Mice and Men", new Author("John Steinbeck"), 1937, 2));
        System.out.println("By Title: " + "\n" +  l.getByTitle());
        System.out.println("By Author: " + "\n"+ l.getByAuthor());
       l.addBook(new Book("Animal Farm", new Author("George"), 1944, 4));
       l.addBook(new Book("Harry Potter and the Chamber of Secrets", r, 1998, 2));
       System.out.println("By Title: " + "\n" +  l.getByTitle());
       System.out.println("By Author: " + "\n"+ l.getByAuthor());


       // Test getByTitle(String)
       System.out.println("Get By Title: " + l.getByTitle("Of Mice and Men"));

       // Test getByAuthor(String)
       System.out.println("Get By Author: " + l.getByAuthor("J.K Rowling"));
    }*/

}
