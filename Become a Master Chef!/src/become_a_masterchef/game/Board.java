package become_a_masterchef.game;

// Import GUI related libraries 
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Import Random class 
import java.util.Random;

// Import Game related sources
import become_a_masterchef.*;
import become_a_masterchef.sound_effects.SoundEffect;

import java.io.*;

/* 
 * A board object contains the board for a new game and a JPanel status bar:
 * 1. Game board composed of cells in a matrix where the game is played
 * 2. Status bar shows current status information for the player 
 */
@SuppressWarnings("serial")
public class Board extends JPanel {

	// Constants for the Game Board
	private final int CELL_SIZE  = 40;
	private final int NUM_IMAGES = 8;

	// Assigning index to selected images to facilitate the call 
	private final int IMAGE_COVER       	= 0;
	private final int IMAGE_BONUS 			= 1;
	private final int IMAGE_MINUS       	= 2;
	private final int IMAGE_ITALIAN_CHEF    = 3;
	private final int IMAGE_CHINESE_CHEF    = 4;
	private final int IMAGE_PLAYER     		= 5;
	private final int IMAGE_GOAL		    = 6;
	private final int IMAGE_VISIBLE			= 7;

	private JPanel statusBar;
	private JLabel playerNameStatus;
	private JLabel energyStatus;
	private JLabel skillStatus1;
	private JLabel skillStatus2;
	private JLabel bestScore;

	private Player player;
	private Level level;
	private Cell[][] cells;
	private int[] playerPos = new int[2];
	private Image[] img;
	private int score;
	private int highscore;

	// Number of all objects on the board
	private int playerNum = 1;
	private int goalNum = 1;
	private int bonusNum = 5;
	private int minusNum = 5;
	private int italianChefNum = 3;
	private int chineseChefNum = 3;

	private int rows = 16, columns = 16;

	private boolean inGame;

	// Constructor 
	public Board(JPanel pStatus, String playerName, String level, int cookingSkills) {

		// Game setup from user input
		this.statusBar = pStatus;
		this.playerNameStatus = new JLabel("");
		statusBar.add(this.playerNameStatus);
		this.energyStatus = new JLabel("");
		statusBar.add(this.energyStatus);
		this.skillStatus1 = new JLabel("");
		statusBar.add(this.skillStatus1);
		this.skillStatus2 = new JLabel("");
		statusBar.add(this.skillStatus2);
		this.bestScore = new JLabel("");
		statusBar.add(this.bestScore);

		this.level = new Level(level);
		this.player = new Player(playerName, cookingSkills);
		this.player.setEnergy(this.level.getInitEnergy());
		updateHighScore();

		// Input images for cell in an array
		this.img = new Image[NUM_IMAGES];
		for (int i = 0; i < NUM_IMAGES; i++) {
			String path = "src/img/j" + i + ".jpg";
			img[i] = new ImageIcon(path).getImage();
		}

		this.setDoubleBuffered(true);
		this.addMouseListener(new PathAdapter());
		this.newGame();
	}


	// Create a matrix for the board layout
	private void initCells () {
		this.cells = new Cell[rows][columns];

		for (int i = 0; i < this.rows; ++i) {
			for (int j = 0; j < this.columns; ++j) {
				this.cells[i][j] = new Cell();
			}
		}
	}


