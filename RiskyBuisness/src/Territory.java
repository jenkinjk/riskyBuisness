
public class Territory {

	private String name;

	public Territory(String string) {
		this.name = string;
	}

	public Object getName() {
		return this.name;
	}

	public Object getPlayer() {
		return new Player("Player One");
	}

}
