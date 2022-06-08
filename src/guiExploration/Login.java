package guiExploration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *  The class for users to login
 */
public class Login extends JFrame implements ActionListener {
    private final JFrame frame = new JFrame("Login Form");
    private final JLabel emailLabel = new JLabel("Email Address ");
    private final JLabel passwordLabel = new JLabel("Password ");
    private final JTextField emailText = new JTextField();
    private final JPasswordField passwordText = new JPasswordField();
    private final JCheckBox term = new JCheckBox("Show Password");
    private final JButton sub = new JButton("Submit");
    private final JButton reset = new JButton("Reset");
    private final JButton back = new JButton("Back");
    public Connection connection = null;

    /**
     * The constructor for the "Login" class
     */
    public Login() {
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        emailLabel.setSize(200, 20);
        emailLabel.setLocation(100, 100);

        emailText.setFont(new Font("Arial", Font.PLAIN, 15));
        emailText.setSize(190, 20);
        emailText.setLocation(250, 100);

        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setSize(100, 20);
        passwordLabel.setLocation(100, 150);

        passwordText.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordText.setSize(150, 20);
        passwordText.setLocation(250, 150);

        term.setFont(new Font("Arial", Font.PLAIN, 15));
        term.setSize(250, 20);
        term.setLocation(150, 400);

        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(150, 450);
        sub.addActionListener(this);

        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 450);
        reset.addActionListener(this);

        back.setFont(new Font("Arial", Font.PLAIN, 15));
        back.setSize(100, 20);
        back.setLocation(390, 450);
        back.addActionListener(e -> {
            new MainMenu();
            frame.dispose();
        });

        frame.add(emailLabel);
        frame.add(emailText);
        frame.add(passwordLabel);
        frame.add(passwordText);
        frame.add(term);
        frame.add(sub);
        frame.add(reset);
        frame.add(back);

        frame.setBounds(300, 90, 700, 700);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * The method activated when the "sub" and "reset"
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            if (emailText.getText().equals("") || String.valueOf(passwordText.getPassword()).equals("")) {
                JOptionPane.showMessageDialog(sub, "All fields need to be filled up to login!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            } else {
                Statement statement = null;
                try {
                    connection = DBConnection.getInstance();
                    statement = connection.createStatement();
                    statement.setQueryTimeout(30);  // set timeout to 30 sec.
                    ResultSet rs = statement.executeQuery("select * from main.Users where Email = '" + emailText.getText() + "' and Password='" + String.valueOf(passwordText.getPassword()) + "'");
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(sub, "You have logged in successfully!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                        new MazeGUI(emailText.getText());
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(sub, "Unable to confirm your credential", "Login failed", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    emailText.setText("");
                    passwordText.setText("");
                    term.setEnabled(true);
                    JOptionPane.showMessageDialog(sub, "Unable to confirm your credential", "Login failed", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        if (e.getSource() == reset) {
            String def = "";
            emailText.setText(def);
            passwordText.setText(def);
        }
    }

    /**
     * The method for getting the title of the "frame"
     * @return "frame.getTitle()"
     */
    public String getFrameName() {
        return frame.getTitle();
    }

    /**
     * The method for getting the text of the "emailLabel"
     * @return "emailLabel.getText()"
     */
    public String getEmailLabel() {
        return emailLabel.getText();
    }

    /**
     * The method for getting the text of the "passwordLabel"
     * @return "passwordLabel.getText()"
     */
    public String getPasswordLabel() {
        return passwordLabel.getText();
    }

    /**
     * The method for getting the text of the "term"
     * @return "term.getText()"
     */
    public String getTerm() {
        return term.getText();
    }

    /**
     * The method for getting the text of the "sub"
     * @return "sub.getText()"
     */
    public String getSub() {
        return sub.getText();
    }

    /**
     * The method for getting the text of the "reset"
     * @return "reset.getText()"
     */
    public String getReset() {
        return reset.getText();
    }

    /**
     * The method for getting the text of the "back"
     * @return "back.getText()"
     */
    public String getBack() {
        return back.getText();
    }
}
