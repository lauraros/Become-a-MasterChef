package BecomeAMasterChef.Board;

import BecomeAMasterChef.*;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
import java.io.*;

public class Board extends JPanel {
    // Constants
    private final int CELL_SIZE  = 40;
    private final int NUM_IMAGES = 8;

    private final int IMAGE_COVER       	= 0;
    private final int IMAGE_BONUS 			= 1;
    private final int IMAGE_MINUS       	= 2;
    private final int IMAGE_ITALIAN_CHEF    = 3;
    private final int IMAGE_CHINESE_CHEF    = 4;
    private final int IMAGE_PLAYER     		= 5;
    private final int IMAGE_GOAL		    = 6;
    private final int IMAGE_VISIBLE			= 7;

    private JLabel statusBar;
    private Player player;
    private Level level;
    private Cell[][] cells;
    private int[] playerPos = new int[2];
    private Image[] img;
    private int score;
    private int highscore;

    // the number of all objects on the board
    private int num_player = 1;
    private int num_goal = 1;
    private int num_bonus = 5;
    private int num_minus = 5;
    private int num_italian_chef = 3;
    private int num_chinese_chef = 3;

    private int rows = 16, columns = 16;

    private boolean inGame;

    public Board(JLabel statusBar, String playerName, String level, int cookingSkills) {
        // Game setup from input
    	this.statusBar = statusBar;
        this.level = new Level(level);
        this.player = new Player(playerName, cookingSkills);
        this.player.setEnergy(this.level.getInitEnergy());
        updateHighScore();

        // Input images for cell in an array
        this.img = new Image[NUM_IMAGES];
        for (int i = 0; i < NUM_IMAGES; i++) {
            String path = "img/j" + i + ".jpg";
            img[i] = new ImageIcon(path).getImage();
        }

        this.setDoubleBuffered(true);
        this.addMouseListener(new MinesAdapter());
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
        this.statusBar.setText("Player: " + this.player.getPlayerName()
        						+ "      Energy: " + Integer.toString(this.player.getEnergy())
        						+ "      Cooking Skills: " + Integer.toString(this.player.getCookingSkills())
        						+ "      Chopping: " + Integer.toString(this.player.getChopping())
        						+ "          High Score: " + Integer.toString(highscore));

        // Assign player to a random place on board and remember player's position
        while (num_player > 0) {
            int randX = random.nextInt(this.rows);
            int randY = random.nextInt(this.columns);

            Cell cell = this.cells[randX][randY];
            if (cell.isEmpty()) {
                cell.setPlayer(true);
                playerPos[0] = randX;
                playerPos[1] = randY;
                //System.out.println("init player: " + playerPos[0] + ", " + playerPos[1]);
                num_player--;
            }
        }
        
        // Assign goal to a random place on board
        while (num_goal > 0) {
            int randX = random.nextInt(this.rows);
            int randY = random.nextInt(this.columns);

            Cell cell = this.cells[randX][randY];
            if (cell.isEmpty()) {
                cell.setGoal(true);
                //System.out.println("init goal: " + randX + ", " + randY);
                num_goal--;
            }
        }
      
        // Assign bonus to random places on board
        while (num_bonus > 0) {
            int randX = random.nextInt(this.rows);
            int randY = random.nextInt(this.columns);

            Cell cell = this.cells[randX][randY];
            if (cell.isEmpty()) {
                cell.setBonus(true);
                num_bonus--;
            }
        }
      
        // Assign minus to random places on board
        while (num_minus > 0) {
            int randX = random.nextInt(this.rows);
            int randY = random.nextInt(this.columns);

            Cell cell = this.cells[randX][randY];
            if (cell.isEmpty()) {
                cell.setMinus(true);
                num_minus--;
            }
        }

        // Assign competitor_1 (Italian chef: cooking skills) to random places on board
        while (num_italian_chef > 0) {
            int randX = random.nextInt(this.rows);
            int randY = random.nextInt(this.columns);

            Cell cell = this.cells[randX][randY];
            if (cell.isEmpty()) {
                cell.setItalianChef(true);
                num_italian_chef--;
            }
        }
        
        // Assign competitor_2 (Chinese chef: chopping) to random places on board
        while (num_chinese_chef > 0) {
            int randX = random.nextInt(this.rows);
            int randY = random.nextInt(this.columns);

            Cell cell = this.cells[randX][randY];
            if (cell.isEmpty()) {
                cell.setChineseChef(true);
                num_chinese_chef--;
            }
        }   
    }

