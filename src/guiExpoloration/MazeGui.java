package guiExpoloration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MazeGui extends JFrame implements ActionListener, MouseListener {

    private static int[] defaultSize = {10, 10};
    public static JButton[] buttonsArray;



    public static void CreateInputSection(JFrame frame, JPanel panelOnTheLeft, JPanel panelOnTheRight) {
        int[] size = new int[10];

        JLabel label1ForInput = new JLabel("Rows:");
        JLabel label2ForInput = new JLabel("Columns:");

        JTextField text1ForInput = new JTextField(5);
        JTextField text2ForInput = new JTextField(5);

        JButton buttonForInput = new JButton("Submit");
        buttonForInput.setBounds(50, 50, 100, 100);
        JButton buttonGenerate = new JButton("Generate");
        buttonGenerate.setBounds(50, 50, 100, 100);
        JButton buttonExport = new JButton("Export");
        buttonExport.setBounds(50, 50, 100, 100);
        JButton buttonReset = new JButton("Reset");
        buttonReset.setBounds(50, 50, 100, 100);
        JButton buttonUpload = new JButton("Upload");
        buttonUpload.setBounds(50, 50, 100, 100);
        JButton buttonTest = new JButton("Reset");
        buttonTest.setBounds(50,50,100,100);
        JPanel panelForRows = new JPanel();
        JPanel panelForColumns = new JPanel();
        JScrollBar scrollBar = new JScrollBar();

        buttonForInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                size[0] = Integer.parseInt(text1ForInput.getText());
                size[1] = Integer.parseInt(text2ForInput.getText());
                panelOnTheLeft.removeAll();
                CreateSelectionButtons(frame, panelOnTheLeft, size);
                if (size[0]>10||size[1]>10){
                    JOptionPane.showMessageDialog(buttonForInput,"Too big",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    size[0]=10;
                    size[1]=10;
                } else if (size[0] == 0 || size[1] == 0) {
                    JOptionPane.showMessageDialog(buttonForInput, "The Column Size or Row Size cannot be zero!",
                            "Invalid Input!", JOptionPane.ERROR_MESSAGE);
                }
                panelOnTheLeft.revalidate();
                panelOnTheLeft.repaint();
                
               
            }
        });

//        buttonReset.addActionListener(new ActionListener() {
//            @Override
//            public
//            void actionPerformed(ActionEvent e) {
//                JButton[] button = (JButton[]) e.getSource();
//                for (JButton jButton : button) {
//                    jButton.setBackground(Color.WHITE);
//                }
//            }
//        });

        panelOnTheRight.setLayout(new GridLayout(10, 1));
        panelForRows.add(label1ForInput);
        panelForRows.add(text1ForInput);
        panelForColumns.add(label2ForInput);
        panelForColumns.add(text2ForInput);
        panelOnTheRight.add(panelForRows);
        panelOnTheRight.add(panelForColumns);
        panelOnTheRight.add(buttonForInput);
        panelOnTheRight.add(buttonGenerate);
        panelOnTheRight.add(buttonExport);
        panelOnTheRight.add(buttonReset);
        panelOnTheRight.add(buttonUpload);

    }
//    public static void JScrollBar(){
//        JScrollBar scrollB1 = new JScrollBar(JScrollBar.HORIZONTAL, 1000, 40, 0, 100);
//        JScrollBar scrollB2 = new JScrollBar(JScrollBar.VERTICAL, 1000, 60, 0, 100);
//    }




    public static
    void CreateSelectionButtons(JFrame frame, JPanel panelOnTheLeft, int[] size) {
        // The only action handler you need
//        class Actions implements ActionListener {
//            public void actionPerformed(ActionEvent e) {
//                // Get the button that was clicked
//                JButton theButton = (JButton) e.getSource();
//                // Set it's background color to white
//                Color color = theButton.getBackground();
//                if (color==Color.black)
//                {
//                    theButton.setBackground(Color.white);}
//                else{theButton.setBackground(Color.black);
//                }
//                Actions myActionHandler = new Actions();
//
//            }}

        JPanel panelForButtons = new JPanel();
        int buttonNum = size[0] * size[1];
        panelForButtons.setLayout(new GridLayout(size[0], size[1]));

        JButton[] buttons = new JButton[buttonNum];
        buttonsArray = buttons;


        for (int i = 0; i < buttonNum; i++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.WHITE);
	    buttons[i].addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
			btn.setBackground(Color.YELLOW);
		}
	    });



//            Change each button size
           buttons[i].setPreferredSize(new Dimension(50, 50));
            panelForButtons.add(buttons[i]);
        }
        panelOnTheLeft.add(panelForButtons, BorderLayout.WEST);

    }

    public static void main(String[] args) {
     JFrame frame = new JFrame("Maze Builder");
     frame.setSize(1440, 1220);
        JPanel panelOnTheLeft = new JPanel();
        JPanel panelOnTheRight = new JPanel();

   frame.setLayout(new BorderLayout());
     CreateSelectionButtons(frame, panelOnTheLeft,  defaultSize);
     CreateInputSection(frame, panelOnTheLeft, panelOnTheRight);
        frame.add(panelOnTheLeft, BorderLayout.WEST);
        frame.add(panelOnTheRight, BorderLayout.EAST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

       frame.setVisible(true);


    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public
    void actionPerformed(ActionEvent e) {

    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public
    void mouseClicked(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public
    void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public
    void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public
    void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public
    void mouseExited(MouseEvent e) {

    }
}
