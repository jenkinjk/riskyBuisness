
public class Battle {

	public Battle(Army a, Army b) throws Exception {
		if(a.getOwner().equals(b.getOwner())) throw new Exception("You cannot attack your own armies.");
		if(!(a.getLocation().getNeighbors().contains(b.getLocation()))) throw new Exception("You cannot attack non adjacent territories.");
		if(a.size()<b.size()) throw new Exception("You cannot attack a larger army.");
	}

}
