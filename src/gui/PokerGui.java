package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 
 * @author Sean Clements, Christopher de Sousa
 *
 */
public class PokerGui extends JFrame {

	private static final Insets insets = new Insets(0, 0, 0, 0);
	private JFrame table;
	private JPanel gui, totalPanel, topMainLayer, topLayer, messageDisplay, cardMainPanel, cardTopPanel, computerCard1,
			computerCard2, cardMidPanel, flopCard1, flopCard2, flopCard3, turnCard, riverCard, cardLowPanel,
			playerCard1, playerCard2, buttonPanel;
	private JLabel playerStash, pot, computerStash, p1, p2, c1, c2, f1, f2, f3, t, r;
	private JTextArea messageLabel;
	private JButton call, check, raise, fold;
	private final String DEFCARD = "resources/images/_Back.png";
	private boolean performed;
	private int decision;
	private Font font = new Font(Font.SERIF, Font.BOLD, 20);

	/**
	 * simple constructor, starts the populating process
	 */
	public PokerGui() {
		createContent();

	}

	/**
	 * Overall content creation method, makes panels and populates them with components
	 */
	public void createContent() {
		table = new JFrame();
		totalPanel = new JPanel(new GridBagLayout());
		topMainLayer = new JPanel();
		topMainLayer.setLayout(new GridBagLayout());
		topLayer = new JPanel();
		topLayer.setLayout(new GridBagLayout());
		playerStash = new JLabel("Player: 0");
		playerStash.setHorizontalAlignment(JLabel.CENTER);
		pot = new JLabel("Pot: 0");
		pot.setHorizontalAlignment(JLabel.CENTER);
		computerStash = new JLabel("Computer: 0");
		computerStash.setHorizontalAlignment(JLabel.CENTER);

		addComponent(topLayer, playerStash, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(topLayer, pot, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(topLayer, computerStash, 2, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

		messageDisplay = new JPanel(); // message display window
		messageDisplay.setBackground(Color.DARK_GRAY);
		messageLabel = new JTextArea("Welcome to Texas Hold'Em");
		messageLabel.setEditable(false);
		messageLabel.setFont(font);
		messageDisplay.add(messageLabel);

		addComponent(topMainLayer, topLayer, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(topMainLayer, messageDisplay, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

		cardMainPanel = new JPanel(new GridBagLayout());
		cardMainPanel.setBackground(Color.DARK_GRAY);
		cardTopPanel = new JPanel();
		cardTopPanel.setBackground(Color.DARK_GRAY);
		computerCard1 = new JPanel();
		computerCard1.setBackground(Color.DARK_GRAY);
		c1 = new JLabel(createImage(DEFCARD));
		computerCard1.add(c1);
		computerCard2 = new JPanel();
		computerCard2.setBackground(Color.DARK_GRAY);
		c2 = new JLabel(createImage(DEFCARD));
		computerCard2.add(c2);
		cardTopPanel.add(computerCard1);
		cardTopPanel.add(computerCard2);

		cardMidPanel = new JPanel();
		cardMidPanel.setBackground(Color.DARK_GRAY);
		flopCard1 = new JPanel();
		flopCard1.setBackground(Color.DARK_GRAY);
		f1 = new JLabel(createImage(DEFCARD));
		flopCard1.add(f1);
		flopCard2 = new JPanel();
		flopCard2.setBackground(Color.DARK_GRAY);
		f2 = new JLabel(createImage(DEFCARD));
		flopCard2.add(f2);
		flopCard3 = new JPanel();
		flopCard3.setBackground(Color.DARK_GRAY);
		f3 = new JLabel(createImage(DEFCARD));
		flopCard3.add(f3);
		turnCard = new JPanel();
		turnCard.setBackground(Color.DARK_GRAY);
		t = new JLabel(createImage(DEFCARD));
		turnCard.add(t);
		riverCard = new JPanel();
		riverCard.setBackground(Color.DARK_GRAY);
		r = new JLabel(createImage(DEFCARD));
		riverCard.add(r);
		cardMidPanel.add(flopCard1);
		cardMidPanel.add(flopCard2);
		cardMidPanel.add(flopCard3);
		cardMidPanel.add(turnCard);
		cardMidPanel.add(riverCard);

		cardLowPanel = new JPanel();
		cardLowPanel.setBackground(Color.DARK_GRAY);
		playerCard1 = new JPanel();
		playerCard1.setBackground(Color.DARK_GRAY);
		p1 = new JLabel(createImage(DEFCARD));
		playerCard1.add(p1);
		playerCard2 = new JPanel();
		playerCard2.setBackground(Color.DARK_GRAY);
		p2 = new JLabel(createImage(DEFCARD));
		playerCard2.add(p2);
		cardLowPanel.add(playerCard1);
		cardLowPanel.add(playerCard2);

		addComponent(cardMainPanel, cardTopPanel, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(cardMainPanel, cardMidPanel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(cardMainPanel, cardLowPanel, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

		buttonPanel = new JPanel();
		call = new JButton("Call");
		call.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Call");
                decision = 1;
                performed = true;
            }
        });
		check = new JButton("Check");
		check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Check");
                decision = 2;
                performed = true;
            }
        });
		raise = new JButton("Raise");
		raise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Raise");
                decision = 3;
                performed = true;
            }
        });
		fold = new JButton("Fold");
		fold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Fold");
                decision = 4;
                performed = true;
            }
        });
		buttonPanel.add(call);
		buttonPanel.add(check);
		buttonPanel.add(raise);
		buttonPanel.add(fold);

		addComponent(totalPanel, topMainLayer, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(totalPanel, cardMainPanel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(totalPanel, buttonPanel, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

		table = new JFrame("Texas Hold'Em");
		table.add(totalPanel);
		table.setBackground(Color.DARK_GRAY);
		table.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.pack();
		table.setVisible(true);

	}

	/**
	 * Simplifies adding components and setting their gridConstraints
	 * @param container
	 * @param component
	 * @param gridx
	 * @param gridy
	 * @param gridwidth
	 * @param gridheight
	 * @param anchor
	 * @param fill
	 */
	private static void addComponent(Container container, Component component, int gridx, int gridy, int gridwidth,
			int gridheight, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0, anchor, fill,
				insets, 0, 0);
		container.add(component, gbc);
	}

	/**
	 * uses given card position and path location to change the image on the table
	 * @param card
	 * @param location
	 */
	public void changeCard(int card, String location) {
		switch (card) {
		case 1:
			playerCard1.removeAll();
			p1 = new JLabel(createImage(location));
			playerCard1.add(p1);
			break;
		case 2:
			playerCard2.removeAll();
			p2 = new JLabel(createImage(location));
			playerCard2.add(p2);
			break;
		case 3:
			computerCard1.removeAll();
			c1 = new JLabel(createImage(location));
			computerCard1.add(c1);
			break;
		case 4:
			computerCard2.removeAll();
			c2 = new JLabel(createImage(location));
			computerCard2.add(c2);
			break;
		case 5:
			flopCard1.removeAll();
			f1 = new JLabel(createImage(location));
			flopCard1.add(f1);
			break;
		case 6:
			flopCard2.removeAll();
			f2 = new JLabel(createImage(location));
			flopCard2.add(f2);
			break;
		case 7:
			flopCard3.removeAll();
			f3 = new JLabel(createImage(location));
			flopCard1.add(f3);
			break;
		case 8:
			turnCard.removeAll();
			t = new JLabel(createImage(location));
			turnCard.add(t);
			break;
		case 9:
			riverCard.removeAll();
			r = new JLabel(createImage(location));
			riverCard.add(r);
			break;
		}
	}

	//Getters and setter for gui values
	
	public void currentPlayerChips(int chips) {
		playerStash.setText("Player: " + chips);
	}

	public void currentComputerChips(int chips) {
		computerStash.setText("Computer: " + chips);
	}

	public void currentPot(int chips) {
		pot.setText("Pot: " + chips);
	}

	public void display(String message) {
		messageLabel.setText(message);
	}

	public void setPlayerActionPerformed(boolean performed) {
		this.performed = performed;
	}

	public boolean getPlayerActionPerformed() {
		return performed;
	}

	public int getPlayerDecision() {
		return decision;
	}
	
	/**
	 * method that takes path location and returns scaled image icon 
	 * @param location
	 * @return
	 */
	private ImageIcon createImage(String location){
		ImageIcon image = new ImageIcon(location);
		BufferedImage resizedImage = new BufferedImage(140, 200, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImage.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(image.getImage(), 0, 0, 140, 200, null);
	    g2.dispose();

	    return new ImageIcon(resizedImage);
	}

}