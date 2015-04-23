
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

	public void execute(int attacker, int defender) throws Exception {
		if(attacker>this.attacker.size()||defender>this.defender.size()) throw new Exception("You cannot Roll more dice than you have armies.");
		if(defender!=1&&defender!=2) throw new Exception("The defender can only roll 1 or 2 dice.");
		if(attacker!=2&&attacker!=1&&attacker!=3) throw new Exception("The attacker can only roll 1, 2 or 3 dice.");
		this.attacker.takeLosses(defender);
		this.defender.takeLosses(attacker);
		
	}

}
