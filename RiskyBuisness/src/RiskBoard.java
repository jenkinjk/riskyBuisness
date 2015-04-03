import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RiskBoard {
	private int numberOfPlayers;
	private Iterator<Player> itr;
	private ArrayList<Player> players;
	private Integer[] numPlayerArray = { 1, 2, 3, 4, 5, 6 };
	private String[] playerName = { "Player One", "Player Two", "Player Three",
			"Player Four", "Player Five", };

	public RiskBoard() {
		ArrayList<Player> players = new ArrayList<Player>();
		this.players = players;
		this.itr = players.iterator();
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
				int num = (int) cb.getSelectedItem();
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
		this.numberOfPlayers = numberOfPlayers;
		setUpPlayers();
		try {
			display();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setUpPlayers() {
		for(int i=0; i< this.numberOfPlayers; i++) {
			Player p = new Player(this.playerName[i]);
			this.players.add(p);
		}
		this.itr = this.players.iterator();
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
		NA.addActionListener(new ContinentListener("North America", this));
		NA.setText("North America");

		JButton SA = new JButton();
		SA.addActionListener(new ContinentListener("South America", this));
		SA.setText("South America");

		JButton A = new JButton();
		A.setText("Africa");
		A.addActionListener(new ContinentListener("Africa", this));

		JButton E = new JButton();
		E.setText("Europe");

		E.addActionListener(new ContinentListener("Europe", this));
		JButton D = new JButton();
		D.setText("Austrailia");

		D.addActionListener(new ContinentListener("Austraila", this));
		JButton Asia = new JButton();

		Asia.addActionListener(new ContinentListener("Asia", this));
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
}
