import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.util.TreeSet;
import java.util.Map.Entry;
import javax.swing.*;

/**
 * GUI representation of the user interface
 *
 * @author shubhan
 * @version May 26, 2021
 */
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author shubhan
 *  @version May 27, 2021
 */
public class UserWindow
    extends JFrame
    implements ActionListener
{

    /**
     * UserWindow frame
     */
    private JFrame             frame;
    /**
     * String label for UserWindow
     */
    private String             label;
    /**
     * Name of user
     */
    private String             name;
    /**
     * Panel for UserWindow frame
     */
    private JPanel             panel;
    /**
     * Menu bar of UserWindow jFrame
     */
    private JMenuBar           menuBar;
    /**
     * Menu areas of menu bar
     */
    private JMenu              books, returns, profile;
    /**
     * Menu Items of the respective menu areas
     */
    private JMenuItem          bookTitle, author, placeReturn, viewMailbox, viewHolds, viewCheckout;
    /**
     * GridBagConstraints for GridBag Layout
     */
    private GridBagConstraints gbcProfile = new GridBagConstraints();

    /**
     * Create a new UserWindow object.
     *
     * @param name
     *            Name of User
     */
    public UserWindow(String name)
    {
        this.name = name;
        label = "Virtual Library: " + name;
        OpenUserWindow();
    }


    /**
     * Opens User Window Frame
     */
    public void OpenUserWindow()
    {
        frame = new JFrame(label);
        gbcProfile.insets = new Insets(5, 5, 5, 5);
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(300, 300);

        JLabel username =
            new JLabel("Username: " + LoginWindow.login.getLoggedUser(name).getName());
        gbcProfile.gridx = 0;
        gbcProfile.gridy = 0;
        panel.add(username, gbcProfile);

        JLabel pswd =
            new JLabel("Password: " + LoginWindow.login.getLoggedUser(name).getPassword());
        gbcProfile.gridx = 0;
        gbcProfile.gridy = 1;
        panel.add(pswd, gbcProfile);

        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        books = new JMenu("Books");
        menuBar.add(books);

        returns = new JMenu("Return");
        menuBar.add(returns);

        profile = new JMenu("Profile");
        menuBar.add(profile);

        placeReturn = new JMenuItem("Return a Book");
        returns.add(placeReturn);

        placeReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev)
            {
                ReturnBooks r = new ReturnBooks();
                r.openFrame();
            }
        });

        bookTitle = new JMenuItem("View By Title");
        books.add(bookTitle);

        bookTitle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev)
            {
                BookByTitle t = new BookByTitle();
                t.openFrame();
            }
        });

        author = new JMenuItem("View by Author");
        books.add(author);

        author.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev)
            {
                BookByAuthor a = new BookByAuthor();
                a.openFrame();
            }
        });

        viewMailbox = new JMenuItem("View Mailbox");
        profile.add(viewMailbox);

        viewMailbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev)
            {
                ViewMail m = new ViewMail();
                m.openFrame();
            }
        });

        viewCheckout = new JMenuItem("View Checkout");
        profile.add(viewCheckout);
        viewCheckout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev)
            {
                ViewCheckouts c = new ViewCheckouts();
                c.openFrame();
            }
        });

        viewHolds = new JMenuItem("View Holds");
        profile.add(viewHolds);
        viewHolds.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev)
            {
                ViewHolds h = new ViewHolds();
                h.openFrame();
            }
        });

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // None
    }

    /**
     * GUI representation of list of books by title
     *
     * @author shubhan
     * @version May 27, 2021
     */
    private class BookByTitle
        implements ActionListener
    {
        /**
         * Frame of BookByTitle window
         */
        private JFrame                   frame1;
        /**
         * List of books sorted by title
         */
        private JList<String>            list;
        /**
         * Model for list
         */
        private DefaultListModel<String> model;

        /**
         * SplitPane for BookByTitle frame
         */
        private JSplitPane               splitPanelTitle;
        /**
         * Checkout button
         */
        private JButton                  checkoutButton;
        /**
         * Place hold button
         */
        private JButton                  placeHold;
        /**
         * Title label
         */
        private JLabel                   title;
        /**
         * Success message
         */
        private JLabel                   success;

        /**
         * Right panel of UserWindow with checkout/place hold button
         */
        private JPanel                   panelBookList;
        /**
         * String of book title selected by user on list
         */
        private String                   selectedValue;

        /**
         * GridBagConstraints for GridBagLayout of panelBookLista
         */
        private GridBagConstraints       gbcBookTitle = new GridBagConstraints();

        /**
         * Opens Book by title Frame
         */
        public void openFrame()
        {
            frame1 = new JFrame("Books by Title" + " : " + name);
            frame1.setSize(1000, 1000);

            list = new JList<String>();
            model = new DefaultListModel<String>();

            for (Entry<String, Book> entry : LibraryRunner.library.getSortTitle().entrySet())
            {
                model.addElement(entry.getKey());
            }

            panelBookList = new JPanel();
            panelBookList.setLayout(new GridBagLayout());

            splitPanelTitle = new JSplitPane();

            title = new JLabel();
            gbcBookTitle.gridx = 0;
            gbcBookTitle.gridy = 0;
            panelBookList.add(title, gbcBookTitle);

            success = new JLabel();
            gbcBookTitle.gridx = 0;
            gbcBookTitle.gridy = 3;
            panelBookList.add(success, gbcBookTitle);

            list.setModel(model);
            splitPanelTitle.setLeftComponent(new JScrollPane(list));
            splitPanelTitle.setRightComponent(panelBookList);

            list.getSelectionModel().addListSelectionListener(e -> {
                if (e.getValueIsAdjusting())
                {
                    selectedValue = list.getSelectedValue();
                    title.setText(LibraryRunner.library.getByTitle(list.getSelectedValue()));
                }
            });

            checkoutButton = new JButton("Checkout Book");
            gbcBookTitle.gridx = 0;
            gbcBookTitle.gridy = 1;
            panelBookList.add(checkoutButton, gbcBookTitle);
            checkoutButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    if (selectedValue != null)
                    {
                        String s = LoginWindow.login.getLoggedUser(name).checkout(selectedValue);

                        success.setText(s);
                        Timer timer = new Timer(2000, i -> success.setText(""));
                        timer.setRepeats(false);
                        timer.start();

                    }
                    else
                    {
                        success.setText("Select a book from the list prior to checking it out");
                    }
                }
            });

            placeHold = new JButton("Place Hold");
            gbcBookTitle.gridx = 0;
            gbcBookTitle.gridy = 2;
            panelBookList.add(placeHold, gbcBookTitle);
            placeHold.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    if (selectedValue != null)
                    {
                        String s = LoginWindow.login.getLoggedUser(name).placeHold(selectedValue);
                        success.setText(s);

                        success.setText(s);
                        Timer timer1 = new Timer(2000, i -> success.setText(""));
                        timer1.setRepeats(false);
                        timer1.start();
                    }
                    else
                    {
                        success.setText("Select a book from the list prior to placing a hold");
                    }
                }
            });

            frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame1.add(splitPanelTitle);
            frame1.pack();
            frame1.setLocationRelativeTo(null);
            frame1.setVisible(true);
        }


        /**
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // None
        }

    }


    /**
     * Book By Author GUI representation
     *
     * @author shubhan
     * @version May 27, 2021
     */
    private class BookByAuthor
        implements ActionListener
    {
        /**
         * Frame for book by author frame
         */
        private JFrame                   frame1;
        /**
         * List of books sorted by author
         */
        private JList<String>            list;
        /**
         * Model helper for list
         */
        private DefaultListModel<String> model;
        /**
         * Split panel for book by author frame
         */
        private JSplitPane               splitPanelAuthor;

        /**
         * Checkout button for book by author frame
         */
        private JButton                  checkoutButton;
        /**
         * Place hold button for book by author frame
         */
        private JButton                  placeHold;
        /**
         * JLabel for title of book selected by user
         */
        private JLabel                   title;
        /**
         * Success message
         */
        private JLabel                   success;

        /**
         * Panel for right side of author by title
         */
        private JPanel                   panelAuthor;

        /**
         * Title of book selected by user
         */
        private String                   selectedValue;

        /**
         * GridBagConstraints for GridLayout of panelAuthor
         */
        private GridBagConstraints       gbcBookTitle = new GridBagConstraints();

        /**
         * Opens Book by Author frame
         */
        public void openFrame()
        {
            frame1 = new JFrame("Books by Author" + " : " + name);
            frame1.setSize(500, 500);

            list = new JList<String>();
            model = new DefaultListModel<String>();

            for (Entry<String, TreeSet<Book>> entry : LibraryRunner.library.getSortAuthor()
                .entrySet())
            {
                for (Book b : entry.getValue())
                {
                    model.addElement(entry.getKey() + ": " + b.getTitle());
                }
            }

            panelAuthor = new JPanel();
            panelAuthor.setLayout(new GridBagLayout());

            splitPanelAuthor = new JSplitPane();

            title = new JLabel();
            gbcBookTitle.gridx = 0;
            gbcBookTitle.gridy = 0;
            panelAuthor.add(title, gbcBookTitle);

            success = new JLabel();
            gbcBookTitle.gridx = 0;
            gbcBookTitle.gridy = 3;
            panelAuthor.add(success, gbcBookTitle);

            list.setModel(model);
            splitPanelAuthor.setLeftComponent(new JScrollPane(list));
            splitPanelAuthor.setRightComponent(panelAuthor);

            list.getSelectionModel().addListSelectionListener(e -> {
                if (e.getValueIsAdjusting())
                {
                    selectedValue = list.getSelectedValue();
                    String[] s = selectedValue.split(" ");
                    String result = "";

                    for (int i = 2; i < s.length - 1; i++)
                    {
                        result += s[i] + " ";
                    }
                    result += s[s.length - 1];
                    selectedValue = result;

                    title.setText(LibraryRunner.library.getByTitle(selectedValue));
                }
            });

            checkoutButton = new JButton("Checkout Book");
            gbcBookTitle.gridx = 0;
            gbcBookTitle.gridy = 1;
            panelAuthor.add(checkoutButton, gbcBookTitle);
            checkoutButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    if (selectedValue != null)
                    {
                        String s = LoginWindow.login.getLoggedUser(name).checkout(selectedValue);
                        success.setText(s);

                        Timer timer = new Timer(2000, i -> success.setText(""));
                        timer.setRepeats(false);
                        timer.start();

                    }
                    else
                    {
                        success.setText("Select a book from the list prior to Checking out a book");
                    }
                }
            });

            placeHold = new JButton("Place Hold");
            gbcBookTitle.gridx = 0;
            gbcBookTitle.gridy = 2;
            panelAuthor.add(placeHold, gbcBookTitle);
            placeHold.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    if (selectedValue != null)
                    {
                        String s = LoginWindow.login.getLoggedUser(name).placeHold(selectedValue);
                        success.setText(s);

                        Timer timer = new Timer(2000, i -> success.setText(""));
                        timer.setRepeats(false);
                        timer.start();
                    }
                    else
                    {
                        success.setText("Select a book from the list prior to placing a hold");
                    }
                }
            });

            frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame1.add(splitPanelAuthor);
            frame1.pack();
            frame1.setLocationRelativeTo(null);
            frame1.setVisible(true);
        }


        /**
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // TODO Auto-generated method stub

        }

    }


    /**
     * Return books GUI representation
     *
     * @author shubhan
     * @version May 27, 2021
     */
    private class ReturnBooks
        implements ActionListener
    {
        /**
         * Text Field for user to input title of book to return
         */
        JTextField text;
        /**
         * Success message
         */
        JLabel     success;

        public void openFrame()
        {
            JFrame frame1 = new JFrame("Return : " + name);
            JPanel panelReturn = new JPanel();
            frame1.setSize(400, 200);
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.add(panelReturn);

            panelReturn.setLayout(null);

            JLabel userLabel = new JLabel("Insert Title of Book: ");
            userLabel.setBounds(10, 20, 150, 25);
            panelReturn.add(userLabel);

            success = new JLabel("");
            success.setBounds(10, 80, 300, 25);
            panelReturn.add(success);

            text = new JTextField(20);
            text.setBounds(180, 20, 165, 25);
            panelReturn.add(text);

            JButton button = new JButton("Return");
            button.setBounds(10, 50, 80, 25);
            button.addActionListener(this);
            panelReturn.add(button);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    String s = LoginWindow.login.getLoggedUser(name).returnBook(text.getText());
                    success.setText(s);

                    Timer timer = new Timer(2000, i -> success.setText(""));
                    timer.setRepeats(false);
                    timer.start();
                }
            });

            JButton back = new JButton("Close");
            back.setBounds(100, 50, 80, 25);
            panelReturn.add(back);
            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    frame1.setVisible(false);
                }
            });

            frame1.setVisible(true);
        }


        /**
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // None

        }

    }


    /**
     * Mailbox GUI
     *
     * @author shubhan
     * @version May 27, 2021
     */
    private class ViewMail
    {
        /**
         * Frame of Mailbox GUI
         */
        JFrame             mailFrame;
        /**
         * Panel for mailFrame
         */
        JPanel             mailPanel;
        /**
         * GridBagConstraints for GridLayout of mailPanel
         */
        GridBagConstraints gbcMail = new GridBagConstraints();
        /**
         * TextArea for Mail to be placed in
         */
        JTextArea          mail;

        /**
         * Opens Mail GUI
         */
        public void openFrame()
        {
            mailFrame = new JFrame("Mailbox : " + name);
            mailPanel = new JPanel();
            mailPanel.setLayout(new GridBagLayout());
            mailFrame.setSize(1000, 300);
            mailFrame.add(mailPanel);

            JLabel l = new JLabel(name + " Mailbox");
            gbcMail.gridx = 0;
            gbcMail.gridy = 0;
            mailPanel.add(l, gbcMail);

            mail = new JTextArea();
            mail.setText(LoginWindow.login.getLoggedUser(name).viewMailbox());
            mail.setEditable(false);
            gbcMail.gridx = 0;
            gbcMail.gridy = 1;
            mailPanel.add(mail, gbcMail);

            mailFrame.setVisible(true);
        }
    }


    /**
     *  GUI that represents User's current holds
     *
     *  @author shubhan
     *  @version May 27, 2021
     */
    private class ViewHolds
    {
        /**
         * Frame for holds GUI
         */
        JFrame             holdsFrame;
        /**
         * Panel for holdsFrame
         */
        JPanel             holdsPanel;
        /**
         * GridBagConstraints for GridLayout of holdsPanel
         */
        GridBagConstraints gbcHolds = new GridBagConstraints();
        /**
         * TextArea for current holds
         */
        JTextArea          holds;

        /**
         * Opens hold GUI
         */
        public void openFrame()
        {
            holdsFrame = new JFrame("Holds : " + name);
            holdsPanel = new JPanel();
            holdsPanel.setLayout(new GridBagLayout());
            holdsFrame.setSize(1000, 300);
            holdsFrame.add(holdsPanel);

            JLabel l = new JLabel(name + " Holds");
            gbcHolds.gridx = 0;
            gbcHolds.gridy = 0;
            holdsPanel.add(l, gbcHolds);

            holds = new JTextArea();
            holds.setText(LoginWindow.login.getLoggedUser(name).viewHolds());
            holds.setEditable(false);
            gbcHolds.gridx = 0;
            gbcHolds.gridy = 1;
            holdsPanel.add(holds, gbcHolds);

            holdsFrame.setVisible(true);
        }
    }


    /**
     *  GUI for viewing current checkouts
     *
     *  @author shubhan
     *  @version May 27, 2021
     */
    private class ViewCheckouts
    {
        /**
         * Frame for checkout GUI
         */
        JFrame             checkoutFrame;
        /**
         * Panel for checkoutFrame
         */
        JPanel             checkoutPanel;
        /**
         * GridBagConstraints for checkoutPanel
         */
        GridBagConstraints gbcCheckout = new GridBagConstraints();
        /**
         * TextArea for viewing checkouts
         */
        JTextArea          checkouts;

        /**
         * Opens Checkout GUI
         */
        public void openFrame()
        {
            checkoutFrame = new JFrame("Checkouts : " + name);
            checkoutPanel = new JPanel();
            checkoutPanel.setLayout(new GridBagLayout());
            checkoutFrame.setSize(1000, 300);
            checkoutFrame.add(checkoutPanel);

            JLabel l = new JLabel(name + " Checkouts");
            gbcCheckout.gridx = 0;
            gbcCheckout.gridy = 0;
            checkoutPanel.add(l, gbcCheckout);

            checkouts = new JTextArea();
            checkouts.setText(LoginWindow.login.getLoggedUser(name).viewBooks());
            checkouts.setEditable(false);
            gbcCheckout.gridx = 0;
            gbcCheckout.gridy = 1;
            checkoutPanel.add(checkouts, gbcCheckout);

            checkoutFrame.setVisible(true);
        }
    }

}
