package become_a_masterchef.game;

// Import GUI related libraries 
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/* Sets up the game GUI and instantiate a new board with input setting variables. */
@SuppressWarnings("serial")
public class Game extends JFrame{
	private final int WIDTH = 648;
	private final int HEIGHT = 750;

	// Constructor 
	public Game(String playerName, String level, int cookingSkills) {

		SoundEffect.init(); // Loads all necessary sounds
		SoundEffect.CLICKRIGHT.play(); // plays start game sound
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setTitle("Become A MasterChef");

		// Creating a separate Panel for the current status information
		JPanel pStatus = new JPanel();
		add(pStatus, BorderLayout.SOUTH);
		FlowLayout pStatus_layout = new FlowLayout(FlowLayout.CENTER, 30, 30);
		pStatus.setLayout(pStatus_layout);
		add(new Board(pStatus, playerName, level, cookingSkills));

		setResizable(false);
		setVisible(true);
	}
}

// Optional main method to check game flow
/*
    public static void main(String[] args) {
        new Game();
    }

}
 */