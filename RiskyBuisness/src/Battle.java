import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Battle {

	private Army attacker;
	private Army defender;
	private String title;
	private Integer[] AttackerDiceOptions;
	private Integer[] DefenderDiceOptions;
	protected int AttackerDice;
	protected int DefenderDice;
	

	public Battle(Army a, Army b) throws Exception {
		if(a.getOwner().equals(b.getOwner())) throw new Exception("You cannot attack your own armies.");
		if(!(a.getLocation().getNeighbors().contains(b.getLocation()))) throw new Exception("You cannot attack non adjacent territories.");
		if(a.size()<b.size()) throw new Exception("You cannot attack a larger army.");
		this.attacker = a;
		this.defender = b;
		this.title = "Battle between " + attacker.getOwner().getName() + " and " + defender.getOwner().getName() + " in " + defender.getLocation().getName();
		int size;
		if(attacker.getSize()!=2) size = 3;
		else size = 2;
		Integer[] AttackerDiceOptions=new Integer[size];
		for(int i = 1; i<= size;i++){
			AttackerDiceOptions[i-1]=i;
		}
		this.AttackerDiceOptions=AttackerDiceOptions;
		if(defender.getSize()!=1) size = 2;
		else size = 1;
		Integer[] DefenderDiceOptions=new Integer[size];
		for(int i = 1; i<= size;i++){
			DefenderDiceOptions[i-1]=i;
		}
		this.DefenderDiceOptions=DefenderDiceOptions;
		
	}

	public Battle() {
		RiskBoard board = new RiskBoard();
		board.initialGame(4);
		Player p1 = new Player("Jonathan", Color.green);
		Player p2 = new Player("Zach", Color.red);
		try{
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska, 7);
		Army b = new Army(p2, Kamchatka, 3);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		if(a.getOwner().equals(b.getOwner())) throw new Exception("You cannot attack your own armies.");
		if(!(a.getLocation().getNeighbors().contains(b.getLocation()))) throw new Exception("You cannot attack non adjacent territories.");
		if(a.size()<b.size()) throw new Exception("You cannot attack a larger army.");
		this.attacker = a;
		this.defender = b;
		this.title = "Battle between " + attacker.getOwner().getName() + " and " + defender.getOwner().getName() + " in " + defender.getLocation().getName();
		int size;
		if(attacker.getSize()!=2) size = 3;
		else size = 2;
		Integer[] AttackerDiceOptions=new Integer[size];
		for(int i = 1; i<= size;i++){
			AttackerDiceOptions[i-1]=i;
		}
		this.AttackerDiceOptions=AttackerDiceOptions;
		if(defender.getSize()!=1) size = 2;
		else size = 1;
		Integer[] DefenderDiceOptions=new Integer[size];
		for(int i = 1; i<= size;i++){
			DefenderDiceOptions[i-1]=i;
		}
		this.DefenderDiceOptions=DefenderDiceOptions;
		}catch(Exception e){
			
		}
	}

	public void execute(int attacker, int defender) throws Exception {
		Random gen = new Random();
		if(attacker>this.attacker.size()||defender>this.defender.size()) throw new Exception("You cannot Roll more dice than you have armies.");
		if(defender!=1&&defender!=2) throw new Exception("The defender can only roll 1 or 2 dice.");
		if(attacker!=2&&attacker!=1&&attacker!=3) throw new Exception("The attacker can only roll 1, 2 or 3 dice.");
		int rounds = 0;
		int attkRoll = 0;
		int defRoll = 0;
		while(rounds < defender) {
			int attacks = 0;
			int defends = 0;
			while(attacks < attacker) {
				attkRoll = Math.max(attkRoll, gen.nextInt(6) + 1);
				attacks++;
			}
			while(defends < defender) {
				defRoll = Math.max(defRoll, gen.nextInt(6) + 1);
				defends++;
			}
			if(attkRoll > defRoll) {
				this.defender.takeLosses(1);
			} else {
				this.attacker.takeLosses(1);
			}
			attkRoll = 0;
			defRoll = 0;
			rounds++;
		}
		
	}
	
	@SuppressWarnings("serial")
	public void display() throws IOException {
		JFrame frame = new JFrame(title);
		frame.setSize(1025, 740);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

		JPanel panel = new JPanel() {
			private Image backgroundImage = ImageIO.read(new File("battle.png"));

			public void paint(Graphics g) {
				super.paint(g);
				g.drawImage(backgroundImage, 0, 30, null);
			}
		};
		
		
		JComboBox<Object> boxA = new JComboBox<Object>(AttackerDiceOptions);
		boxA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg) {
				@SuppressWarnings("unchecked")
				JComboBox<Object> cb = (JComboBox<Object>) arg.getSource();
				int num = (int) cb.getSelectedItem();
				AttackerDice = num;
			}
		});
		
		JComboBox<Object> boxD = new JComboBox<Object>(DefenderDiceOptions);
		boxD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg) {
				@SuppressWarnings("unchecked")
				JComboBox<Object> cb = (JComboBox<Object>) arg.getSource();
				int num = (int) cb.getSelectedItem();
				DefenderDice = num;
			}
		});

		panel.setLayout(new GridLayout(10,10));
		panel.add(boxA, 30);
		panel.add(boxD, 39);
		frame.setContentPane(panel);
		panel.setFocusable(true);
		panel.revalidate();
		frame.revalidate();
		frame.repaint();
	}
	//For test only
	public String getTitle() {
		return title;
	}

	//for test only
	public Integer[] getAttackerOptions() {
		return this.AttackerDiceOptions;
		
	}
	
	//for test only
	public Integer[] getDefenderOptions() {
		return this.DefenderDiceOptions;
	}

}
