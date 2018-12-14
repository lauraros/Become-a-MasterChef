package BecomeAMasterChef;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BecomeAMasterChef.Board.*;
import BecomeAMasterChef.SoundEffects.SoundEffect;

// Sets up the game GUI and passes input variables in a new game
@SuppressWarnings("serial")
public class Game extends JFrame{
	private final int WIDTH = 820;
    private final int HEIGHT = 690;

    //private JLabel statusbar;

    public Game(String playerName, String level, int cookingSkills) {
    	SoundEffect.init();
    	SoundEffect.CLICKRIGHT.play();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Become A Master Chef");
        //statusbar = new JLabel("");
        //add(statusbar, BorderLayout.SOUTH);
        //add(new Board(statusbar, playerName, level, cookingSkills));
        
		// Creating a separate Panel for skills settings for the game
		JPanel pStatus = new JPanel();
		add(pStatus, BorderLayout.EAST);
		GridLayout fl_pStatus = new GridLayout(5, 1);
		
		pStatus.setLayout(fl_pStatus);

		add(new Board(pStatus, playerName, level, cookingSkills));
		
		
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