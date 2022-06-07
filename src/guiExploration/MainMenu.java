package guiExploration;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JFrame implements ActionListener {
    private JButton reg;
    private JButton log;
    private JButton exit;

    public MainMenu() {
        JFrame frame = new JFrame("Main Menu");
        JPanel menuPane = new JPanel();

        menuPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        menuPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        menuPane.add(new JLabel("<html><h1><strong><i> Main Menu </i></strong></h1><hr></html>"), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());
        reg = new JButton("Sign Up");
        reg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.setVisible(false);
                    SignUp sign = new SignUp();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        });
        reg.setFont(new Font("Arial", Font.PLAIN, 15));
        buttons.add(reg);
        buttons.setSize(100, 50);

        log = new JButton("Login");
        log.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Login log = new Login();
            }
        });
        log.setFont(new Font("Arial", Font.PLAIN, 15));
        buttons.add(log);
        buttons.setBounds(50, 50, 100, 100);

        exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });
        exit.setFont(new Font("Arial", Font.PLAIN, 15));
        buttons.add(exit);
        buttons.setBounds(50, 50, 100, 100);
        gbc.weighty = 1;



        menuPane.add(buttons, gbc);
        frame.setSize(1440, 1220);
        frame.add(menuPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