	// Start a new game
	public void newGame () {
		Random random = new Random();
		score = 0;

		this.inGame = true;
		this.initCells();

		// Information of settings are shown in the statusBar below the board
		this.playerNameStatus.setText("Player:" + this.player.getPlayerName());
		this.skillStatus1.setText("Cooking: " + Integer.toString(this.player.getCookingSkills()));
		this.skillStatus2.setText("Chopping: " + Integer.toString(this.player.getChopping()));
		this.energyStatus.setText("Energy: " + Integer.toString(this.player.getEnergy()));
		this.bestScore.setText("Best Score: " + Integer.toString(highscore));

		// Assign player to a random place on board and remember player's position
		while (playerNum > 0) {
			int randX = random.nextInt(this.rows);
			int randY = random.nextInt(this.columns);

			Cell cell = this.cells[randX][randY];
			if (cell.isEmpty()) {
				cell.setPlayer(true);
				playerPos[0] = randX;
				playerPos[1] = randY;
				//System.out.println("init player: " + playerPos[0] + ", " + playerPos[1]);
				playerNum--;
			}
		}

		// Assign goal to a random place on board
		while (goalNum > 0) {
			int randX = random.nextInt(this.rows);
			int randY = random.nextInt(this.columns);

			Cell cell = this.cells[randX][randY];
			if (cell.isEmpty()) {
				cell.setGoal(true);
				//System.out.println("init goal: " + randX + ", " + randY);
				goalNum--;
			}
		}

		// Assign bonus to random places on board
		while (bonusNum > 0) {
			int randX = random.nextInt(this.rows);
			int randY = random.nextInt(this.columns);

			Cell cell = this.cells[randX][randY];
			if (cell.isEmpty()) {
				cell.setBonus(true);
				bonusNum--;
			}
		}

		// Assign minus to random places on board
		while (minusNum > 0) {
			int randX = random.nextInt(this.rows);
			int randY = random.nextInt(this.columns);

			Cell cell = this.cells[randX][randY];
			if (cell.isEmpty()) {
				cell.setMinus(true);
				minusNum--;
			}
		}

		// Assign competitor_1 (Italian chef: corresponds to cooking skills) to random places on board
		while (italianChefNum > 0) {
			int randX = random.nextInt(this.rows);
			int randY = random.nextInt(this.columns);

			Cell cell = this.cells[randX][randY];
			if (cell.isEmpty()) {
				cell.setItalianChef(true);
				italianChefNum--;
			}
		}

		// Assign competitor_2 (Chinese chef: corresponds to chopping) to random places on board
		while (chineseChefNum > 0) {
			int randX = random.nextInt(this.rows);
			int randY = random.nextInt(this.columns);

			Cell cell = this.cells[randX][randY];
			if (cell.isEmpty()) {
				cell.setChineseChef(true);
				chineseChefNum--;
			}
		}   
	}

