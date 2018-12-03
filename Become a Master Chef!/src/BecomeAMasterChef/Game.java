package BecomeAMasterChef;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import BecomeAMasterChef.Board.*;

public class Game extends JFrame{
	private final int WIDTH = 642;
    private final int HEIGHT = 690;

    private JLabel statusbar;

    public Game() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("BecomeAMasterChef");

        statusbar = new JLabel("");
        add(statusbar, BorderLayout.SOUTH);

        add(new Board(statusbar));

        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Game();
    }

}
