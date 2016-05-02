package gui;

import components.Chips;
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

    private JPanel Buttons;
    private JPanel tableCards;
    private JPanel playerCards;
    private JPanel computerCards;
    private JPanel infoPanel;

    private JButton restartGameButton;
    private JButton callButton;
    private JButton raiseButton;
    private JButton checkButton;
    private JButton foldButton;
    private JButton dealButton;
    private JTextArea betBox;

    private Deck cards;

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
        setResizable(true);

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
                dispose();
                new UserView();
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
                playerDecision = 4;
                performed = true;
            }
        });
        Buttons.add(foldButton);

        /*
        * Table Card Panel
        * */
        tableCards = new JPanel(new BorderLayout());
        tablePane.add(tableCards);

        /*
        * Table card images
        * */


        /*
        * Player Card Panel
        * */
        playerCards = new JPanel(new GridLayout());
        tablePane.add(playerCards);


        /*
        * AI Card Panel
        * */
        computerCards = new JPanel(new GridLayout());
        tablePane.add(computerCards);

        /*
        * Panel & buttons for pot & chips
        * */
        infoPanel = new JPanel(new GridLayout());

        JButton playerBank = new JButton("Player: ");
        playerBank.setBorderPainted(false);
        playerBank.setFocusPainted(false);
        infoPanel.add(playerBank);

        JButton computerBank = new JButton("Computer: ");
        computerBank.setBorderPainted(false);
        computerBank.setFocusPainted(false);
        infoPanel.add(computerBank);

        JButton totalPot = new JButton("Pot: ");
        totalPot.setBorderPainted(false);
        totalPot.setFocusPainted(false);
        infoPanel.add(totalPot);

        tablePane.add(infoPanel, BorderLayout.NORTH);

    }

    public void display(String string) {

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
        ImageIcon flop1 = new ImageIcon(string);
        Image flop1Img = flop1.getImage();
        flop1Img.getScaledInstance(100, 150, Image.SCALE_DEFAULT);
        ImageIcon finalFlop1 = new ImageIcon(flop1Img);
        tableCards.add(new JLabel(finalFlop1));
    }

    public void changeFlop2(String string) {
        ImageIcon flop2 = new ImageIcon(string);
        Image flop2Img = flop2.getImage();
        flop2Img.getScaledInstance(100, 150, Image.SCALE_DEFAULT);
        ImageIcon finalFlop2 = new ImageIcon(flop2Img);
        tableCards.add(new JLabel(finalFlop2));
    }

    public void changeFlop3(String string) {
        ImageIcon flop3 = new ImageIcon(string);
        Image flop3Img = flop3.getImage();
        flop3Img.getScaledInstance(100, 150, Image.SCALE_DEFAULT);
        ImageIcon finalFlop3 = new ImageIcon(flop3Img);
        tableCards.add(new JLabel(finalFlop3));
    }


    public void changeTurn(String string) {
        ImageIcon turn = new ImageIcon(string);
        Image turnImg = turn.getImage();
        turnImg.getScaledInstance(100, 150, Image.SCALE_DEFAULT);
        ImageIcon finalTurn = new ImageIcon(turnImg);
        tableCards.add(new JLabel(finalTurn));
    }

    public void changeRiver(String string) {
        ImageIcon river = new ImageIcon(string);
        Image riverImg = river.getImage();
        riverImg.getScaledInstance(100, 150, Image.SCALE_DEFAULT);
        ImageIcon finalRiver = new ImageIcon(riverImg);
        tableCards.add(new JLabel(finalRiver));
    }

    public void changeAiHand1(String string) {
        ImageIcon aiHand1 = new ImageIcon(string);
        Image ai1Image = aiHand1.getImage();
        ai1Image.getScaledInstance(100, 150, Image.SCALE_DEFAULT);
        ImageIcon finalAi1 = new ImageIcon(ai1Image);
        computerCards.add(new JLabel(finalAi1));
        //tableCards.add(new JLabel(aiHand1));
    }

    public void changeAiHand2(String string) {
        ImageIcon aiHand2 = new ImageIcon(string);
        //tableCards.add(new JLabel(aiHand2));
    }

    public void changePlayerHand1(String string) {
        ImageIcon playerHand1 = new ImageIcon(string);
        //tableCards.add(new JLabel(playerHand1));
    }

    public void changePlayerHand2(String string) {
        ImageIcon playerHand2 = new ImageIcon(string);
        //tableCards.add(new JLabel(playerHand2));
    }

    /*
    * Methods for passing Player & Computer chip amounts
    * */
    public int currentPlayerChips(Integer num) {
        int playerChips = Chips.getPlayerChips();
        return playerChips;
    }

    public int currentComputerChips(Integer num) {
        int computerChips = Chips.getComputerChips();
        return computerChips;
    }
}