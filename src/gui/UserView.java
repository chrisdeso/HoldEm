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

    private JPanel PokerTable = new JPanel();
    private JPanel Buttons = new JPanel();

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

        Container tablePane = getContentPane();
        tablePane.setLayout(null);
        tablePane.setBackground(Color.DARK_GRAY);

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hold'Em Alpha");
        setSize(800, 750);
        setLocation((userScreenSize.width / 2) - (getSize().width / 2), userScreenSize.height / 2 - getSize().height / 2);
        setResizable(false);

        restartGameButton = new JButton("Restart");
        restartGameButton.setBounds(50, 600, 75, 25);
        //restartGameButton.addActionListener(this);
        tablePane.add(restartGameButton);

        callButton = new JButton("Call");
        callButton.setBounds(150, 600, 75, 25);
        tablePane.add(callButton);

        raiseButton = new JButton("Raise");
        raiseButton.setBounds(250, 600, 75, 25);
        tablePane.add(raiseButton);

        checkButton = new JButton("Check");
        checkButton.setBounds(350, 600, 75, 25);
        tablePane.add(checkButton);

        foldButton = new JButton("Fold");
        foldButton.setBounds(450, 600, 75, 25);
        tablePane.add(foldButton);

    }

    // Main method
    public static void main(String[] args) {
        new UserView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

