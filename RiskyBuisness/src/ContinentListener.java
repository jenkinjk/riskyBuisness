import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ContinentListener implements ActionListener {

	private String label;

	public ContinentListener(String label) {
		this.label = label;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.printf(identity());

	}

	public String identity() {
		return String.format("I am %s. Fear me!\n", label);
	}

}
