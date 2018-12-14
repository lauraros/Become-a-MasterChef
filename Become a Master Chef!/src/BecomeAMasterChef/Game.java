package BecomeAMasterChef;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import BecomeAMasterChef.Board.*;
import BecomeAMasterChef.SoundEffects.SoundEffect;

// Sets up the game GUI and passes input variables in a new game
@SuppressWarnings("serial")
public class Game extends JFrame{
	private final int WIDTH = 642;
    private final int HEIGHT = 690;

    private JLabel statusbar;

    public Game(String playerName, String level, int cookingSkills) {
    	SoundEffect.init();
    	SoundEffect.CLICKRIGHT.play();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("BecomeAMasterChef");
        statusbar = new JLabel("");
        add(statusbar, BorderLayout.SOUTH);

        add(new Board(statusbar, playerName, level, cookingSkills));

        setResizable(false);
        setVisible(true);
    }
}

/*
    public static void main(String[] args) {
        new Game();
    }

}
*/