    // Print images in each cell on the board
    public void paint(Graphics g) {
    	boolean gamewin = false;
    	
    	// Update status bar information
    	this.statusBar.setText("Player: " + this.player.getPlayerName()
		+ "      Energy: " + Integer.toString(this.player.getEnergy())
		+ "      Cooking Skills: " + Integer.toString(this.player.getCookingSkills())
		+ "      Chopping: " + Integer.toString(this.player.getChopping())
		+ "          High Score: " + Integer.toString(highscore));

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                Cell cell = this.cells[i][j];
                int imageType;
                int xPosition, yPosition;
                xPosition = (j * CELL_SIZE);
                yPosition = (i * CELL_SIZE);

                // Game is over whenever player is out of energy
                if (inGame) {	
                    if (player.getEnergy() == 0) {
                        inGame = false;
                    }
                }
                
                // Game is won and over if player reaches the goal before consuming up energy
            	if (cell.isGoal() && cell.isPlayer() && player.getEnergy() >= 0) {
                    inGame = false;
                    gamewin = true;
                    score = player.getEnergy() + player.getChopping() + player.getCookingSkills() + 1;
                    updateHighScore();
                    
                    if (score > highscore) {    
                        statusBar.setText("Game won! You now have the new high score of "
                        					+ score + "! The previous high score was " + highscore + ".");
                    } else if (score == highscore) {
                    	statusBar.setText("Game won! You tied the high score of " + score + "!");
                    } else {
                    	statusBar.setText("Game won! Your score is " + score
                    						+ ". The all time high score was " + highscore + ".");
                    }
                
                // Game is lost and over if player is out of energy and not winning the game
                } else if (!inGame && !gamewin) {
                    statusBar.setText("You're out of energy. Game Lost!");
                }

            	// Set cells visible around the player
            	if (isAroundPlayer(i,j)) {
            		cell.setVisible(true);
            	}
            	
            	// Set the cell visible where the player is at
            	else if (i == playerPos[0] && j == playerPos[1]) {
            		cell.setVisible(true);
            	}
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
    class MinesAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            int pressedCol = e.getX() / CELL_SIZE;
            int pressedRow = e.getY() / CELL_SIZE;

            boolean doRepaint = false;
            Cell pressedCell;

            // Out of bounds
            if ((pressedCol < 0 || pressedCol >= columns)
                || (pressedRow < 0 || pressedRow >= rows)) {
                return;
            }

            pressedCell = cells[pressedRow][pressedCol];

            // Reaction to only left click on visible cells
            if (e.getButton() == MouseEvent.BUTTON3) {
                return;
            } else if (!pressedCell.isVisible()) {
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
                    	player.setEnergy(player.getEnergy() + 3 + 1);
                    	cells[pressedRow][pressedCol].setBonus(false);
                    }
                    
                    else if (pressedCell.isMinus()) {
                    	player.setEnergy(player.getEnergy() - 3 + 1);
                    	cells[pressedRow][pressedCol].setMinus(false);
                    }
                    
                    else if (pressedCell.isItalianChef()) {
                    	if (player.getCookingSkills() >= level.getItalianChefSkill()) {
                    		player.setCookingSkills(player.getCookingSkills() + 2);
                    	}
                    	else {
                    		player.setCookingSkills(player.getCookingSkills() - 2);
                    	}
                    	cells[pressedRow][pressedCol].setItalianChef(false);
                    }
                    
                    else if (pressedCell.isChineseChef()) {
                    	if (player.getChopping() >= level.getChineseChefSkill()) {
                    		player.setChopping(player.getChopping() + 2);
                    	}
                    	else {
                    		player.setChopping(player.getChopping() - 2);
                    	}
                    	cells[pressedRow][pressedCol].setChineseChef(false);
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
    
    	// Read the current high score
    	try {
    	BufferedReader reader = new BufferedReader(new FileReader("score.txt"));
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
        reader.close();

    } catch (IOException ex) {
        System.err.println("ERROR reading scores from file");
        }

    	// Append the last score to the end of the file
		try {
	    BufferedWriter output = new BufferedWriter(new FileWriter("score.txt", true));
	    output.newLine();
	    output.append("" + score);
	    output.close();
		} catch (IOException ex1) {
	    System.out.printf("ERROR writing score to file: %s\n", ex1);
	    }
    }
}