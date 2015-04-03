//Just a data class for now. Won't stay that way!
public class Territory {

	private String name;
	private Player player;

	public Territory(String string, Player player) {
		this.name = string;
		this.player = player;
	}

	public String getName() {
		return this.name;
	}

	public Player getPlayer() {
		return player;
	}

}
