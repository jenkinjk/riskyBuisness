import javax.swing.JButton;


@SuppressWarnings("serial")
public class Card extends JButton {
	private String type;
	private Player owner;
	private int x;
	private int y;
	private int w;
	private int h;

	public Card(String type, Player p) {
		this.type = type;
		this.owner = p;
		
		this.x = 10;
		this.y = 720;
		this.w = 70;
		this.h = 90;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
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
}
