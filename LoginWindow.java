import java.awt.event.*;
import javax.swing.*;

/**
 * Provides window for users to login or sign up
 *
 * @author andrew
 * @version May 26, 2021
 */
public class LoginWindow extends JFrame
{
    /**
     * Login system used
     */
    public static Login   login;
    private static JFrame frame;

    /**
     * Creates a new LoginWindow object
     * @param log login to use
     */
    public LoginWindow(Login log)
    {
        LoginWindow.login = log;

        frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel welcome = new JLabel("Welcome to our Virtual Library");
        welcome.setBounds(50, 30, 300, 25);
        panel.add(welcome);

        JButton login1 = new JButton("Login");
        login1.setBounds(50, 80, 90, 25);
        login1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                OpenLogin o = new OpenLogin();
                o.openFrame();
            }
        });
        panel.add(login1);

        JButton newUser = new JButton("New User");
        newUser.setBounds(150, 80, 90, 25);
        newUser.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                OpenSignUp o = new OpenSignUp();
                o.openFrame();
            }
        });
        panel.add(newUser);

        frame.setVisible(true);
    }

    private class OpenLogin implements ActionListener
    {
        JTextField text;
        JPasswordField pass;
        JLabel success;

        public void openFrame()
        {
            final JFrame frame1 = new JFrame();
            JPanel panel = new JPanel();
            frame1.setSize(350, 200);
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.add(panel);

            panel.setLayout(null);

            JLabel userLabel = new JLabel("User");
            userLabel.setBounds(10, 20, 80, 25);
            panel.add(userLabel);

            text = new JTextField(20);
            text.setBounds(100, 20, 165, 25);
            panel.add(text);

            JLabel passwordLabel = new JLabel("Password");
            passwordLabel.setBounds(10, 50, 80, 25);
            panel.add(passwordLabel);

            pass = new JPasswordField(20);
            pass.setBounds(100, 50, 165, 25);
            panel.add(pass);

            JButton button = new JButton("Login");
            button.setBounds(10, 80, 80, 25);
            button.addActionListener(this);
            panel.add(button);

            JButton back = new JButton("Close");
            back.setBounds(100, 80, 80, 25);
            back.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    frame1.setVisible(false);
                }
            });
            panel.add(back);

            success = new JLabel("");
            success.setBounds(10, 110, 300, 25);
            panel.add(success);

            frame1.setVisible(true);
        }

        @SuppressWarnings("deprecation")
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(login.login(text.getText(), pass.getText()))
            {
                success.setText("Login successful");
                @SuppressWarnings("unused")
                UserWindow u = new UserWindow(text.getText());
            }
            else
            {
                success.setText("Login failed");
            }

        }
    }

    private class OpenSignUp implements ActionListener
    {
        JTextField user;
        JTextField pass;
        JLabel success;

        public void openFrame()
        {
            @SuppressWarnings("hiding")
            final
            JFrame frame = new JFrame();
            JPanel panel = new JPanel();
            frame.setSize(350, 250);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);

            panel.setLayout(null);

            JLabel userLabel = new JLabel("Set username");
            userLabel.setBounds(10, 20, 150, 25);
            panel.add(userLabel);

            user = new JTextField(20);
            user.setBounds(150, 20, 165, 25);
            panel.add(user);

            JLabel passwordLabel = new JLabel("Set password");
            passwordLabel.setBounds(10, 50, 150, 25);
            panel.add(passwordLabel);

            pass = new JTextField(20);
            pass.setBounds(150, 50, 165, 25);
            panel.add(pass);

            JLabel passNotice1 = new JLabel("Password must contain at least");
            passNotice1.setBounds(10, 85, 300, 25);
            panel.add(passNotice1);

            JLabel passNotice2 = new JLabel("8 characters of letters and numbers");
            passNotice2.setBounds(10, 100, 300, 25);
            panel.add(passNotice2);

            JButton button = new JButton("Register");
            button.setBounds(10, 140, 80, 25);
            button.addActionListener(this);
            panel.add(button);

            JButton back = new JButton("Close");
            back.setBounds(100, 140, 80, 25);
            back.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    frame.setVisible(false);
                }
            });
            panel.add(back);

            success = new JLabel("");
            success.setBounds(10, 170, 300, 25);
            panel.add(success);

            frame.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            int num = login.setUserPass(user.getText(), pass.getText());
            if (num == 0)
            {
                success.setText("Registration successful");
            }
            else
            {
                if(num == -1)
                    success.setText("Username already taken");
                else if(num == -2)
                    success.setText("Password invalid");
            }

        }
    }

}


