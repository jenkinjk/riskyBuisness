import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RiskBoard {
	private Iterator<Player> itr;
	private ArrayList<Player> players;

	public RiskBoard() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Player One"));
		players.add(new Player("Player Two"));
		players.add(new Player("Player Three"));
		players.add(new Player("Player Four"));
		players.add(new Player("Player Five"));
		this.players = players;
		this.itr = players.iterator();
	}

	public static void display() throws IOException {
		JFrame frame = new JFrame("Risk Board");
		frame.setSize(1000, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
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

	public void setUp() {
		// TODO Auto-generated method stub

	}

	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	public Player getNextPlayer() {
		if (!itr.hasNext())
			this.itr = this.players.iterator();
		return this.itr.next();
	}
}
