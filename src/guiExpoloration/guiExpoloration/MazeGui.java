package guiExpoloration;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MazeGui extends JFrame implements ActionListener, MouseListener {

    private static int[] defaultSize = {10, 10};
    public static JButton[] buttonsArray;



    public static void CreateInputSection(JFrame frame, JPanel panelOnTheLeft, JPanel panelOnTheRight, JToggleButton start, JToggleButton end) {
        int[] size = new int[10];

        JLabel label1ForInput = new JLabel("Rows:");
        JLabel label2ForInput = new JLabel("Columns:");

        JTextField text1ForInput = new JTextField(5);
        JTextField text2ForInput = new JTextField(5);

        JButton buttonForInput = new JButton("Submit");
        buttonForInput.setBounds(50, 50, 100, 100);

        JButton buttonRandom = new JButton("Random");
        buttonRandom.setBounds(50, 50, 100, 100);
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

        // Generate a maze randomly
        buttonRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
// Get the button that was clicked
                if(end.isSelected()){JOptionPane.showMessageDialog(buttonForInput, "please stop the end function first!",
                        "Invalid Input!", JOptionPane.ERROR_MESSAGE);
                    start.setSelected(false);}

            }});
        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
// Get the button that was clicked
                if(start.isSelected()){JOptionPane.showMessageDialog(buttonForInput, "please stop the start function first!",
                        "Invalid Input!", JOptionPane.ERROR_MESSAGE);
                    end.setSelected(false);}
            }});

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
                                                 CreateSelectionButtons(frame, panelOnTheLeft, size, end,start);
                                                 panelOnTheLeft.revalidate();
                                                 panelOnTheLeft.repaint();
                                             }
                                         }
        );
        buttonExport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

/*
                Scanner myObj = new Scanner(System.in);  // Create a Scanner object
                System.out.println("File name: ");

                String imageName = myObj.nextLine();  // Read user input
**/

                try {
                    Robot robot = new Robot();
                    String format = "jpg";
                    String fileName = "Screenshot." + format;

                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    Rectangle captureRect = new Rectangle(0, 0, screenSize.width / 2, screenSize.height/2);
                    BufferedImage screenFullImage = robot.createScreenCapture(captureRect);
                    ImageIO.write(screenFullImage, format, new File(fileName));

                    System.out.println("A screenshot has been saved");
                } catch (AWTException | IOException ex) {
                    System.err.println(ex);
                }
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
        panelOnTheRight.add(buttonRandom);
        panelOnTheRight.add(buttonGenerate);
        panelOnTheRight.add(buttonExport);
        panelOnTheRight.add(start);
        panelOnTheRight.add(end);
        panelOnTheRight.add(buttonReset);
        panelOnTheRight.add(buttonUpload);

    }
//    public static void JScrollBar(){
//        JScrollBar scrollB1 = new JScrollBar(JScrollBar.HORIZONTAL, 1000, 40, 0, 100);
//        JScrollBar scrollB2 = new JScrollBar(JScrollBar.VERTICAL, 1000, 60, 0, 100);
//    }






        public static void CreateSelectionButtons(JFrame frame, JPanel panelOnTheLeft, int[] size, AbstractButton start, AbstractButton end) {
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
//
            JPanel panelForButtons = new JPanel();
            int buttonNum = size[0] * size[1];
            panelForButtons.setLayout(new GridLayout(size[0], size[1]));
            JButton[] buttons = new JButton[buttonNum];
            buttonsArray = buttons;
            class Actions implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                    // Get the button that was clicked
                    JButton theButton = (JButton) e.getSource();

                    // Set it's background color to white
                    Color color = theButton.getBackground();
                    if(start.isSelected()){
                        for(int i=0;i<buttonNum;i++){
                            Color check=buttons[i].getBackground();
                            if(check==Color.green){
                                buttons[i].setBackground(Color.white);

                            }
                        }
                        theButton.setBackground(Color.green);}
                    else if(end.isSelected()){
                        for(int i=0;i<buttonNum;i++){
                            Color check=buttons[i].getBackground();
                            if(check==Color.red){
                                buttons[i].setBackground(Color.white);

                            }
                        }
                        theButton.setBackground(Color.red);}
                    else if (color==Color.black)
                    {
                        theButton.setBackground(Color.white);}
                    else{theButton.setBackground(Color.black);
                    }

                }}




// The only action handler you need
            int[] defaultSize = {10, 10};
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
     JFrame frame = new JFrame("Maze Builder");
     frame.setSize(1440, 1220);
        JPanel panelOnTheLeft = new JPanel();
        JPanel panelOnTheRight = new JPanel();
        JToggleButton start = new JToggleButton("start block");
        start.setBounds(50, 50, 100, 100);

        JToggleButton end = new JToggleButton("end block");
        end.setBounds(50, 50, 100, 100);


   frame.setLayout(new BorderLayout());
        int[] defaultSize = {10, 10};
        CreateSelectionButtons(frame, panelOnTheLeft,defaultSize, start,end);
     CreateInputSection(frame, panelOnTheLeft, panelOnTheRight,start,end);
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
