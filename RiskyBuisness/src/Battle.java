
public class Battle {

	private Army attacker;
	private Army defender;

	public Battle(Army a, Army b) throws Exception {
		if(a.getOwner().equals(b.getOwner())) throw new Exception("You cannot attack your own armies.");
		if(!(a.getLocation().getNeighbors().contains(b.getLocation()))) throw new Exception("You cannot attack non adjacent territories.");
		if(a.size()<b.size()) throw new Exception("You cannot attack a larger army.");
		this.attacker = a;
		this.defender = b;
	}

	public void execute(int attacker, int defender) {
		this.attacker.takeLosses(defender);
		this.defender.takeLosses(attacker);
		
	}

}
