//package guiExpoloration;
//
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//
//public class Main {
//
//    private static int[] defaultSize = {10, 10};
//    public static JButton[] buttonsArray;
//
//    public static void CreateInputSection(JFrame frame, JPanel panelOnTheLeft, JPanel panelOnTheRight) {
//        int[] size = new int[2];
//        JLabel label1ForInput = new JLabel("Rows:    ");
//        JLabel label2ForInput = new JLabel("Columns:");
//
//        JTextField text1ForInput = new JTextField(5);
//        JTextField text2ForInput = new JTextField(5);
//
//        JButton buttonForInput = new JButton("Submit");
//        JPanel panelForRows = new JPanel();
//        JToggleButton start = new JToggleButton("start block");
//        start.setBounds(50, 50, 100, 100);
//
//        JToggleButton end = new JToggleButton("end block");
//        end.setBounds(50, 50, 100, 100);
//        JPanel panelForColumns = new JPanel();
//
//
//        // Generate a maze randomly
//        buttonRandom.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFrame frame = new JFrame("Random Maze");
//                RandomGeneration randGen = new RandomGeneration();
//                frame.add(randGen.printMaze());
//                frame.setSize(800, 800);
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setVisible(true);
//            }
//        });
//        start.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//// Get the button that was clicked
//                if(end.isSelected()){JOptionPane.showMessageDialog(buttonForInput, "please stop the end function first!",
//                        "Invalid Input!", JOptionPane.ERROR_MESSAGE);
//                    start.setSelected(false);}
//
//            }});
//        end.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//// Get the button that was clicked
//                if(start.isSelected()){JOptionPane.showMessageDialog(buttonForInput, "please stop the start function first!",
//                        "Invalid Input!", JOptionPane.ERROR_MESSAGE);
//                end.setSelected(false);}
//            }});
//
//            buttonForInput.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    size[0] = Integer.parseInt(text1ForInput.getText());
//                    size[1] = Integer.parseInt(text2ForInput.getText());
//                    panelOnTheLeft.removeAll();
//                    if (size[0]>10||size[1]>10){
//                        JOptionPane.showMessageDialog(buttonForInput,"Too big",
//                            "Wiring Class: Error", JOptionPane.ERROR_MESSAGE);
//                        size[0]=10;
//                        size[1]=10;
//                    }
//                CreateSelectionButtons(frame, panelOnTheLeft, size, end,start);
//                panelOnTheLeft.revalidate();
//                panelOnTheLeft.repaint();
//            }
//        }
//        );
//
//
//        panelOnTheRight.setLayout(new GridLayout(10, 1));
//        panelForRows.add(label1ForInput);
//        panelForRows.add(text1ForInput);
//        panelForColumns.add(label2ForInput);
//        panelForColumns.add(text2ForInput);
//        panelOnTheRight.add(panelForRows);
//        panelOnTheRight.add(panelForColumns);
//        panelOnTheRight.add(buttonForInput);
//        panelOnTheRight.add(start);
//        panelOnTheRight.add(end);
//    }
//
//
//    public static void CreateSelectionButtons(JFrame frame, JPanel panelOnTheLeft, int[] size, AbstractButton end,AbstractButton start) {
//        int buttonNum = size[0] * size[1];
//        JButton[] buttons = new JButton[buttonNum];
//        class Actions implements ActionListener {
//            public void actionPerformed(ActionEvent e) {
//                // Get the button that was clicked
//                JButton theButton = (JButton) e.getSource();
//
//                // Set it's background color to white
//                Color color = theButton.getBackground();
//                if(start.isSelected()){
//                    for(int i=0;i<=buttonNum;i++){
//                        Color check=buttons[i].getBackground();
//                        if(check==Color.green){
//                            buttons[i].setBackground(Color.white);
//
//                        }
//                    }
//                    theButton.setBackground(Color.green);}
//                else if(end.isSelected()){
//                    for(int i=0;i<=buttonNum;i++){
//                        Color check=buttons[i].getBackground();
//                        if(check==Color.red){
//                            buttons[i].setBackground(Color.white);
//
//                        }
//                    }
//                    theButton.setBackground(Color.red);}
//                else if (color==Color.black)
//                {
//                    theButton.setBackground(Color.white);}
//                else{theButton.setBackground(Color.black);
//                }
//
//            }}
//
//        JPanel panelForButtons = new JPanel();
//
//        panelForButtons.setLayout(new GridLayout(size[0], size[1]));
//
//
//        buttonsArray = buttons;
//
//// The only action handler you need
//        Actions myActionHandler = new Actions();
//
//        for (int i = 0; i < buttonNum; i++) {
//            buttons[i] = new JButton();
//
//            // Change each button size
//            buttons[i].setPreferredSize(new Dimension(50, 50));
//            buttons[i].setBackground(Color.black);
//
//            // Just pass in the one we already have from above
//            buttons[i].addActionListener(myActionHandler);
//
//            panelForButtons.add(buttons[i]);
//        }
//
//        panelOnTheLeft.add(panelForButtons, BorderLayout.WEST);
//
//
//
//    }
//
//        public static void main(String[] args) {
//            JFrame frame = new JFrame("Maze Program");
//            JPanel panelOnTheLeft = new JPanel();
//            JPanel panelOnTheRight = new JPanel();
//
//            frame.setLayout(new BorderLayout());
//            CreateSelectionButtons(frame, panelOnTheLeft, defaultSize, null,null);
//            CreateInputSection(frame, panelOnTheLeft, panelOnTheRight);
//            frame.add(panelOnTheLeft, BorderLayout.WEST);
//            frame.add(panelOnTheRight, BorderLayout.EAST);
//
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.pack();
//            frame.setVisible(true);
//
//        }
//    }
