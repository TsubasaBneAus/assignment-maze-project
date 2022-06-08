package guiExploration;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * The class for displaying the main menu of this maze application
 */
public class MainMenu extends JFrame {
    JFrame frame = new JFrame("Main Menu");
    JPanel menuPane = new JPanel();
    JPanel buttons = new JPanel(new GridBagLayout());
    JButton reg = new JButton("Sign Up");
    JButton log = new JButton("Login");
    JButton exit = new JButton("Exit");

    /**
     * The constructor for this "MainMenu" class
     */
    public MainMenu() {
        menuPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        menuPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        menuPane.add(new JLabel("<html><h1><strong><i> Main Menu </i></strong></h1><hr></html>"), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        reg.addActionListener(e -> {
            frame.dispose();
            new SignUp();
        });
        reg.setFont(new Font("Arial", Font.PLAIN, 15));
        buttons.add(reg);
        buttons.setSize(100, 50);
        log.addActionListener(e -> {
            frame.dispose();
            new Login();
        });
        log.setFont(new Font("Arial", Font.PLAIN, 15));
        buttons.add(log);
        buttons.setBounds(50, 50, 100, 100);
        exit.addActionListener(e -> System.exit(0));
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
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * The method for getting the name of the "frame"
     * @return "frame"
     */
    public String getFrameName() {
        return frame.getTitle();
    }

    /**
     * The method for getting the text of the "reg"
     * @return "reg.getText()"
     */
    public String getReg() {
        return reg.getText();
    }

    /**
     * The method for getting the text of the "log"
     * @return "log.getText()"
     */
    public String getLog() {
        return log.getText();
    }

    /**
     * The method for getting the text of the "exit"
     * @return "exit.getText()"
     */
    public String getExit() {
        return exit.getText();
    }
}

