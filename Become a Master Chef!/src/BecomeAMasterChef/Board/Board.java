package BecomeAMasterChef.Board;

import BecomeAMasterChef.*;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

    private int num_player = 1;
    private int num_goal = 1;
    private int num_bonus = 5;
    private int num_minus = 5;
    private int num_italian_chef = 3;
    private int num_chinese_chef = 3;

    private int rows = 16, columns = 16;
    
    private Cell[][] cells;
    private int[] playerPos = new int[2];
    private Image[] img;
    private Gamer player;
    private Level level;
    private int highscore;

    private boolean inGame;

    public Board(JLabel statusBar) {
        this.statusBar = statusBar;

        //save images in an array
        this.img = new Image[NUM_IMAGES];
        for (int i = 0; i < NUM_IMAGES; i++) {
            String path = "img/j" + i + ".jpg";
            img[i] = new ImageIcon(path).getImage();
        }

        this.setDoubleBuffered(true);
        this.addMouseListener(new MinesAdapter());
        this.newGame();
    }

    private void initCells () {
        this.cells = new Cell[rows][columns];

        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                this.cells[i][j] = new Cell();
            }
        }
    }

    public void newGame () {
        Random random = new Random();
        player = new Gamer("Fisher");		//import player
        level = new Level();				//import level
        player.setEnergy(level.getInitEnergy());
        highscore = 0;
        
        this.inGame = true;

        this.initCells();
        this.statusBar.setText("Player: " + this.player.getPlayername()
        						+ "      Energy: " + Integer.toString(this.player.getEnergy())
        						+ "      Cooking Skills: " + Integer.toString(this.player.getCookingSkills())
        						+ "      Chopping: " + Integer.toString(this.player.getChopping())
        						+ "          High Score: " + Integer.toString(highscore));

        //Assign player to a random place on board
        while (num_player > 0) {
            int randX = random.nextInt(this.rows);
            int randY = random.nextInt(this.columns);

            Cell cell = this.cells[randX][randY];
            if (cell.isEmpty()) {
                cell.setPlayer(true);
                playerPos[0] = randX;
                playerPos[1] = randY;
                System.out.println("init player: " + playerPos[0] + ", " + playerPos[1]);
                num_player--;
            }
        }
        
        //Assign goal to a random place on board
        while (num_goal > 0) {
            int randX = random.nextInt(this.rows);
            int randY = random.nextInt(this.columns);

            Cell cell = this.cells[randX][randY];
            if (cell.isEmpty()) {
                cell.setGoal(true);
                System.out.println("init goal: " + randX + ", " + randY);
                num_goal--;
            }
        }
      
        //Assign bonus to random places on board
        while (num_bonus > 0) {
            int randX = random.nextInt(this.rows);
            int randY = random.nextInt(this.columns);

            Cell cell = this.cells[randX][randY];
            if (cell.isEmpty()) {
                cell.setBonus(true);
                num_bonus--;
            }
        }
      
        //Assign minus to random places on board
        while (num_minus > 0) {
            int randX = random.nextInt(this.rows);
            int randY = random.nextInt(this.columns);

            Cell cell = this.cells[randX][randY];
            if (cell.isEmpty()) {
                cell.setMinus(true);
                num_minus--;
            }
        }

        //Assign competitor_1 to random places on board
        while (num_italian_chef > 0) {
            int randX = random.nextInt(this.rows);
            int randY = random.nextInt(this.columns);

            Cell cell = this.cells[randX][randY];
            if (cell.isEmpty()) {
                cell.setItalianChef(true);
                num_italian_chef--;
            }
        }
        
        //Assign competitor_2 to random places on board
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

    //print images on board
    public void paint(Graphics g) {
    	boolean gamewin = false;
    	int score;
    	
    	this.statusBar.setText("Player: " + this.player.getPlayername()
		+ "      Energy: " + Integer.toString(this.player.getEnergy())
		+ "      Cooking Skills: " + Integer.toString(this.player.getCookingSkills())
		+ "      Chopping: " + Integer.toString(this.player.getChopping())
		+ "          High Score: " + Integer.toString(highscore));

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                Cell cell = this.cells[i][j];
                int imageType;
                int xPosition, yPosition;

                if (inGame) {	
                    if (player.getEnergy() == 0) {
                        inGame = false;
                    }
                }
                
            	if (cell.isGoal() && cell.isPlayer() && player.getEnergy() >= 0) {
                    inGame = false;
                    gamewin = true;
                    score = player.getEnergy() + player.getChopping() + player.getCookingSkills();
                    statusBar.setText("Game Won! Your score is: " + score
                    				    + "        Current Highscore: " + highscore);
                    if (score > highscore) {
                    	highscore = score;
                    }
                } else if (!inGame && !gamewin) {
                    statusBar.setText("You're out of energy. Game Lost!");
                }

            	if (isAroundPlayer(i,j)) {
            		cell.setVisible(true);
            	}
            	else if (i == playerPos[0] && j == playerPos[1]) {
            		cell.setVisible(true);
            	}
            	else {
            		cell.setVisible(false);
            	}
            	
            	imageType = this.decideImageType(cell);

                xPosition = (j * CELL_SIZE);
                yPosition = (i * CELL_SIZE);

                g.drawImage(img[imageType], xPosition, yPosition, this);
            }
        }
    }

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


    class MinesAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            int pressedCol = e.getX() / CELL_SIZE;
            int pressedRow = e.getY() / CELL_SIZE;

            boolean doRepaint = false;
            Cell pressedCell;
 
            if (!inGame) {
                newGame();
                repaint();
            }

            if ((pressedCol < 0 || pressedCol >= columns)
                || (pressedRow < 0 || pressedRow >= rows)) {
                return;
            }

            pressedCell = cells[pressedRow][pressedCol];

            if (e.getButton() == MouseEvent.BUTTON3) {
                return;
            } else if (!pressedCell.isVisible()) {
            	return;
            } else if (pressedCell.isVisible()) {
            		//Move player position
            		cells[playerPos[0]][playerPos[1]].setPlayer(false);
            		playerPos[0] = pressedRow;
            		playerPos[1] = pressedCol;
            		cells[playerPos[0]][playerPos[1]].setPlayer(true);

                	//Consume energy
            		player.setEnergy(player.getEnergy() - 1);
                	
                    if (pressedCell.isBonus()) {
                    	player.setEnergy(player.getEnergy() + 4);
                    	cells[pressedRow][pressedCol].setBonus(false);
                    }
                    
                    else if (pressedCell.isMinus()) {
                    	player.setEnergy(player.getEnergy() - 2);
                    	cells[pressedRow][pressedCol].setMinus(false);
                    }
                    
                    else if (pressedCell.isItalianChef()) {
                    	if (player.getCookingSkills() >= level.getItalianChefSkill()) {
                    		player.setCookingSkills(player.getCookingSkills() + 1);
                    	}
                    	else {
                    		player.setCookingSkills(player.getCookingSkills() - 1);
                    	}
                    	cells[pressedRow][pressedCol].setItalianChef(false);
                    }
                    
                    else if (pressedCell.isChineseChef()) {
                    	if (player.getChopping() >= level.getChineseChefSkill()) {
                    		player.setChopping(player.getChopping() + 1);
                    	}
                    	else {
                    		player.setChopping(player.getChopping() - 1);
                    	}
                    	cells[pressedRow][pressedCol].setChineseChef(false);
                    }

                    doRepaint = true;
                }

            if (doRepaint) {
                repaint(); 
            }
        }
    }
}