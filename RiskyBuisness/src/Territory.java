
public class Territory {

	private String name;
	private Player player;

	public Territory(String string, Player player2) {
		this.name = string;
		this.player = player2;
	}

	public String getName() {
		return this.name;
	}

	public Player getPlayer() {
		return player;
	}

}
