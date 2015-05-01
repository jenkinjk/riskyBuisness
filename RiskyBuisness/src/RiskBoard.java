import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
			"Player Four", "Player Five", "Player Six" };
	private Color[] playerColor = { Color.RED, Color.BLACK, Color.BLUE,
			Color.GREEN, Color.ORANGE, Color.DARK_GRAY };
	private ArrayList<Territory> territories;
	private ArrayList<Territory> Asia;
	private ArrayList<Territory> Europe;
	private ArrayList<Territory> Africa;
	private ArrayList<Territory> Australia;
	private ArrayList<Territory> NA;
	private ArrayList<Territory> SA;
	private ArrayList<Army> armies;
	private HashMap<String, ArrayList<Territory>> nameToNeigbhors;
	private HashMap<String, Point> nameToCoordinates;
	private Player currentPlayer;
	private JLabel statusLabel;
	private JFrame frame;
	private JPanel panel;

	public RiskBoard() {
		ArrayList<Player> players = new ArrayList<Player>();
		this.players = players;
		this.itr = players.iterator();
		territories = new ArrayList<Territory>();
		Asia = new ArrayList<Territory>();
		Europe = new ArrayList<Territory>();
		Africa = new ArrayList<Territory>();
		Australia = new ArrayList<Territory>();
		NA = new ArrayList<Territory>();
		SA = new ArrayList<Territory>();
		armies = new ArrayList<Army>();
		nameToNeigbhors = new HashMap<String, ArrayList<Territory>>();
		nameToCoordinates = new HashMap<String, Point>();
		this.currentPlayer = null;
		this.statusLabel = new JLabel("Player One's Turn");
	}

	public void selectNumberOfPlayers() {
		final JFrame setUpFrame = new JFrame("Risk");
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
				try {
					display();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				Battle b = new Battle();
//				try {
//					b.display();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				setUpFrame.dispose();
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
		setUpTerritories();
		setUpArmy();
	}

	private void setUpPlayers() {
		for (int i = 0; i < this.numberOfPlayers; i++) {
			Player p = new Player(this.playerName[i], this.playerColor[i]);
			this.players.add(p);
		}
		this.itr = this.players.iterator();
	}

	private void setUpTerritories() {
		String[] asianCountries = { "Afghanistan", "China", "India", "Irkutsk",
				"Japan", "Kamchatka", "Middle East", "Mongolia", "Siam",
				"Siberia", "Ural", "Yakutsk" };
		String[] europeCountries = { "Great Britain", "Iceland",
				"Northen Europe", "Scandinavia", "Southern Europe", "Ukraine",
				"Western Europe" };
		String[] naCountries = { "Alaska", "Alberta", "Centeral America",
				"Eastern United States", "Greenland", "Northwest Territory",
				"Ontario", "Quebec", "Western United States" };
		String[] saCountries = { "Argentina", "Brazil", "Peru", "Venezula" };
		String[] africaCountries = { "Congo", "East Africa", "Egypt",
				"Madagascar", "North Africa", "South Africa" };
		String[] australiaCountries = { "Eastern Australia", "Indonesia",
				"New Guinea", "Western Australia" };
		for (String country : asianCountries) {
			Territory t = new Territory(country);
			territories.add(t);
			Asia.add(t);
		}
		for (String country : europeCountries) {
			Territory t = new Territory(country);
			territories.add(t);
			Europe.add(t);
		}
		for (String country : naCountries) {
			Territory t = new Territory(country);
			territories.add(t);
			NA.add(t);
		}
		for (String country : africaCountries) {
			Territory t = new Territory(country);
			territories.add(t);
			Africa.add(t);
		}
		for (String country : saCountries) {
			Territory t = new Territory(country);
			territories.add(t);
			SA.add(t);
		}
		for (String country : australiaCountries) {
			Territory t = new Territory(country);
			territories.add(t);
			Australia.add(t);
		}
		
		setUpNeighborsMap();
		setUpCoordinatesMap();
		for (Territory t : territories) {
			randomPlayer().addTerritory(t);
			assignNeighbors(t);
			assignCoordinates(t);
		}
	}

	private void setUpArmy() {
		for (Player p : players) {
			for (Territory t : p.getTerritories()) {
					Army a = new Army(p, t); //Note this constructor defaults to a size of three.
					p.addArmy(a);
					this.armies.add(a);
			}
		}
	}

	private void setUpNeighborsMap() {
		ArrayList<Territory> neighbors = new ArrayList<Territory>();
		try {
			neighbors.add(getTerritoryNamed("Ukraine"));
			neighbors.add(getTerritoryNamed("Middle East"));
			neighbors.add(getTerritoryNamed("India"));
			neighbors.add(getTerritoryNamed("China"));
			neighbors.add(getTerritoryNamed("Ural"));
			nameToNeigbhors.put("Afghanistan", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Ukraine"));
			neighbors.add(getTerritoryNamed("Afghanistan"));
			neighbors.add(getTerritoryNamed("India"));
			neighbors.add(getTerritoryNamed("Egypt"));
			neighbors.add(getTerritoryNamed("Southern Europe"));
			nameToNeigbhors.put("Middle East", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Afghanistan"));
			neighbors.add(getTerritoryNamed("Middle East"));
			neighbors.add(getTerritoryNamed("China"));
			neighbors.add(getTerritoryNamed("Siam"));
			nameToNeigbhors.put("India", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Indonesia"));
			neighbors.add(getTerritoryNamed("China"));
			neighbors.add(getTerritoryNamed("India"));
			nameToNeigbhors.put("Siam", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Siam"));
			neighbors.add(getTerritoryNamed("Afghanistan"));
			neighbors.add(getTerritoryNamed("India"));
			neighbors.add(getTerritoryNamed("Ural"));
			neighbors.add(getTerritoryNamed("Siberia"));
			neighbors.add(getTerritoryNamed("Mongolia"));
			nameToNeigbhors.put("China", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Ukraine"));
			neighbors.add(getTerritoryNamed("Afghanistan"));
			neighbors.add(getTerritoryNamed("China"));
			neighbors.add(getTerritoryNamed("Siberia"));
			nameToNeigbhors.put("Ural", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Ural"));
			neighbors.add(getTerritoryNamed("Irkutsk"));
			neighbors.add(getTerritoryNamed("Mongolia"));
			neighbors.add(getTerritoryNamed("Yakutsk"));
			nameToNeigbhors.put("Siberia", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Siberia"));
			neighbors.add(getTerritoryNamed("Irkutsk"));
			neighbors.add(getTerritoryNamed("China"));
			neighbors.add(getTerritoryNamed("Japan"));
			neighbors.add(getTerritoryNamed("Kamchatka"));
			nameToNeigbhors.put("Mongolia", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Mongolia"));
			neighbors.add(getTerritoryNamed("Kamchatka"));
			nameToNeigbhors.put("Japan", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Siberia"));
			neighbors.add(getTerritoryNamed("Mongolia"));
			neighbors.add(getTerritoryNamed("Yakutsk"));
			neighbors.add(getTerritoryNamed("Kamchatka"));
			nameToNeigbhors.put("Irkutsk", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Siberia"));
			neighbors.add(getTerritoryNamed("Irkutsk"));
			neighbors.add(getTerritoryNamed("Kamchatka"));
			nameToNeigbhors.put("Yakutsk", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Japan"));
			neighbors.add(getTerritoryNamed("Mongolia"));
			neighbors.add(getTerritoryNamed("Yakutsk"));
			neighbors.add(getTerritoryNamed("Irkutsk"));
			neighbors.add(getTerritoryNamed("Alaska"));
			nameToNeigbhors.put("Kamchatka", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Kamchatka"));
			neighbors.add(getTerritoryNamed("Northwest Territory"));
			neighbors.add(getTerritoryNamed("Alberta"));
			nameToNeigbhors.put("Alaska", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Greenland"));
			neighbors.add(getTerritoryNamed("Ontario"));
			neighbors.add(getTerritoryNamed("Alberta"));
			neighbors.add(getTerritoryNamed("Alaska"));
			nameToNeigbhors.put("Northwest Territory", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Northwest Territory"));
			neighbors.add(getTerritoryNamed("Western United States"));
			neighbors.add(getTerritoryNamed("Ontario"));
			neighbors.add(getTerritoryNamed("Alaska"));
			nameToNeigbhors.put("Alberta", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Quebec"));
			neighbors.add(getTerritoryNamed("Ontario"));
			neighbors.add(getTerritoryNamed("Northwest Territory"));
			neighbors.add(getTerritoryNamed("Iceland"));
			nameToNeigbhors.put("Greenland", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Greenland"));
			neighbors.add(getTerritoryNamed("Ontario"));
			neighbors.add(getTerritoryNamed("Eastern United States"));
			nameToNeigbhors.put("Quebec", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Quebec"));
			neighbors.add(getTerritoryNamed("Greenland"));
			neighbors.add(getTerritoryNamed("Northwest Territory"));
			neighbors.add(getTerritoryNamed("Alberta"));
			neighbors.add(getTerritoryNamed("Western United States"));
			neighbors.add(getTerritoryNamed("Eastern United States"));
			nameToNeigbhors.put("Ontario", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Alberta"));
			neighbors.add(getTerritoryNamed("Ontario"));
			neighbors.add(getTerritoryNamed("Eastern United States"));
			neighbors.add(getTerritoryNamed("Centeral America"));
			nameToNeigbhors.put("Western United States", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Quebec"));
			neighbors.add(getTerritoryNamed("Ontario"));
			neighbors.add(getTerritoryNamed("Western United States"));
			neighbors.add(getTerritoryNamed("Centeral America"));
			nameToNeigbhors.put("Eastern United States", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Venezula"));
			neighbors.add(getTerritoryNamed("Eastern United States"));
			neighbors.add(getTerritoryNamed("Western United States"));
			nameToNeigbhors.put("Centeral America", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Northen Europe"));
			neighbors.add(getTerritoryNamed("Southern Europe"));
			neighbors.add(getTerritoryNamed("Scandinavia"));
			neighbors.add(getTerritoryNamed("Middle East"));
			neighbors.add(getTerritoryNamed("Ural"));
			neighbors.add(getTerritoryNamed("Afghanistan"));
			nameToNeigbhors.put("Ukraine", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Northen Europe"));
			neighbors.add(getTerritoryNamed("Iceland"));
			neighbors.add(getTerritoryNamed("Ukraine"));
			nameToNeigbhors.put("Scandinavia", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Southern Europe"));
			neighbors.add(getTerritoryNamed("Great Britain"));
			neighbors.add(getTerritoryNamed("Scandinavia"));
			neighbors.add(getTerritoryNamed("Western Europe"));
			neighbors.add(getTerritoryNamed("Ukraine"));
			nameToNeigbhors.put("Northen Europe", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Western Europe"));
			neighbors.add(getTerritoryNamed("Northen Europe"));
			neighbors.add(getTerritoryNamed("Ukraine"));
			neighbors.add(getTerritoryNamed("Middle East"));
			nameToNeigbhors.put("Southern Europe", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Southern Europe"));
			neighbors.add(getTerritoryNamed("Northen Europe"));
			neighbors.add(getTerritoryNamed("Great Britain"));
			neighbors.add(getTerritoryNamed("North Africa"));
			nameToNeigbhors.put("Western Europe", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Southern Europe"));
			neighbors.add(getTerritoryNamed("Northen Europe"));
			neighbors.add(getTerritoryNamed("Scandinavia"));
			neighbors.add(getTerritoryNamed("Iceland"));
			nameToNeigbhors.put("Great Britain", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Scandinavia"));
			neighbors.add(getTerritoryNamed("Great Britain"));
			nameToNeigbhors.put("Iceland", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Venezula"));
			neighbors.add(getTerritoryNamed("Peru"));
			neighbors.add(getTerritoryNamed("Argentina"));
			neighbors.add(getTerritoryNamed("North Africa"));
			nameToNeigbhors.put("Brazil", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Venezula"));
			neighbors.add(getTerritoryNamed("Brazil"));
			neighbors.add(getTerritoryNamed("Argentina"));
			nameToNeigbhors.put("Peru", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Brazil"));
			neighbors.add(getTerritoryNamed("Peru"));
			nameToNeigbhors.put("Argentina", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Brazil"));
			neighbors.add(getTerritoryNamed("Peru"));
			neighbors.add(getTerritoryNamed("Centeral America"));
			nameToNeigbhors.put("Venezula", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("North Africa"));
			neighbors.add(getTerritoryNamed("East Africa"));
			neighbors.add(getTerritoryNamed("South Africa"));
			nameToNeigbhors.put("Congo", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Congo"));
			neighbors.add(getTerritoryNamed("Egypt"));
			neighbors.add(getTerritoryNamed("Madagascar"));
			neighbors.add(getTerritoryNamed("North Africa"));
			neighbors.add(getTerritoryNamed("South Africa"));
			nameToNeigbhors.put("East Africa", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Middle East"));
			neighbors.add(getTerritoryNamed("North Africa"));
			neighbors.add(getTerritoryNamed("East Africa"));
			nameToNeigbhors.put("Egypt", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("South Africa"));
			neighbors.add(getTerritoryNamed("East Africa"));
			nameToNeigbhors.put("Madagascar", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Congo"));
			neighbors.add(getTerritoryNamed("Egypt"));
			neighbors.add(getTerritoryNamed("Western Europe"));
			neighbors.add(getTerritoryNamed("East Africa"));
			neighbors.add(getTerritoryNamed("Brazil"));
			nameToNeigbhors.put("North Africa", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Congo"));
			neighbors.add(getTerritoryNamed("East Africa"));
			neighbors.add(getTerritoryNamed("Madagascar"));
			nameToNeigbhors.put("South Africa", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Western Australia"));
			neighbors.add(getTerritoryNamed("New Guinea"));
			neighbors.add(getTerritoryNamed("Indonesia"));
			nameToNeigbhors.put("Eastern Australia", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Siam"));
			neighbors.add(getTerritoryNamed("Western Australia"));
			neighbors.add(getTerritoryNamed("New Guinea"));
			nameToNeigbhors.put("Indonesia", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Indonesia"));
			neighbors.add(getTerritoryNamed("Eastern Australia"));
			nameToNeigbhors.put("New Guinea", neighbors);
			neighbors = new ArrayList<Territory>();
			neighbors.add(getTerritoryNamed("Eastern Australia"));
			neighbors.add(getTerritoryNamed("Indonesia"));
			nameToNeigbhors.put("Western Australia", neighbors);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void assignNeighbors(Territory t) {
		t.setNeighbors(nameToNeigbhors.get(t.getName()));
	}
	
	private void setUpCoordinatesMap() {
		nameToCoordinates.put("Afghanistan", new Point(670, 280));
		nameToCoordinates.put("China", new Point(800, 335));
		nameToCoordinates.put("India", new Point(730, 390));
		nameToCoordinates.put("Irkutsk", new Point(805, 200));
		nameToCoordinates.put("Japan", new Point(930, 265));
		nameToCoordinates.put("Kamchatka", new Point(905, 115));
		nameToCoordinates.put("Middle East", new Point(615, 395));
		nameToCoordinates.put("Mongolia", new Point(820, 270));
		nameToCoordinates.put("Siam", new Point(820, 420));
		nameToCoordinates.put("Siberia", new Point(750, 140));
		nameToCoordinates.put("Ural", new Point(695, 200));
		nameToCoordinates.put("Yakutsk", new Point(820, 115));
		nameToCoordinates.put("Great Britain", new Point(390, 250));
		nameToCoordinates.put("Iceland", new Point(415, 155));
		nameToCoordinates.put("Northen Europe", new Point(485, 245));
		nameToCoordinates.put("Scandinavia", new Point(485, 155));
		nameToCoordinates.put("Southern Europe", new Point(485, 315));
		nameToCoordinates.put("Ukraine", new Point(560, 210));
		nameToCoordinates.put("Western Europe", new Point(400, 340));
		nameToCoordinates.put("Alaska", new Point(30, 140));
		nameToCoordinates.put("Alberta", new Point(115, 190));
		nameToCoordinates.put("Centeral America", new Point(155, 360));
		nameToCoordinates.put("Eastern United States", new Point(205, 260));
		nameToCoordinates.put("Greenland", new Point(320,100));
		nameToCoordinates.put("Northwest Territory", new Point(130, 125));
		nameToCoordinates.put("Ontario", new Point(190, 185));
		nameToCoordinates.put("Quebec", new Point(255, 205));
		nameToCoordinates.put("Western United States", new Point(125, 270));
		nameToCoordinates.put("Argentina", new Point(235, 590));
		nameToCoordinates.put("Brazil", new Point(280, 485));
		nameToCoordinates.put("Peru", new Point(215, 495));
		nameToCoordinates.put("Venezula", new Point(200, 415));
		nameToCoordinates.put("Congo", new Point(520, 540));
		nameToCoordinates.put("East Africa", new Point(555, 465));
		nameToCoordinates.put("Egypt", new Point(520, 420));
		nameToCoordinates.put("Madagascar", new Point(630, 630));
		nameToCoordinates.put("North Africa", new Point(430, 430));
		nameToCoordinates.put("South Africa", new Point(520, 610));
		nameToCoordinates.put("Eastern Australia", new Point(950, 620));
		nameToCoordinates.put("Indonesia", new Point(805, 515));
		nameToCoordinates.put("New Guinea", new Point(915, 495));
		nameToCoordinates.put("Western Australia", new Point(855, 620));		
	}

	private void assignCoordinates(Territory t) {
		t.setCoordinates(nameToCoordinates.get(t.getName()));
	}

	private Player randomPlayer() {
		Random playerChooser = new Random();
		int max = 0;
		for (Player p : players) {
			if (p.getNumberOfTerritories() > max) {
				max = p.getNumberOfTerritories();
			}
		}
		Player player;
		while (true) { // There is no way to know how many times this must
						// run.
			player = players.get(playerChooser.nextInt(players.size()));
			if (player.getNumberOfTerritories() < max) {
				return player;
			} else {
				if (allHaveMax(players, max)) {
					return player;
				}
			}
		}
	}

	private boolean allHaveMax(ArrayList<Player> players, int max) {
		for (Player p : players) {
			if (p.getTerritories().size() != max) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("serial")
	public void display() throws IOException {
		frame = new JFrame("Risk Board");
		frame.setSize(1025, 740);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

		panel = new JPanel() {
			private Image backgroundImage = ImageIO.read(new File("risk.png"));
			public void paint(Graphics g) {
				super.paint(g);
				g.drawImage(backgroundImage, 0, 30, null);
			}
		};
		
		panel.setLayout(null);
		this.statusLabel.setBounds(15, 0, 500, 30);
		panel.add(this.statusLabel);
		Image soldierImage = ImageIO.read(new File("soldier.png"));
		Icon soldierIcon = new ImageIcon(soldierImage);
		for(Army a : this.armies) {
			a.setBounds(a.getX(), a.getY(), a.getW(), a.getH());
			a.setIcon(soldierIcon);
			ArmyListener al = new ArmyListener(a);
			a.addMouseListener(al);
			panel.add(a);
		}
		
		frame.setContentPane(panel);
		panel.setFocusable(true);
		panel.revalidate();
		frame.revalidate();
		frame.repaint();
	}

	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	public Player getNextPlayer() {
		if (!itr.hasNext())
			this.itr = this.players.iterator();
		this.currentPlayer = this.itr.next();
		this.statusLabel.setText(this.currentPlayer.getName() + "'s Turn");
		return this.currentPlayer;
	}
	
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public String getLabelText() {
		return this.statusLabel.getText();
	}

	public ArrayList<Territory> getTerritories() {
		return territories;
	}

	public ArrayList<Territory> getAsiaTerritories() {
		return Asia;
	}

	public ArrayList<Territory> getEuropeTerritories() {
		return Europe;
	}

	public ArrayList<Territory> getAfricaTerritories() {
		return Africa;
	}

	public ArrayList<Territory> getAustralaTerritories() {
		return Australia;
	}

	public ArrayList<Territory> getNATerritories() {
		return NA;
	}

	public ArrayList<Territory> getSATerritories() {
		return SA;
	}

	public Territory getTerritoryNamed(String string) throws Exception {
		for (Territory t : territories) {
			if (t.getName().equals(string))
				return t;
		}
		throw new Exception("This territory does not exist. " + string);
	}
}
