package guiExploration;// Java program to implement
// a Simple Registration Form
// using Java Swing

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The class for displaying a sign-up page
 */
public class SignUp extends JFrame implements ActionListener {

    // Components of the SignUp Form
    private final JFrame frame = new JFrame("Registration Form");
    private final JTextField firstNameText = new JTextField();
    private final JTextField lastNameText = new JTextField();
    private final JTextField emailText;
    private final JPasswordField passwordText;

    private final JCheckBox term = new JCheckBox("Accept Terms And Conditions.");
    private final JButton sub = new JButton("Submit");
    private final JButton reset = new JButton("Reset");
    private final JTextArea userOutput = new JTextArea();
    private JTextArea resadd;
    public Connection connection = null;

    /**
     * The constructor for this "SingUp" class
     * @throws SQLException
     */
    public SignUp() throws SQLException {
//        connection = DriverManager.getConnection("jdbc:sqlite:C://Users/User/IdeaProjects/Maze-project/MazeDesign.db");

        JLabel firstName = new JLabel("First Name ");
        firstName.setFont(new Font("Arial", Font.PLAIN, 20));
        firstName.setSize(150, 20);
        firstName.setLocation(100, 100);

        firstNameText.setFont(new Font("Arial", Font.PLAIN, 15));
        firstNameText.setSize(190, 20);
        firstNameText.setLocation(200, 100);


        JLabel lastName = new JLabel("Last Name ");
        lastName.setFont(new Font("Arial", Font.PLAIN, 20));
        lastName.setSize(150, 20);
        lastName.setLocation(100, 150);

        lastNameText.setFont(new Font("Arial", Font.PLAIN, 15));
        lastNameText.setSize(190, 20);
        lastNameText.setLocation(200, 150);

        JLabel email = new JLabel("Email Address ");
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        email.setSize(150, 20);
        email.setLocation(50, 200);

        emailText = new JTextField();
        emailText.setFont(new Font("Arial", Font.PLAIN, 15));
        emailText.setSize(190, 20);
        emailText.setLocation(200, 200);

        JLabel password = new JLabel("Password ");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setSize(100, 20);
        password.setLocation(100, 250);

        passwordText = new JPasswordField();
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

        JButton back = new JButton("Back");
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

        JLabel res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);

//        resadd = new JTextArea();
//        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
//        resadd.setSize(200, 75);
//        resadd.setLocation(580, 175);
//        resadd.setLineWrap(true);
//        c.add(resadd);


        frame.add(firstName);
        frame.add(firstNameText);
        frame.add(lastName);
        frame.add(lastNameText);
        frame.add(email);
        frame.add(emailText);
        frame.add(password);
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
    }

    /**
     * The method being activated when the "sub" or "reset" are clicked
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == sub) {
//            if (term.isSelected() ) {
//                String data1;
//                String data
//                        = "Full Name: "
//                        + Fname.getText() + Lname.getText() + "\n"
//                        + "Email : "
//                        + email.getText() + "\n"
//                        + "Password : "
//                        + password.getPassword() + "\n";
//                UserOutput.setText(data);
//                UserOutput.setEditable(false);
//                Fname.setText("");
//                Lname.setText("");
//                email.setText("");
//                password.setText("");
//                term.setEnabled(false);
//
//                JOptionPane.showMessageDialog(sub, "You have successfully created your account!", "Account Registration",JOptionPane.INFORMATION_MESSAGE);
//            } else {
//                JOptionPane.showMessageDialog(sub, "Invalid Input in one of the fields or user has not agreed to terms and conditions!",
//                        "Sign Up Failed!", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//        else if (e.getSource() == reset) {
//            String def = "";
//            Fname.setText(def);
//            Lname.setText(def);
//            email.setText(def);
//            password.setText(def);
//            UserOutput.setText(def);
//            term.setSelected(false);
//            term.setEnabled(true);
//            resadd.setText(def);
//        }
        if (e.getSource() == sub) {
            if (firstNameText.getText().equals("") && lastNameText.getText().equals("") && emailText.getText().equals("") && String.valueOf(passwordText.getPassword()).equals("")) {
                JOptionPane.showMessageDialog(sub, "All Fields must be filled in to register an account!", "Registration Failed!", JOptionPane.ERROR_MESSAGE);
            } else if (firstNameText.getText().matches("^[a-zA-Z]*$") && lastNameText.getText().matches("^[a-zA-Z]*$") && term.isSelected()) {
                JOptionPane.showMessageDialog(sub, "First name and last name should only contain alphabets!", "Registration Failed!", JOptionPane.ERROR_MESSAGE);
            } else if (emailText.getText().matches("^(.+)@(.+)$")) {
                JOptionPane.showMessageDialog(sub, "Email should be in the right format!!", "Registration Failed!", JOptionPane.ERROR_MESSAGE);
            } else if (passwordText.getPassword().toString().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")) {
                JOptionPane.showMessageDialog(sub, "Password should contain uppercase, lowercase, numbers, and special characters!", "Registration Failed!", JOptionPane.ERROR_MESSAGE);
                String data1;
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
                    statement.executeUpdate("insert into main.Users (FirstName,LastName, Email, Password) values ('" + firstNameText.getText() + "','" + lastNameText.getText() + "','" + emailText.getText() + "','" + passwordText.getPassword().toString() + "')");
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
            } else {
                JOptionPane.showMessageDialog(sub, "Invalid Input in one of the fields or user has not agreed to terms and conditions!",
                        "Sign Up Failed!", JOptionPane.ERROR_MESSAGE);
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
            resadd.setText(def);
        } // else if (Fname.getText().equals("") && Lname.getText().equals("") && email.getText().equals("") && password.getPassword().toString().equals("")){
        //         JOptionPane.showMessageDialog(sub, "All Fields must be filled in to register an account!", "Registration Failed!", JOptionPane.ERROR_MESSAGE);
        // }
    }
}
