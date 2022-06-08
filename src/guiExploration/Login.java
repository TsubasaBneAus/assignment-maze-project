package guiExploration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    private final JFrame frame = new JFrame("Login Form");
    private final JLabel Email;
    private final JLabel Password;
    private final JTextField email;
    private final JPasswordField password;
    private final JCheckBox term;
    private final JButton sub;
    private final JButton reset;
    private final JButton back;
    public Connection connection = null;

    public Login() {
        frame.setTitle("Login Form");
        frame.setBounds(300, 90, 700, 700);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);


        Email = new JLabel("Email Address ");
        Email.setFont(new Font("Arial", Font.PLAIN, 20));
        Email.setSize(200, 20);
        Email.setLocation(100, 100);
        frame.add(Email);

        email = new JTextField();
        email.setFont(new Font("Arial", Font.PLAIN, 15));
        email.setSize(190, 20);
        email.setLocation(250, 100);
        frame.add(email);


        Password = new JLabel("Password ");
        Password.setFont(new Font("Arial", Font.PLAIN, 20));
        Password.setSize(100, 20);
        Password.setLocation(100, 150);
        frame.add(Password);

        password = new JPasswordField();
        password.setFont(new Font("Arial", Font.PLAIN, 15));
        password.setSize(150, 20);
        password.setLocation(250, 150);
        frame.add(password);


        term = new JCheckBox("Show Password");
        term.setFont(new Font("Arial", Font.PLAIN, 15));
        term.setSize(250, 20);
        term.setLocation(150, 400);
        frame.add(term);

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(150, 450);
        sub.addActionListener(this);
        frame.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 450);
        reset.addActionListener(this);
        frame.add(reset);

        back = new JButton("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 15));
        back.setSize(100, 20);
        back.setLocation(390, 450);
        back.addActionListener(e -> {
            new MainMenu();
            frame.dispose();
        });
        frame.add(back);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource() == sub);
        if (e.getSource() == sub) {
            System.out.println(email.getText());
            System.out.println(password.getPassword().toString());
            if (email.getText().equals("") || String.valueOf(password.getPassword()).equals("")) {
                JOptionPane.showMessageDialog(sub, "All fields need to be filled up to login!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            } else {
                Statement statement = null;
                try {
                    connection = DBConnection.getInstance();
                    statement = connection.createStatement();
                    statement.setQueryTimeout(30);  // set timeout to 30 sec.
                    ResultSet rs = statement.executeQuery("select * from main.Users where Email = '" + email.getText() + "' and Password='" + String.valueOf(password.getPassword()) + "'");
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(sub, "You have logged in successfully!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                        new MazeGUI(email.getText());
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(sub, "Unable to confirm your credential", "Login failed", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    email.setText("");
                    password.setText("");
                    term.setEnabled(true);
                    JOptionPane.showMessageDialog(sub, "Unable to confirm your credential", "Login failed", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        if (e.getSource() == reset) {
            String def = "";
            email.setText(def);
            password.setText(def);
        }
    }
}
