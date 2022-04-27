package guiExpoloration;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Main {

    private static int[] defaultSize = {10, 10};
    public static JButton[] buttonsArray;

    public static void CreateInputSection(JFrame frame, JPanel panelOnTheLeft, JPanel panelOnTheRight) {
        int[] size = new int[2];
        JLabel label1ForInput = new JLabel("Rows:    ");
        JLabel label2ForInput = new JLabel("Columns:");

        JTextField text1ForInput = new JTextField(5);
        JTextField text2ForInput = new JTextField(5);

        JButton buttonForInput = new JButton("Submit");
        JPanel panelForRows = new JPanel();
        JPanel panelForColumns = new JPanel();

            buttonForInput.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    size[0] = Integer.parseInt(text1ForInput.getText());
                    size[1] = Integer.parseInt(text2ForInput.getText());
                    panelOnTheLeft.removeAll();
                    if (size[0]>10||size[1]>10){
                        JOptionPane.showMessageDialog(buttonForInput,"Too big",
                            "Wiring Class: Error", JOptionPane.ERROR_MESSAGE);
                        size[0]=10;
                        size[1]=10;
                    }
                CreateSelectionButtons(frame, panelOnTheLeft, size);
                panelOnTheLeft.revalidate();
                panelOnTheLeft.repaint();
            }
        }
        );


        panelOnTheRight.setLayout(new GridLayout(10, 1));
        panelForRows.add(label1ForInput);
        panelForRows.add(text1ForInput);
        panelForColumns.add(label2ForInput);
        panelForColumns.add(text2ForInput);
        panelOnTheRight.add(panelForRows);
        panelOnTheRight.add(panelForColumns);
        panelOnTheRight.add(buttonForInput);
    }


    public static void CreateSelectionButtons(JFrame frame, JPanel panelOnTheLeft, int[] size) {
        class Actions implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                // Get the button that was clicked
                JButton theButton = (JButton) e.getSource();
                // Set it's background color to white
                Color color = theButton.getBackground();
                if (color==Color.black)
                {
                theButton.setBackground(Color.white);}
                else{theButton.setBackground(Color.black);
            }
        }}
        JPanel panelForButtons = new JPanel();
        int buttonNum = size[0] * size[1];
        panelForButtons.setLayout(new GridLayout(size[0], size[1]));

        JButton[] buttons = new JButton[buttonNum];
        buttonsArray = buttons;

// The only action handler you need
        Actions myActionHandler = new Actions();

        for (int i = 0; i < buttonNum; i++) {
            buttons[i] = new JButton();

            // Change each button size
            buttons[i].setPreferredSize(new Dimension(50, 50));
            buttons[i].setBackground(Color.black);

            // Just pass in the one we already have from above
            buttons[i].addActionListener(myActionHandler);

            panelForButtons.add(buttons[i]);
        }

        panelOnTheLeft.add(panelForButtons, BorderLayout.WEST);



    }

        public static void main(String[] args) {
            JFrame frame = new JFrame("Maze Program");
            JPanel panelOnTheLeft = new JPanel();
            JPanel panelOnTheRight = new JPanel();

            frame.setLayout(new BorderLayout());
            CreateSelectionButtons(frame, panelOnTheLeft, defaultSize);
            CreateInputSection(frame, panelOnTheLeft, panelOnTheRight);
            frame.add(panelOnTheLeft, BorderLayout.WEST);
            frame.add(panelOnTheRight, BorderLayout.EAST);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

        }
    }
