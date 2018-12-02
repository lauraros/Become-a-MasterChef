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
    private final int CELL_SIZE  = 50;
    private final int NUM_IMAGES = 8;

    private final int IMAGE_GOAL       		= 0;
    private final int IMAGE_ITALIAN_CHEF    = 1;
    private final int IMAGE_CHINESE_CHEF    = 2;
    private final int IMAGE_BONUS 			= 3;
    private final int IMAGE_MINUS       	= 4;
    private final int IMAGE_PLAYER     		= 5;
    private final int IMAGE_COVER		    = 6;
    private final int IMAGE_VISIBLE			= 7;

    private JLabel statusBar;

    private int num_player = 1;
    private int num_goal = 1;
    private int num_bonus = 3;
    private int num_minus = 3;
    private int num_italian_chef = 3;
    private int num_chinese_chef = 3;

    private int rows = 16, columns = 16;

    private int energy;
    
    private Cell[][] cells;
    private Cell playerCell;
    private Image[] img;
    private Gamer player;
    private Level level;

    private boolean inGame;

    public Board(JLabel statusBar) {
        this.statusBar = statusBar;

        this.img = new Image[NUM_IMAGES];
        for (int i = 0; i < NUM_IMAGES; i++) {
            String path = "img/g" + i + ".tiff";
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
        player = new Gamer("Fihser");		//import player
        level = new Level();				//import level
        energy = level.getInitEnergy();
        player.setEnergy(energy);
        
        this.inGame = true;

        this.initCells();
        this.statusBar.setText(Integer.toString(this.player.getEnergy()));

        while (num_player >= 0) {
            int randX = random.nextInt(this.rows);
            int randY = random.nextInt(this.columns);

            playerCell = this.cells[randX][randY];
            if (playerCell.isEmpty()) {
                playerCell.setPlayer(true);
                num_player--;
            }
        }
        
        while (num_goal >= 0) {
            int randX = random.nextInt(this.rows);
            int randY = random.nextInt(this.columns);

            Cell cell = this.cells[randX][randY];
            if (cell.isEmpty()) {
                cell.setGoal();
                num_goal--;
            }
        }
        
        while (num_bonus >= 0) {
            int randX = random.nextInt(this.rows);
            int randY = random.nextInt(this.columns);

            Cell cell = this.cells[randX][randY];
            if (cell.isEmpty()) {
                cell.setBonus();
                num_bonus--;
            }
        }
        
        while (num_minus >= 0) {
            int randX = random.nextInt(this.rows);
            int randY = random.nextInt(this.columns);

            Cell cell = this.cells[randX][randY];
            if (cell.isEmpty()) {
                cell.setMinus();
                num_minus--;
            }
        }

        while (num_italian_chef >= 0) {
            int randX = random.nextInt(this.rows);
            int randY = random.nextInt(this.columns);

            Cell cell = this.cells[randX][randY];
            if (cell.isEmpty()) {
                cell.setItalianChef();
                num_italian_chef--;
            }
        }
        
        while (num_chinese_chef >= 0) {
            int randX = random.nextInt(this.rows);
            int randY = random.nextInt(this.columns);

            Cell cell = this.cells[randX][randY];
            if (cell.isEmpty()) {
                cell.setChineseChef();
                num_chinese_chef--;
            }
        }   
    }

    public void paint(Graphics g) {
        int highscore = 0;

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
                    statusBar.setText("Game Won");
                    highscore = player.getEnergy() + player.getChopping() + player.getCookingSkills();
                    if (highscore > player.getHighScore()) {
                    	player.setHighScore(highscore);
                    }
                } else if (!inGame) {
                    statusBar.setText("You're out of energy. Game Lost!");
                }

            	if (isAroundPlayer(i,j)) {
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
                	playerCell.setPlayer(false);
                	pressedCell.setPlayer(true);	//Move player here
                	playerCell = pressedCell;
                	energy--;
                	
                    if (pressedCell.isBonus()) {
                    	player.setEnergy(energy + 3);
                    }
                    
                    else if (pressedCell.isMinus()) {
                    	player.setEnergy(energy - 3);
                    }
                    
                    else if (pressedCell.isItalianChef()) {
                    	if (player.getCookingSkills() >= level.getItalianChefSkill()) {
                    		player.setCookingSkills(player.getCookingSkills() + 1);
                    	}
                    	else {
                    		player.setCookingSkills(player.getCookingSkills() - 1);
                    	}
                    }
                    
                    else if (pressedCell.isChineseChef()) {
                    	if (player.getChopping() >= level.getChineseChefSkill()) {
                    		player.setChopping(player.getChopping() + 1);
                    	}
                    	else {
                    		player.setChopping(player.getChopping() - 1);
                    	}
                    }
                    
                    else if (pressedCell.isGoal()) {
                    	pressedCell.setPlayer(true);
                    }
                    
                    else {
                    	return;
                    }

                    doRepaint = true;
                }

            if (doRepaint) {
                repaint();
            }
        }
    }
}