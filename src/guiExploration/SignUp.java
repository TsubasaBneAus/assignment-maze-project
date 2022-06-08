package guiExploration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The class for users to sign up their accounts
 */
public class SignUp extends JFrame implements ActionListener {
    private final JFrame frame = new JFrame("Registration Form");
    private final JLabel firstNameLabel = new JLabel("First Name ");
    private final JLabel lastNameLabel = new JLabel("Last Name ");
    private final JLabel emailLabel = new JLabel("Email Address ");
    private final JLabel passwordLabel = new JLabel("Password ");
    private final JTextField firstNameText = new JTextField();
    private final JTextField lastNameText = new JTextField();
    private final JTextField emailText = new JTextField();
    private final JPasswordField passwordText = new JPasswordField();
    private final JCheckBox term = new JCheckBox("Accept Terms And Conditions.");
    private final JButton sub = new JButton("Submit");
    private final JButton reset = new JButton("Reset");
    private final JButton back = new JButton("Back");
    private final JTextArea userOutput = new JTextArea();
    private final JLabel res = new JLabel("");
    public Connection connection = null;


    /**
     * The constructor for the "SignUp" class
     */
    public SignUp() {
        firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        firstNameLabel.setSize(150, 20);
        firstNameLabel.setLocation(100, 100);

        firstNameText.setFont(new Font("Arial", Font.PLAIN, 15));
        firstNameText.setSize(190, 20);
        firstNameText.setLocation(200, 100);


        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        lastNameLabel.setSize(150, 20);
        lastNameLabel.setLocation(100, 150);

        lastNameText.setFont(new Font("Arial", Font.PLAIN, 15));
        lastNameText.setSize(190, 20);
        lastNameText.setLocation(200, 150);

        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        emailLabel.setSize(150, 20);
        emailLabel.setLocation(50, 200);

        emailText.setFont(new Font("Arial", Font.PLAIN, 15));
        emailText.setSize(190, 20);
        emailText.setLocation(200, 200);

        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setSize(100, 20);
        passwordLabel.setLocation(100, 250);

        passwordText.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordText.setSize(190, 20);
        passwordText.setLocation(200, 250);

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
            frame.dispose();
            new MainMenu();
        });

        userOutput.setFont(new Font("Arial", Font.PLAIN, 15));
        userOutput.setSize(300, 400);
        userOutput.setLocation(500, 100);
        userOutput.setLineWrap(true);
        userOutput.setEditable(false);

        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);

        frame.add(firstNameLabel);
        frame.add(firstNameText);
        frame.add(lastNameLabel);
        frame.add(lastNameText);
        frame.add(emailLabel);
        frame.add(emailText);
        frame.add(passwordLabel);
        frame.add(passwordText);
        frame.add(term);
        frame.add(sub);
        frame.add(reset);
        frame.add(back);
        frame.add(userOutput);
        frame.add(res);

        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * The method activated when the "sub" and "reset"
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            if (firstNameText.getText().equals("") && lastNameText.getText().equals("") && emailText.getText().equals("") && String.valueOf(passwordText.getPassword()).equals("")) {
                JOptionPane.showMessageDialog(sub, "All Fields must be filled in to register an account!", "Registration Failed!", JOptionPane.ERROR_MESSAGE);
            } else if (!firstNameText.getText().matches("^[a-zA-Z]*$") || !lastNameText.getText().matches("^[a-zA-Z]*$") || !term.isSelected()) {
                JOptionPane.showMessageDialog(sub, "First name and last name should only contain alphabets!", "Registration Failed!", JOptionPane.ERROR_MESSAGE);
            } else if (!emailText.getText().matches("^(.+)@(.+)$")) {
                JOptionPane.showMessageDialog(sub, "Email should be in the right format!!", "Registration Failed!", JOptionPane.ERROR_MESSAGE);
            } else if (!String.valueOf(passwordText.getPassword()).matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")) {
                JOptionPane.showMessageDialog(sub, "Password should contain uppercase, lowercase, numbers, and special characters!", "Registration Failed!", JOptionPane.ERROR_MESSAGE);
            } else {
                String data
                        = "Full Name: "
                        + firstNameText.getText() + lastNameText.getText() + "\n"
                        + "Email : "
                        + emailText.getText() + "\n"
                        + "Password : "
                        + passwordText.getPassword() + "\n";
                userOutput.setText(data);
//                getClass(Login);
                Statement statement = null;
                try {
                    connection = DBConnection.getInstance();
                    statement = connection.createStatement();
                    statement.setQueryTimeout(30);  // set timeout to 30 sec.
                    statement.executeUpdate("insert into main.Users (FirstName,LastName, Email, Password) values ('" + firstNameText.getText() + "','" + lastNameText.getText() + "','" + emailText.getText() + "','" + String.valueOf(passwordText.getPassword()) + "')");
                    //ResultSet rs = statement.executeQuery("select * from User");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    userOutput.setEditable(false);
                    firstNameText.setText("");
                    lastNameText.setText("");
                    emailText.setText("");
                    passwordText.setText("");
                    term.setEnabled(true);
                }

                JOptionPane.showMessageDialog(sub, "You have successfully created your account!", "Account Registration", JOptionPane.INFORMATION_MESSAGE);
                new MazeGUI(emailText.getText());
                frame.dispose();
            }
        } else if (e.getSource() == reset) {
            String def = "";
            firstNameText.setText(def);
            lastNameText.setText(def);
            emailText.setText(def);
            passwordText.setText(def);
            userOutput.setText(def);
            term.setSelected(false);
            term.setEnabled(true);
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
     * The method for getting the text of the "firstNameLabel"
     * @return "firstNameLabel.getText()"
     */
    public String getFirstNameLabel() {
        return firstNameLabel.getText();
    }

    /**
     * The method for getting the text of the "lastNameLabel"
     * @return "lastNameLabel.getText()"
     */
    public String getLastNameLabel() {
        return lastNameLabel.getText();
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
