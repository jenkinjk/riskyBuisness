
public class Army {
	private Player owner;
	private Territory location;
	
	public Army(Player p, Territory t) {
		this.owner = p;
		this.location = t;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Territory getLocation() {
		return location;
	}

	public void setLocation(Territory location) {
		this.location = location;
	}
	
	
}
