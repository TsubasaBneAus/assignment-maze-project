package guiExploration;// Java program to implement
// a Simple Registration Form
// using Java Swing

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    // Components of the SignUp Form
    private JFrame frame = new JFrame();
    private JLabel email = new JLabel("Email Address ");
    private JLabel password = new JLabel("Password ");
    private JTextField emailText = new JTextField();
    private JPasswordField passwordText = new JPasswordField();

    private JCheckBox term = new JCheckBox("Show Password");
    private JButton sub = new JButton("Submit");
    private JButton reset = new JButton("Reset");
    private JButton back = new JButton("Back");
    private JLabel res;

    // constructor, to initialize the components
    // with default values.
    public Login() {
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        email.setSize(200, 20);
        email.setLocation(100, 100);

        emailText.setFont(new Font("Arial", Font.PLAIN, 15));
        emailText.setSize(190, 20);
        emailText.setLocation(250, 100);

        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setSize(100, 20);
        password.setLocation(100, 150);

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
            frame.dispose();
            MainMenu mainMenu = new MainMenu();
        });

        frame.add(email);
        frame.add(emailText);
        frame.add(password);
        frame.add(passwordText);
        frame.add(term);
        frame.add(sub);
        frame.add(reset);
        frame.add(back);

        frame.setTitle("Login Form");
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(600, 600);
    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e) {
//        if (email.getText() == ""){
//            JOptionPane.showMessageDialog(null, "The email field is empty. Please fill in all details to Login!", "Login Failed!",JOptionPane.ERROR_MESSAGE);
//        } else if (password.getPassword().toString() == ""){
//            JOptionPane.showMessageDialog(null, "The password field is empty. Please fill in all details to Login!", "Login Failed!",JOptionPane.ERROR_MESSAGE);
//
//        }
        System.out.println(e.getSource() == sub);
        if (e.getSource() == sub) {
            System.out.println(emailText.getText());
            if (emailText.getText().equals("") || passwordText.getPassword().equals("")) {
//                System.out.println("masuk sini");
                JOptionPane.showMessageDialog(sub, "All fields need to be filled up to login!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            } else if (passwordText.getPassword().equals("") && emailText.getText().equals("")) {
                JOptionPane.showMessageDialog(sub, "All the fields need to be filled up to login!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            } else
                JOptionPane.showMessageDialog(sub, "You have logged in successfully!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);

//            if (e.getSource() == sub && email.getText() == "" && password.getPassword().toString() == ""){
//            JOptionPane.showMessageDialog(sub, "Fill up all fields to login", "LoginFailed!", JOptionPane.ERROR_MESSAGE);
        }
//        else
//            JOptionPane.showMessageDialog(sub, "You have successfully logged in!", "Login Success!", JOptionPane.INFORMATION_MESSAGE);

//        } else {
//            JOptionPane.showMessageDialog(sub, "Agree to the terms and conditions to Sign Up!",
//                    "Try Again!!", JOptionPane.ERROR_MESSAGE);
//        }
        if (e.getSource() == reset) {
            String def = "";
            emailText.setText(def);
            passwordText.setText(def);
        }


    }
}

// Driver Code
//class SignIn {
//
//    public static void main(String[] args) throws Exception {
//        Login login = new Login();
//    }
//}
