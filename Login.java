import java.util.*;

/**
 * Represents somewhere where Users login
 *
 * @author andrew
 * @version May 6, 2021
 */
public class Login
{
    // ~ Fields ................................................................
    private Map<String, User> loggedUsers;

    // ~ Constructors ..........................................................
    /**
     * Creates a Login object
     */
    public Login()
    {
        loggedUsers = new TreeMap<String, User>();
    }


    // ~Public Methods ........................................................
    /**
     * Sets the username and password for a new User and adds it to loggedUsers
     *
     * @param u
     *            Username of new user
     * @param p
     *            Password of new user
     * @return number indicating
     */
    public int setUserPass(String u, String p)
    {

        if (loggedUsers.containsKey(u))
        {
            return -1; // if user name already taken
        }
        else if (!isValid(p))
        {
            return -2; // if password not valid
        }
        else
        {
            loggedUsers.put(u, new User(u, p));
            return 0; // no errors
        }

    }


    /**
     * Checks if password is valid: password is greater than 8 characters and
     * contains numbers and letters
     *
     * @param p
     *            Password to check validity
     * @return true if valid, false otherwise
     */
    public boolean isValid(String p)
    {
        int digitCount = 0;
        int letterCount = 0;
        if (p.length() >= 8)
        {
            for (int i = 0; i < p.length(); i++)
            {
                if (Character.isDigit(p.charAt(i)))
                {
                    digitCount++;
                }
                if (Character.isLetter(p.charAt(i)))
                {
                    letterCount++;
                }
            }
        }

        return digitCount > 0 && letterCount > 0;
    }


    /**
     * Tries to log in username and password
     *
     * @param u
     *            Username
     * @param p
     *            Password
     * @return true if username and password exist in loggedUsers, false
     *         otherwise
     */
    public boolean login(String u, String p)
    {
        Set<String> keys = loggedUsers.keySet();
        if (!keys.contains(u))
        {
            return false;
        }
        if (loggedUsers.get(u).getPassword().equals(p))
        {
            return true;
        }
        return false;
    }


    /**
     * Returns already logged user by name
     *
     * @param u
     *            Username of User
     * @return User object that matches the username
     */
    public User getLoggedUser(String u)
    {
        Set<String> keys = loggedUsers.keySet();
        if (!keys.contains(u))
        {
            return null;
        }
        return loggedUsers.get(u);
    }


    /**
     * Getter for Logged in Users
     *
     * @return Map of logged in users
     */
    public Map<String, User> getLoggedUser()
    {
        return loggedUsers;
    }

   /*public static void main(String [] args)
    {
        Login login = new Login();
        System.out.println(login.setUserPass("Shubhan", "12345678")); // -2
        System.out.println(login.setUserPass("Shubhan", "1234abcd")); //  0;
        System.out.println(login.setUserPass("Shubhan", "1234567a")); // -1
        System.out.println(login.setUserPass("Andrew", "1234567a")); // 0
        System.out.println(login.isValid("1234abc")); // false
        System.out.println(login.isValid("12345678")); // false
        System.out.println(login.isValid("abcdefgh")); // false
        System.out.println(login.isValid("1234abcd")); // true
        System.out.println(login.login("Shubhan", "1234abcd")); // true
        System.out.println(login.login("Shubhan", "1234abc")); // false
        System.out.println(login.login("Shub", "1234abcd")); // false
        System.out.println(login.getLoggedUser("Shubhan").getName()); // Shubhan
        System.out.println(login.getLoggedUser()); // Shubhan & Andrewa
   }*/

}
