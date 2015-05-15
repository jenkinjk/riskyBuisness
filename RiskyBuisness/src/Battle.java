import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Battle {

	private Army attacker;
	private Army defender;
	private String title;
	private Integer[] AttackerDiceOptions;
	private Integer[] DefenderDiceOptions;
	protected int AttackerDice = 1;
	protected int DefenderDice = 1;
	protected boolean attackerWon = false;
	private RiskBoard callBoard;

	public Battle(Army a, Army b) throws Exception {
		if (a.getOwner().equals(b.getOwner()))
			throw new Exception("You cannot attack your own armies.");
		if (!(a.getArmyLocation().getNeighbors().contains(b.getArmyLocation())))
			throw new Exception("You cannot attack non adjacent territories.");
		if (a.getArmySize() < b.getArmySize())
			throw new Exception("You cannot attack a larger army.");
		this.attacker = a;
		this.defender = b;
		this.title = "Battle between " + attacker.getOwner().getName()
				+ " and " + defender.getOwner().getName() + " in "
				+ defender.getArmyLocation().getName();
		this.AttackerDiceOptions = calcAttackerDiceOptions();
		this.DefenderDiceOptions = calcDefenderDiceOptions();
		this.callBoard = b.getBoard();
	}

	private Integer[] calcDefenderDiceOptions() {
		int size;
		if (defender.getArmySize() != 1)
			size = 2;
		else
			size = 1;
		Integer[] DefenderDiceOptions = new Integer[size];
		for (int i = 1; i <= size; i++) {
			DefenderDiceOptions[i - 1] = i;
		}
		return DefenderDiceOptions;
	}

	private Integer[] calcAttackerDiceOptions() {
		int size;
		if (attacker.getArmySize() != 2)
			size = 3;
		else
			size = 2;
		Integer[] AttackerDiceOptions = new Integer[size];
		for (int i = 1; i <= size; i++) {
			AttackerDiceOptions[i - 1] = i;
		}
		return AttackerDiceOptions;
		//TODO: Tests should be edited for this control logic -
//		int size;
//		if (attacker.getArmySize() != 2 && attacker.getArmySize() != 3)
//			size = 4;
//		else if (attacker.getArmySize() == 3)
//			size = 3;
//		else
//			size = 2;
//		Integer[] AttackerDiceOptions = new Integer[size];
//		for (int i = 1; i < size; i++) {
//			AttackerDiceOptions[i - 1] = i;
//		}
//		return AttackerDiceOptions;
	}

	public Battle() {
		RiskBoard board = new RiskBoard();
		board.initialGame(4);
		Player p1 = new Player("Jonathan", Color.green);
		Player p2 = new Player("Zach", Color.red);
		try {
			Territory Alaska = board.getTerritoryNamed("Alaska");
			Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
			Army a = new Army(p1, Alaska, 7);
			Army b = new Army(p2, Kamchatka, 3);
			p1.addArmy(a);
			p2.addArmy(b);
			p1.addTerritory(Alaska);
			p2.addTerritory(Kamchatka);
			if (a.getOwner().equals(b.getOwner()))
				throw new Exception("You cannot attack your own armies.");
			if (!(a.getArmyLocation().getNeighbors().contains(b.getArmyLocation())))
				throw new Exception(
						"You cannot attack non adjacent territories.");
			if (a.getArmySize() < b.getArmySize())
				throw new Exception("You cannot attack a larger army.");
			this.attacker = a;
			this.defender = b;
			this.title = "Battle between " + attacker.getOwner().getName()
					+ " and " + defender.getOwner().getName() + " in "
					+ defender.getArmyLocation().getName();
			this.AttackerDiceOptions = calcAttackerDiceOptions();
			this.DefenderDiceOptions = calcDefenderDiceOptions();
		} catch (Exception e) {

		}
	}

	public void execute(int attacker, int defender) throws Exception {
		Random gen = new Random();
		if (attacker > this.attacker.getArmySize() || defender > this.defender.getArmySize())
			throw new Exception(
					"You cannot Roll more dice than you have armies.");
		if (defender != 1 && defender != 2)
			throw new Exception("The defender can only roll 1 or 2 dice.");
		if (attacker != 2 && attacker != 1 && attacker != 3)
			throw new Exception("The attacker can only roll 1, 2 or 3 dice.");
		int rounds = 0;
		int attkRoll = 0;
		int defRoll = 0;
		while (rounds < defender) {
			int attacks = 0;
			int defends = 0;
			while (attacks < attacker) {
				attkRoll = Math.max(attkRoll, gen.nextInt(6) + 1);
				attacks++;
			}
			while (defends < defender) {
				defRoll = Math.max(defRoll, gen.nextInt(6) + 1);
				defends++;
			}
			if (attkRoll > defRoll) {
				this.defender.takeLosses(1);
			} else {
				this.attacker.takeLosses(1);
			}
			attkRoll = 0;
			defRoll = 0;
			rounds++;
		}

	}

	final JFrame frame = new JFrame(title);
	@SuppressWarnings("serial")
	public void display() throws IOException {
		final RiskBoard localBoard = this.callBoard;
		frame.setSize(1025, 740);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		JPanel panel = new JPanel() {
			private Image backgroundImage = ImageIO
					.read(new File("battle.png"));

			public void paint(Graphics g) {
				super.paint(g);
				g.drawImage(backgroundImage, 0, 0, null);
				for (Component c : this.getComponents()) {
					c.repaint();
				}
			}
		};

		final JComboBox<Integer> boxA = new JComboBox<Integer>(
				AttackerDiceOptions);
		boxA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg) {
				@SuppressWarnings("unchecked")
				JComboBox<Object> cb = (JComboBox<Object>) arg.getSource();
				int num = (int) cb.getSelectedItem();
				// TODO: Need to check attacker.getArmySize() to decide what happens 
				// when an attacker runs out of armies.
				AttackerDice = num;
			}
		});

		final JComboBox<Integer> boxD = new JComboBox<Integer>(
				DefenderDiceOptions);
		boxD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg) {
				@SuppressWarnings("unchecked")
				JComboBox<Object> cb = (JComboBox<Object>) arg.getSource();
				int num = (int) cb.getSelectedItem();
				DefenderDice = num;
			}
		});

		Label defenderLabel = new Label(
				"Select the number of dice to roll for the defender.");

		Label attackerLabel = new Label(
				"Select the number of dice to roll for the attacker.");

		final Label defenderSize = new Label("The defender has "
				+ defender.getArmySize() + " armies left.");

		final Label attackerSize = new Label("The attacker has "
				+ attacker.getArmySize() + " armies left.");

		final Label derictions = new Label("Do you wish to continue?");

		Button endBattle = new Button("Retreat");
		endBattle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				resolveCombat();
				if(attackerWon) {
					getArmiesToSend();
				} else {
					localBoard.getFrame().setVisible(true);
					localBoard.getFrame().revalidate();
					localBoard.getFrame().repaint();
				}
			}

			private void getArmiesToSend() {
				final JFrame setUpFrame = new JFrame("Army Splitting");
				JPanel panel = new JPanel();
				panel.setLayout(new BorderLayout());

				JLabel select = new JLabel("Please select the number of armies you wish to send to the conquered territory.");
				panel.add(select, BorderLayout.NORTH);
				
				Integer[] armiesToSendOptions = new Integer[attacker.getArmySize()-1];
				for (int i = 1; i <= attacker.getArmySize()-1; i++) {
					armiesToSendOptions[i - 1] = i;
				}

				JComboBox<Integer> box = new JComboBox<>(armiesToSendOptions);
				box.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg) {
						@SuppressWarnings("unchecked")
						JComboBox<Integer> cb = (JComboBox<Integer>) arg
								.getSource();
						int numberToSend = (int) cb.getSelectedItem();
						conquer(numberToSend);
						// TODO: Winner needs to get a card.
						try {
							localBoard.giveWinnerCard(attacker.getOwner());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						localBoard.getFrame().setVisible(true);
						localBoard.getFrame().revalidate();
						localBoard.getFrame().repaint();
						setUpFrame.dispose();
					}

				});
				panel.add(box, BorderLayout.CENTER);

				setUpFrame.add(panel);
				setUpFrame.setSize(500, 150);
				setUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setUpFrame.setVisible(true);
				setUpFrame.setResizable(false);

			}

		});

		final Button fight = new Button("Fight");
		fight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					execute(AttackerDice, DefenderDice);
					defenderSize.setText("The defender has "
							+ defender.getArmySize() + " armies left.");
					attackerSize.setText("The attacker has "
							+ attacker.getArmySize() + " armies left.");
					DefenderDiceOptions = calcDefenderDiceOptions();
					AttackerDiceOptions = calcAttackerDiceOptions();
					updateAttackerAndDefenderOptions();
					if (defender.getArmySize() == 0) {
						derictions.setText(attacker.getOwner().getName()
								+ " has won!");
						fight.disable();
						attackerWon = true;
					}
					if (attacker.getArmySize() < defender.getArmySize()
							|| attacker.getArmySize() == 1) {
						derictions.setText(defender.getOwner().getName()
								+ " has won!");
						fight.disable();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			private void updateAttackerAndDefenderOptions() {
				for (int i = 0; i < boxA.getItemCount(); i++) {
					if (!contains(AttackerDiceOptions, boxA.getItemAt(i))) {
						boxA.removeItemAt(i);
						break;
					}
				}
				for (int i = 0; i < boxD.getItemCount(); i++) {
					if (!contains(DefenderDiceOptions, boxD.getItemAt(i))) {
						boxD.removeItemAt(i);
						break;
					}
				}
			}

			private boolean contains(Integer[] array, Integer itemAt) {
				for (Integer i : array) {
					if (i == itemAt)
						return true;
				}
				return false;
			}

		});

		boxA.setBounds(100, 100, 100, 35);
		boxD.setBounds(frame.getWidth() - 200, 100, 100, 35);
		attackerLabel.setBounds(0, 60, 275, 35);
		defenderLabel.setBounds(frame.getWidth() - 275, 60, 275, 35);
		defenderSize.setBounds(frame.getWidth() - 240, 140, 175, 35);
		attackerSize.setBounds(60, 140, 175, 35);
		fight.setBounds(1025 / 2 - 25, 600, 50, 35);
		derictions.setBounds(1025 / 2 - 75, 500, 150, 35);
		endBattle.setBounds(1025 / 2 - 25, 550, 50, 35);

		panel.setLayout(null);

		panel.add(boxA);
		panel.add(boxD);
		panel.add(defenderLabel);
		panel.add(attackerLabel);
		panel.add(defenderSize);
		panel.add(attackerSize);
		panel.add(fight);
		panel.add(endBattle);
		panel.add(derictions);

		frame.setContentPane(panel);
		frame.revalidate();
		frame.repaint();
		panel.revalidate();
		panel.repaint();
	}

	// For test only
	public String getTitle() {
		return title;
	}

	// for test only
	public Integer[] getAttackerOptions() {
		return this.AttackerDiceOptions;

	}

	// for test only
	public Integer[] getDefenderOptions() {
		return this.DefenderDiceOptions;
	}
	// for test only
	void resolveCombat() {
		if (attackerWon){
			defender.getOwner().removeTerritory(defender.getArmyLocation());
			attacker.getOwner().addTerritory(defender.getArmyLocation());
			
		}
	}
	
	// for test only
	void conquer(int numberToSend) {
		defender.setOwner(attacker.getOwner());
		defender.setArmySize(numberToSend);
		attacker.setArmySize(attacker.getArmySize()-numberToSend);
	}
	
	public JFrame getFrame() {
		return this.frame;
	}

}
