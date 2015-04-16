import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
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
			"Player Four", "Player Five", "Player Six"};
	private ArrayList<Territory> territories;
	private ArrayList<Territory> Asia;
	private ArrayList<Territory> Europe;
	private ArrayList<Territory> Africa;
	private ArrayList<Territory> Australia;
	private ArrayList<Territory> NA;
	private ArrayList<Territory> SA;

	public RiskBoard() {
		ArrayList<Player> players = new ArrayList<Player>();
		this.players = players;
		this.itr = players.iterator();
		territories=new ArrayList<Territory>();
		Asia=new ArrayList<Territory>();
		Europe=new ArrayList<Territory>();
		Africa=new ArrayList<Territory>();
		Australia=new ArrayList<Territory>();
		NA=new ArrayList<Territory>();
		SA=new ArrayList<Territory>();
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
		setUpTerritories();
		try {
			display();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setUpTerritories() {
		String[] asianCountries = { "Afghanistan", "China", "India",
				"Irkutsk", "Japan", "Kamchatka", "Middle East", "Mongolia", "Siam",
				"Siberia", "Ural", "Yakutsk" };
		String[] europeCountries = { "Great Britain", "Iceland", "Northen Europe", "Scandinavia", "Southern Europe", "Ukraine", "Western Europe"};
		String[] naCountries = { "Alaska", "Alberta", "Centeral America", "Eastern United States", "Greenland", "Northwest Territory", "Ontario", "Quebec", "Western United States" };
		String[] saCountries = { "Argentina", "Brazil", "Peru", "Venezula" };
		String[] africaCountries = { "Congo", "East Africa", "Egypt", "Madagascar", "North Africa", "South Africa" };
		String[] australiaCountries = { "Eastern Australia", "Indonesia", "New Guinea", "Western Australia" };
		for(String country: asianCountries) {
			Territory t = new Territory(country);
			territories.add(t);
			Asia.add(t);
			randomPlayer().addTerritory(t);
		}
		for(String country: europeCountries) {
			Territory t = new Territory(country);
			territories.add(t);
			Europe.add(t);
		}
		for(String country: naCountries) {
			Territory t = new Territory(country);
			territories.add(t);
			NA.add(t);
		}
		for(String country: africaCountries) {
			Territory t = new Territory(country);
			territories.add(t);
			Africa.add(t);
		}
		for(String country: saCountries) {
			Territory t = new Territory(country);
			territories.add(t);
			SA.add(t);
		}
		for(String country: australiaCountries) {
			Territory t = new Territory(country);
			territories.add(t);
			Australia.add(t);
		}
		for(Territory t: territories){
		randomPlayer().addTerritory(t);
		}
	}
	
	private Player randomPlayer() {
		Random playerChooser = new Random();
		int max = 0;
		for(Player p: players){
			if(p.getNumberOfTerritories()>max){
				max = p.getNumberOfTerritories();
			}
		}
		Player player;
		boolean added = false;
		while(!added){
			player = players.get(playerChooser.nextInt(players.size()));
			if(player.getNumberOfTerritories()<max){
				return player;
			}else{
				if(allHaveMax(players, max)){
					return player;
				}
			}
		}
		//Can't get here
		return null;
	}


	private boolean allHaveMax(ArrayList<Player> players, int max) {
		for(Player p: players){
			if(p.getTerritories().size()!=max){
				return false;
			}
		}
		return true;
	}

	private void setUpPlayers() {
		for(int i=0; i< this.numberOfPlayers; i++) {
			Player p = new Player(this.playerName[i]);
			this.players.add(p);
		}
		this.itr = this.players.iterator();
	}

	@SuppressWarnings("serial")
	public void display() throws IOException {
		JFrame frame = new JFrame("Risk Board");
		frame.setSize(1025, 740);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		
		JPanel panel = new JPanel() {
			private Image backgroundImage = ImageIO.read(new File("risk.png"));
			public void paint( Graphics g ) { 
				super.paint(g);
				g.drawImage(backgroundImage, 0, 30, null);
			  	}
			};
		//panel.setBackground(Color.blue);
		/*
		
		This only existed for the first week. Has no actual reason to show up.
		
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
		
		panel.add(NA);
		panel.add(SA);
		panel.add(E);
		panel.add(D);
		panel.add(A);
		panel.add(Asia);
		*/
		//panel.setSize(1000, 50);
		frame.setContentPane(panel);
		panel.setFocusable(true);
		//while(!panel.hasFocus()){
		//	panel.requestFocusInWindow();
		//}
		//System.out.println(panel.hasFocus());
		//panel.requestFocus();
		panel.revalidate();
		frame.revalidate();
		frame.repaint();
		//System.out.println(javax.swing.SwingUtilities.isEventDispatchThread());
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
}
