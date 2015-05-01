import javax.swing.JButton;

@SuppressWarnings("serial")
public class Army extends JButton {
	private Player owner;
	private Territory location;
	private int size;
	
	private int x;
	private int y;
	private int w;
	private int h;
	private RiskBoard board = null;

	public Army(Player p, Territory t) {
		this.owner = p;
		this.location = t;
		this.size = 3;
		
		this.x = t.getCoordinates().x;
		this.y = t.getCoordinates().y;
		this.w = 20;
		this.h = 40;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public Army(Player p, Territory t, int i) {
		this.owner = p;
		this.location = t;
		this.size = i;
		
		this.x = t.getCoordinates().x;
		this.y = t.getCoordinates().y;
		this.w = 20;
		this.h = 40;
	}
	
	public Army(Player p, Territory t, RiskBoard b) {
		this.owner = p;
		this.location = t;
		this.size = 3;
		this.board = b;
		
		this.x = t.getCoordinates().x;
		this.y = t.getCoordinates().y;
		this.w = 20;
		this.h = 40;
	}

	public int getArmySize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Territory getArmyLocation() {
		return location;
	}

	public void setLocation(Territory location) {
		this.location = location;
	}

	public void takeLosses(int loss) {
		if(loss >= this.size) {
			this.size = 0;
		} else {
			this.size = this.size - loss;
		}
	}

	public RiskBoard getBoard() {
		return this.board;
	}

}
