import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhaseChangeManager implements ActionListener {
	private RiskBoard board;
	
	public PhaseChangeManager(RiskBoard board) {
		this.board = board;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String currentPhase = this.board.getPhase();
		if(currentPhase == "Deployment Phase") {
			board.endDeployment();
		} else {
			board.endTurn();
		}
	}
	
}
