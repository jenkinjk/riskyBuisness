import java.awt.Point;
import java.util.ArrayList;

//Just a data class for now. Won't stay that way!
public class Territory {

	private String name;
	private ArrayList<Territory> neighbors=null;
	private Point coordinates;
	private Army army;

	public Territory(String string) {
		this.name = string;
	}
	
	public Point getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Point coordinates) {
		this.coordinates = coordinates;
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

	public Army getArmy() {
		return this.army;
	}

	public void setArmy(Army a) {
		this.army = a;
	}
}
