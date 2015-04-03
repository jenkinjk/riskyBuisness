import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RiskBoard {
	private Iterator<Player> itr;
	private ArrayList<Player> players;
	private ArrayList<Territory> territories;
	private Integer[] numPlayerArray = {1, 2, 3, 4, 5, 6};

	public RiskBoard() {
		ArrayList<Player> players = new ArrayList<Player>();
		this.players = players;
		this.itr = players.iterator();
		ArrayList<Territory> territories = new ArrayList<Territory>();
		this.territories = territories;
	}
	
	public void selectNumberOfPlayers() {
		JFrame setUpFrame = new JFrame("Risk");
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JLabel select = new JLabel("Please select number of player(s)");
		panel.add(select, BorderLayout.NORTH);
		
		JComboBox<Integer> box = new JComboBox<>(numPlayerArray);
		box.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg) {
				@SuppressWarnings("unchecked")
				JComboBox<Integer> cb = (JComboBox<Integer>) arg.getSource();
				int num = (int)cb.getSelectedItem();
				initialGame(num);
			}
		});
		panel.add(box, BorderLayout.CENTER);
		
		setUpFrame.add(panel);
		setUpFrame.setSize(300, 150);
		setUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUpFrame.setVisible(true);
		setUpFrame.setResizable(false);
	}
	
	public void initialGame(int numberOfPlayers) {
	}

	private void randomAdd(ArrayList<Territory> territories) {
		// Note, this assumes you have 5 players and 25 territories to be named
		// 1 through 25!
		int p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0, p, count = 0;
		Random rand = new Random();
		while (count < 25) {
			p = rand.nextInt(5);
			if (p == 0 && p1 < 5) {
				territories.add(new Territory(count + 1 + "", players.get(p)));
				p1++;
				count++;
			}
			if (p == 1 && p2 < 5) {
				territories.add(new Territory(count + 1 + "", players.get(p)));
				p2++;
				count++;
			}
			if (p == 2 && p3 < 5) {
				territories.add(new Territory(count + 1 + "", players.get(p)));
				p3++;
				count++;
			}
			if (p == 3 && p4 < 5) {
				territories.add(new Territory(count + 1 + "", players.get(p)));
				p4++;
				count++;
			}
			if (p == 4 && p5 < 5) {
				territories.add(new Territory(count + 1 + "", players.get(p)));
				p5++;
				count++;
			}

		}
	}

	public void display() throws IOException {
		JFrame frame = new JFrame("Risk Board");
		frame.setSize(1000, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBackground(Color.blue);
		JButton NA = new JButton();
		NA.addActionListener(new ContinentListener("North America"));
		NA.setText("North America");

		JButton SA = new JButton();
		SA.addActionListener(new ContinentListener("South America"));
		SA.setText("South America");

		JButton A = new JButton();
		A.setText("Africa");
		A.addActionListener(new ContinentListener("Africa"));

		JButton E = new JButton();
		E.setText("Europe");

		E.addActionListener(new ContinentListener("Europe"));
		JButton D = new JButton();
		D.setText("Austrailia");

		D.addActionListener(new ContinentListener("Austraila"));
		JButton Asia = new JButton();

		Asia.addActionListener(new ContinentListener("Asia"));
		Asia.setText("Asia");
		frame.add(panel);
		panel.add(NA);
		panel.add(SA);
		panel.add(E);
		panel.add(D);
		panel.add(A);
		panel.add(Asia);
	}

	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	public Player getNextPlayer() {
		if (!itr.hasNext())
			this.itr = this.players.iterator();
		return this.itr.next();
	}

	public ArrayList<Territory> getTerritories() {
		return this.territories;
	}
}
