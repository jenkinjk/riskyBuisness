import java.util.ArrayList;

//Just a data class for now. Won't stay that way!
public class Territory {

	private String name;
	private ArrayList<Territory> neighbors=null;

	public Territory(String string) {
		this.name = string;
	}
	
	public void setNeighbors(ArrayList<Territory> neighbors) {
		this.neighbors = neighbors;
	}

	public String getName() {
		return this.name;
	}
	public ArrayList<Territory> getNeighbors(){
		return this.neighbors;
	}
}
