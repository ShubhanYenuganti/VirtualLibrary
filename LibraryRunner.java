
/**
 * Runs the library
 *
 * @author andrew and shubhan
 * @version May 6, 2021
 */
public class LibraryRunner
{
    /**
     * Login Object
     */
    public static Login   login   = new Login();
    /**
     * New Library object
     */
    public static Library library = new Library(login);

    /**
     * Main method Adds eight books to library and starts running the program
     *
     * @param args
     *            Command line arguments
     */
    public static void main(String[] args)
    {
        Author george = new Author("George Orwell");
        Author rowling = new Author("JK Rowling");
        Book b1 = new Book("Of Mice and Men", new Author("John Steinbeck"), 1937, 2);
        Book b2 = new Book("Harry Potter and the Sorcerer's Stone", rowling, 1997, 1);
        Book b3 = new Book("Becoming", new Author("Michelle Obama"), 2018, 13);
        Book b4 = new Book("To Kill a Mockingbird", new Author("Harper Lee"), 1960, 20);
        Book b5 = new Book("Nineteen Eighty-Four", george, 1949, 2);
        Book b6 = new Book("Lord of the Flies", new Author("William Golding"), 1953, 15);
        Book b7 = new Book("Animal Farm", george, 1944, 4);
        Book b8 = new Book("Harry Potter and the Chamber of Secrets", rowling, 1998, 2);

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);
        library.addBook(b4);
        library.addBook(b5);
        library.addBook(b6);
        library.addBook(b7);
        library.addBook(b8);
    }
}
