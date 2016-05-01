package gui;

import components.Deck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private boolean action;
    private boolean performed;
    private int playerDecision;

    // Populate view
    public UserView () {

        /*
        * Create window pane
        * */
        Container tablePane = getContentPane();
        tablePane.setLayout(new BorderLayout());
        tablePane.setBackground(Color.DARK_GRAY);

        /*
        * Window attributes
        * */
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hold'Em Alpha");
        setSize(800, 750);
        setLocation((userScreenSize.width / 2) - (getSize().width / 2), userScreenSize.height / 2 - getSize().height / 2);
        setResizable(false);

        /*
        * JPanel for cards on table
        * @TODO
        * */


        /*
        * Add panel with buttons (bottom of window)
        * */
        Buttons = new JPanel();
        Buttons.setLayout(new GridLayout());


        /*
        * Add Buttons panel to window (tablePane)
        * */
        tablePane.add(Buttons, BorderLayout.SOUTH);

        /*
        * Restart Button Logic
        * Add to Buttons panel
        * */
        restartGameButton = new JButton("Restart");
        restartGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent restart) {
                System.out.println("Restart");
                // @TODO Add logic
            }
        });
        Buttons.add(restartGameButton);

        /*
        * Call Button attributes
        * Add to Buttons panel
        * */
        // Call Button attributes & add to Buttons panel
        callButton = new JButton("Call");
        callButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent call) {
                System.out.println("Call");
                // @TODO Add logic
                playerDecision = 1;
                performed = true;
            }
        });
        Buttons.add(callButton);

        /*
        * Check Button attributes
        * Add to Buttons panel
        * */
        checkButton = new JButton("Check");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Check");
                // @TODO Add logic
                playerDecision = 2;
                performed = true;
            }
        });
        Buttons.add(checkButton);


        /*
        * Raise Button attributes
        * Add to Buttons panel
        * */
        // Raise Button attributes & add to Buttons panel
        raiseButton = new JButton("Raise");
        raiseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent raise) {
                System.out.println("Raise");
                // @TODO Add logic
                playerDecision = 3;
                performed = true;
            }
        });
        Buttons.add(raiseButton);

        /*
        * Fold Button attributes
        * Add to Buttons panel
        * */
        foldButton = new JButton("Fold");
        foldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Fold");
                // @TODO Add logic
                playerDecision = 4;
                performed = true;
            }
        });
        Buttons.add(foldButton);


        /*
        * Player Card Panel
        * @TODO add
        * */
        JPanel playerCards = new JPanel();

        /*
        * AI Card Panel
        * @TODO add
        * */

        /*
        * Message Panel
        * */
        JPanel displayPanel = new JPanel();


    }

    public void display(String string) {

    }

    /*
    * Main Method
    * */
    public static void main(String[] args) {
        new UserView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /*
    * Set + Get methods for action state
    * */
    public void setPendingAction(boolean action) {
        this.action = true;
    }

    public boolean getPendingAction() {
        return action;
    }

    public void setPlayerActionPerformed(boolean performed) {
        this.performed = true;
    }

    public boolean getPlayerActionPerformed() {
        return performed;
    }

    public void setPlayerDecision(int decision) {
        this.playerDecision = decision;
    }

    public int getPlayerDecision() {
        return playerDecision;
    }


    /*
    * Methods to pass card image changes for all cards on table
    * */
    public void changeFlop1(String string) {

    }

    public void changeFlop2(String string) {

    }

    public void changeFlop3(String string) {

    }

    public void changeTurn(String string) {

    }

    public void changeRiver(String string) {

    }

    public void changeAiHand1(String string) {

    }

    public void changeAiHand2(String string) {

    }

    public void changePlayerHand1(String string) {

    }

    public void changePlayerHand2(String string) {

    }

    /*
    * Methods for passing Player & Computer chip amounts
    * */
    public void currentPlayerChips(Integer num) {

    }

    public void currentComputerChips(Integer num) {

    }
}