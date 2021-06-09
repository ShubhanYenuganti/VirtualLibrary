// import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Represents an Author
 *
 * @author shubhan
 * @version May 6, 2021
 */
public class Author
{
    private String        name;
    private TreeSet<Book> authorBooks;

    /**
     * Create a new Author object.
     *
     * @param n
     *            Name of Author
     */
    public Author(String n)
    {
        name = n;
        authorBooks = new TreeSet<Book>();
    }


    /**
     * Create a new Author object.
     *
     * @param n
     *            Name of Author
     * @param b
     *            Set of Books Author has at Library
     */
    public Author(String n, TreeSet<Book> b)
    {
        name = n;
        authorBooks = b;
    }


    /**
     * Returns name of an Author
     *
     * @return name of Author
     */
    public String getName()
    {
        return name;
    }


    /**
     * Returns String representation of list of Author's books at Library
     *
     * @return String representation of list of Author's books
     */
    public String getAuthorBooksString()
    {
        String result = "";

        for (Book b : authorBooks)
        {
            result += b.toString() + "\n";
        }

        return result;
    }


    /**
     * Returns list of Author's books at Library
     *
     * @return TreeSet of Author's books at Library
     */
    public TreeSet<Book> getAuthorBooks()
    {
        return authorBooks;
    }


    /**
     * Adds book to Author's list of books
     *
     * @param b
     *            Book to be added
     */
    public void setAuthorBooks(Book b)
    {
        boolean result = authorBooks.add(b);

        if (!result)
        {
            for (Book books : authorBooks)
            {
                if (books.getTitle().equals(b.getTitle()))
                {
                    books.setQuantity(books.getQuantity() + b.getQuantity());
                }
            }
        }
    }


    /**
     * {@inheritDoc}
     */
    public String toString()
    {
        return name + "\n" + getAuthorBooksString();
    }

    // Test
    /*public static void main(String[] args) {

        // Test Author Constructor (1 parameter) & getName()
      Author author = new Author("J.K. Rowling");
      System.out.println("Name: " + author.getName()); // Test Author

      // Constructor (2 parameter) & getAuthorBooksString() Method & getAuthorBooks() & toString method
      TreeSet<Book> b = new TreeSet<Book>(); Author a = new Author("J.R.R. Tolkein", b);
      Book ring = new Book("Lord of the Rings", a,1954, 100);
      Book hobbit = new Book("The Hobbit", a, 1937, 20);
      Book random = new Book("Random", a, 1940, 24);
      b.add(random);
      b.add(ring);
      b.add(hobbit);

      System.out.println("Get Author Books: " + a.getAuthorBooksString());
      System.out.println("Get Author Books: " + a.getAuthorBooks());
      System.out.println("ToString of author: " +a);

      // Test setAuthorBooks
      ArrayList<Book> books = new ArrayList<Book>();
      books.add(new Book("Romeo and Juliet", a, 1940, 1));
      books.add(new Book("Hamlet", a, 1500, 1));
      books.add(new Book("Of Mice and Men", a,1950, 1));
      books.add(new Book("Guide to APCS A", a, 1950, 1));
      books.add(new Book("Guide to APCS A", a, 1950, 1));
      for (Book b1 : books)
      {
          a.setAuthorBooks(b1);
      }
      System.out.println("Set author tester: " + a.getAuthorBooksString());
      }*/
}
