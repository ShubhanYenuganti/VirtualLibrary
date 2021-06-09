import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a Book
 *
 * @author andrew
 * @version May 7, 2021
 */
public class Book
    implements Comparable<Book>
{
    // ~ Fields ................................................................
    private String      title;
    private Author      author;
    private int         year;
    private int         quantity;
    private Queue<User> holdsOnBook;

    // ~ Constructors ..........................................................
    /**
     * Creates a new Book object
     *
     * @param t
     *            Title of Book
     * @param a
     *            Author of Book
     * @param y
     *            Year book was written
     * @param q
     *            Quantity of book
     */
    public Book(String t, Author a, int y, int q)
    {
        title = t;
        author = a;
        year = y;
        quantity = q;
        holdsOnBook = new LinkedList<User>();
    }


    // ~Public Methods ........................................................
    /**
     * Returns title of the book
     *
     * @return title of book
     */
    public String getTitle()
    {
        return title;
    }


    /**
     * Returns author's name as String
     *
     * @return author's name
     */
    public String getAuthorString()
    {
        return author.getName();
    }


    /**
     * Returns author as an Author object
     *
     * @return author as Author object
     */
    public Author getAuthor()
    {
        return author;
    }


    /**
     * Returns year of Book
     *
     * @return year of Book
     */
    public int getYear()
    {
        return year;
    }


    /**
     * Returns quantity of Book
     *
     * @return quantity of Book
     */
    public int getQuantity()
    {
        return quantity;
    }


    /**
     * Sets quantity to specified value
     *
     * @param q
     *            new quantity of Book
     */
    public void setQuantity(int q)
    {
        quantity = q;
    }


    /**
     * Compares two book objects by quantity in order greatest to smallest If
     * quantities are the same, Books are compared alphabetically
     *
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object.
     */
    public int compareTo(Book b)
    {
        Integer bQ = Integer.valueOf(b.getQuantity());
        Integer aQ = Integer.valueOf(quantity);
        if (bQ.compareTo(aQ) == 0)
        {
            return title.compareTo(b.getTitle());
        }
        return bQ.compareTo(aQ);
    }


    /**
     * Returns String representation of Book
     *
     * @return String representation of Book
     */
    public String toString()
    {
        return "Title: " + title + ", Author: " + author.getName() + ", Year: " + year
            + ", Quantity: " + quantity;
    }


    /**
     * Places hold on book
     *
     * @param u
     *            User who wants to place a hold
     */
    public void placeHold(User u)
    {
        holdsOnBook.add(u);
    }


    /**
     * Removes hold on a book
     *
     * @param u
     *            User who wants to remove a hold
     */
    public void removeHold(User u)
    {
        holdsOnBook.remove(u);
    }


    /**
     * Returns queue of Users who has a hold on Book
     *
     * @return Queue of Users who have holds on Book
     */
    public Queue<User> getHoldsOnBook()
    {
        return holdsOnBook;
    }

    // Test
     /*public static void main(String args[])
     {
         // Test Constructor
         Book b1 = new Book("Of Mice and Men", new Author("John Steinbeck"), 1937, 2);

         // Test getAuthor()
         System.out.println(b1.getAuthor());

         // Test getAuthorString()
         System.out.println(b1.getAuthorString());

         // Test getTitle()
         System.out.println(b1.getTitle());

         // Test getQuantity();
         System.out.println(b1.getQuantity());

         // Test getYear();
         System.out.println(b1.getYear());

         // Test setQuantity();
         Book b2 = b1;
         b2.setQuantity(6);
         System.out.println(b2.getQuantity());

         // Test compareTo();
         System.out.println(b2.compareTo(b1)); // 0, same quantity

         //Test toString()
         System.out.println(b1);

         // Test placeHold()
         User u = new User("User", "1234567a");
         User u1 = new User("User1", "1234567a");
         b2.placeHold(u);
         b2.placeHold(u1);

         // Test removeHold()
         b2.removeHold(u);

         // Test getHoldsOnBook()
         System.out.println(b2.getHoldsOnBook().toString()); // One user remaining
     }*/

}