	// Print images in each cell on the board
	public void paint(Graphics g) {
		boolean gamewin = false;
		int input;
		ImageIcon winIcon = new ImageIcon("src/img/j6.jpg");
		ImageIcon lostIcon = new ImageIcon("src/img/j2.jpg");
		// Update status bar information
		this.playerNameStatus.setText("Player: " + this.player.getPlayerName());
		this.skillStatus1.setText("Cooking: " + Integer.toString(this.player.getCookingSkills()));
		this.skillStatus2.setText("Chopping: " + Integer.toString(this.player.getChopping()));
		this.energyStatus.setText("Energy: " + Integer.toString(this.player.getEnergy()));
		this.bestScore.setText("Best Score: " + Integer.toString(highscore));


		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				Cell cell = this.cells[i][j];
				int imageType;
				int xPosition, yPosition;
				xPosition = (j * CELL_SIZE);
				yPosition = (i * CELL_SIZE);

				// Game is over whenever player is out of energy
				if (inGame) {	
					if (player.getEnergy() <= 0) {
						inGame = false;
					}
				}

				// Game is won and over if player reaches the goal before consuming up energy
				if (cell.isGoal() && cell.isPlayer() && player.getEnergy() >= 0) {
					inGame = false;
					gamewin = true;

					SoundEffect.WIN.play();
					score = player.getEnergy() + player.getChopping() + player.getCookingSkills();
					updateHighScore();

					if (score > highscore) {
						Game.getFrames()[Game.getFrames().length-1].dispose();
						input= JOptionPane.showOptionDialog(null,
								"Your score of " + score + 
								" is the best score ever! \nThe previous best score was " + 
								highscore + ".\n\nDo you want to play again?",
								"You WON!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, winIcon, null, null);
						if(input == JOptionPane.YES_OPTION){
							BecomeAMasterChef.initGUI();
							return;
						} else {
							System.exit(0);
						}

					} else if (score == highscore) {
						Game.getFrames()[Game.getFrames().length-1].dispose();
						System.out.println(Game.getFrames());
						input= JOptionPane.showOptionDialog(null,
								"You tied with the best score of " + score + 
								"!\n\nDo you want to play again?",
								"You WON!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, winIcon, null, null);
						if(input == JOptionPane.YES_OPTION){
							BecomeAMasterChef.initGUI();
							return;
						} else {
							System.exit(0);
						}

					} else {
						Game.getFrames()[Game.getFrames().length-1].dispose();
						input = JOptionPane.showOptionDialog(null,
								"Your score is " + score
								+ ". The best score ever is " + highscore + ".\n\nDo you want to play again?",
								"You WON!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, winIcon, null, null);
						if(input == JOptionPane.YES_OPTION){
							BecomeAMasterChef.initGUI();
							return;
						} else {
							System.exit(0);
						}
					}

					// Game is lost and over if player is out of energy and not winning the game
				} else if (!inGame && !gamewin) {
					SoundEffect.LOSE.play();
					Game.getFrames()[Game.getFrames().length-1].dispose();
					input= JOptionPane.showOptionDialog(null, "You're out of energy. Game Over!\nDo you want to play again?",
							"Game Over!", JOptionPane.YES_NO_OPTION,  JOptionPane.INFORMATION_MESSAGE, lostIcon, null, null);
					if(input == JOptionPane.YES_OPTION){
						BecomeAMasterChef.initGUI();
						return;
					} else {
						System.exit(0);
					}

				} else {
					;
				}

				// Set cells visible around the player
				if (isAroundPlayer(i,j)) {
					cell.setVisible(true);
				}

				// Set the cell visible where the player is at
				else if (i == playerPos[0] && j == playerPos[1]) {
					cell.setVisible(true);
				}

				// All the other cells set to be invisible
				else {
					cell.setVisible(false);
				}

				// Decide which image to print for each cell
				imageType = this.decideImageType(cell);

				// Draw images
				g.drawImage(img[imageType], xPosition, yPosition, this);
			}
		}
	}

	// Decide which image to print for each cell
	private int decideImageType(Cell cell) {
		int imageType = 0;

		if (cell.isVisible()) {
			if (cell.isEmpty()) {
				imageType = IMAGE_VISIBLE;
			}
			else if (cell.isPlayer()) {
				imageType = IMAGE_PLAYER;
			}
			else if (cell.isGoal()) {
				imageType = IMAGE_GOAL;
			}
			else if (cell.isBonus()) {
				imageType = IMAGE_BONUS;
			}
			else if (cell.isMinus()) {
				imageType = IMAGE_MINUS;
			}
			else if (cell.isChineseChef()) {
				imageType = IMAGE_CHINESE_CHEF;
			}
			else if (cell.isItalianChef()) {
				imageType = IMAGE_ITALIAN_CHEF;
			}
		}

		else if (!cell.isVisible()){
			imageType = IMAGE_COVER;
		}

		return imageType;
	}

	// Return if a cell for a certain position is around the player
	private boolean isAroundPlayer(int x, int y) {
		boolean around_player = false;

		// Exclude out-of-bound cells
		for (int i = -1; i <= 1; ++i) {
			int xIndex = x + i;
			if (xIndex < 0 || xIndex >= this.rows) {
				continue;
			}

			for (int j = -1; j <= 1; ++j) {
				int yIndex = y + j;
				if (yIndex < 0 || yIndex >= this.columns) {
					continue;
				}

				// Exclude the player's position
				if (i == 0 && j == 0) {
					continue;
				}

				if (this.cells[xIndex][yIndex].isPlayer()) {
					around_player = true;
				}
			}
		}

		return around_player;
	}

	// Process the button press
	class PathAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			int pressedCol = e.getX() / CELL_SIZE;
			int pressedRow = e.getY() / CELL_SIZE;

			boolean doRepaint = false;
			Cell pressedCell;

			// Out of bounds
			if ((pressedCol < 0 || pressedCol >= columns)
					|| (pressedRow < 0 || pressedRow >= rows)) {
				SoundEffect.WRONG.play();
				return;
			}

			pressedCell = cells[pressedRow][pressedCol];

			// Reaction to only left click on visible cells
			if (e.getButton() == MouseEvent.BUTTON3) {
				SoundEffect.WRONG.play();
				return;
			} else if (!pressedCell.isVisible()) {
				SoundEffect.WRONG.play();
				return;
			} else if (pressedCell.isPlayer()) {
				SoundEffect.WRONG.play();
				return;
			} else if (inGame && pressedCell.isVisible()) {
				// Move player position to where clicked
				cells[playerPos[0]][playerPos[1]].setPlayer(false);
				playerPos[0] = pressedRow;
				playerPos[1] = pressedCol;
				cells[playerPos[0]][playerPos[1]].setPlayer(true);

				// Consume energy by 1 for each move
				player.setEnergy(player.getEnergy() - 1);

				// Interaction with events on the board accordingly
				// Events should vanish afterwards
				if (pressedCell.isBonus()) {
					SoundEffect.BONUS.play();
					player.setEnergy(player.getEnergy() + 3 + 1);
					cells[pressedRow][pressedCol].setBonus(false);
				}

				else if (pressedCell.isMinus()) {
					SoundEffect.MINUS.play();
					player.setEnergy(player.getEnergy() - 3 + 1);
					cells[pressedRow][pressedCol].setMinus(false);
				}

				else if (pressedCell.isItalianChef()) {
					SoundEffect.ITALIAN.play();
					if (player.getCookingSkills() >= level.getItalianChefSkill()) {
						player.setCookingSkills(player.getCookingSkills() + 2);
					}
					else {
						player.setCookingSkills(player.getCookingSkills() - 2);
					}
					cells[pressedRow][pressedCol].setItalianChef(false);
				}

				else if (pressedCell.isChineseChef()) {
					SoundEffect.CHINESE.play();
					if (player.getChopping() >= level.getChineseChefSkill()) {
						player.setChopping(player.getChopping() + 2);
					}
					else {
						player.setChopping(player.getChopping() - 2);
					}
					cells[pressedRow][pressedCol].setChineseChef(false);
				} else {
					SoundEffect.MOVE.play();
				}

				// Repaint the board
				doRepaint = true;
			}

			if (doRepaint) {
				repaint(); 
			}
		}
	}

	// Record and update scores in a text file
	private void updateHighScore() {
		String scoreFilePath = 	level.getScoreFilePath();
		File f = new File(scoreFilePath);
		// Read the current high score
		try {
			if(!f.exists()){
				f.createNewFile();
				highscore = 0;
			} else {

				BufferedReader reader = new BufferedReader(new FileReader(scoreFilePath));
				String line = reader.readLine();
				while (line != null)		// read the score file line by line
				{
					try {
						int score = Integer.parseInt(line.trim());   // parse each line as an int
						if (score > highscore)                       // and keep track of the largest
						{ 
							highscore = score; 
						}
					} catch (NumberFormatException e1) {
						// ignore invalid scores
						// System.err.println("ignoring invalid score: " + line);
					}
					line = reader.readLine();
				}
				reader.close();}

		} catch (IOException ex) {
			System.err.println("ERROR missing score file");
			System.exit(1);
		}

		// Append the last score to the end of the file
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(scoreFilePath, true));
			output.newLine();
			output.append("" + score);
			output.close();
		} catch (IOException ex1) {
			System.out.printf("ERROR writing score to file: %s\n", ex1);
		}
	}
}