package gui;

import components.Deck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Class that represents the GUI
 * @author Christopher de Sousa, Sean Clements
 */
public class UserView extends JFrame implements ActionListener{

    private static Dimension userScreenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private JPanel PokerTable;
    private JPanel Buttons;

    private JButton restartGameButton;
    private JButton callButton;
    private JButton raiseButton;
    private JButton checkButton;
    private JButton foldButton;
    private JButton dealButton;
    private JTextArea betBox;

    private Deck tableCards;

    // Populate view
    public UserView () {

        // Create Pane (Window)
        Container tablePane = getContentPane();
        tablePane.setLayout(new BorderLayout());
        tablePane.setBackground(Color.DARK_GRAY);

        // Pane attributes
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hold'Em Alpha");
        setSize(800, 750);
        setLocation((userScreenSize.width / 2) - (getSize().width / 2), userScreenSize.height / 2 - getSize().height / 2);
        setResizable(false);

        /*
        * JPanel for cards on table
        * */


        // Add panel at bottom of screen for buttons
        Buttons = new JPanel();
        Buttons.setLayout(new GridLayout());

        // Add Buttons panel to window (tablepane)
        tablePane.add(Buttons, BorderLayout.SOUTH);

        /*
        * Restart Button Logic
        *
        * */
        restartGameButton = new JButton("Restart");
        restartGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent restart) {

            }
        });
        Buttons.add(restartGameButton);

        // Call Button attributes & add to Buttons panel
        callButton = new JButton("Call");
        callButton.setBounds(150, 600, 75, 25);
        Buttons.add(callButton);

        // Raise Button attributes & add to Buttons panel
        raiseButton = new JButton("Raise");
        raiseButton.setBounds(250, 600, 75, 25);
        Buttons.add(raiseButton);

        // Check Button attributes & add to Buttons panel
        checkButton = new JButton("Check");
        checkButton.setBounds(350, 600, 75, 25);
        Buttons.add(checkButton);

        // Fold Button attributes & add to Buttons panel
        foldButton = new JButton("Fold");
        foldButton.setBounds(450, 600, 75, 25);
        Buttons.add(foldButton);

    }

    public void display(String string) {

    }

    // Main method
    public static void main(String[] args) {
        new UserView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

