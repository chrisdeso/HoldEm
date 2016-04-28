package gui;

import components.Deck;

import javax.swing.*;
import java.awt.*;

/**
 * Class that represents the GUI
 * @author Christopher de Sousa, Sean Clements
 */
public class UserView extends JFrame {

    private static Dimension userScreenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private JFrame PokerTable = new JFrame();
    private JButton restartGameButton = new JButton();
    private JButton callButton = new JButton();
    private JButton raiseButton = new JButton();
    private JButton checkButton = new JButton();
    private JButton foldButton = new JButton();
    private JButton dealButton = new JButton();
    private JTextArea betBox = new JTextArea();
    //private Deck tableCards = new Deck();

    // Populate view
    public UserView () {

        PokerTable.setTitle("Hold'Em Alpha");
        PokerTable.setSize(1000, 600);
        PokerTable.setVisible(true);
        // Gets user screen size and populates HoldEm window in the middle
        PokerTable.setLocation((userScreenSize.width / 2) - (PokerTable.getSize().width / 2),
                userScreenSize.height / 2 - PokerTable.getSize().height / 2);
        PokerTable.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /*//Buttons Panel
        JPanel buttons = new JPanel();
        buttons.add(restartGameButton);
*/
    }

    private JButton restartGameButton() {
        restartGameButton.setText("Restart Game");
        restartGameButton.setVisible(false);
        restartGameButton.setForeground(Color.WHITE);
        restartGameButton.setBounds(30, 50, 50, 50);
        restartGameButton.setOpaque(false);
        restartGameButton.setContentAreaFilled(false);
        return restartGameButton;
    }

    private JButton callButton() {
        return callButton;
    }

    private JButton raiseButton() {
        return raiseButton;
    }

    private JButton checkButton() {
        return checkButton;
    }

    private JButton foldButton() {
        return foldButton;
    }

    private JButton dealButton() {
        return dealButton;
    }
    // Main method
    public static void main(String[] args) {
        new UserView();
    }
}

