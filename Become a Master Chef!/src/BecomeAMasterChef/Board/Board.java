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
    private final int CELL_SIZE  = 15;			//change to larger size
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

    private int total_italian_chef = 3;
    private int total_chinese_chef = 3;
    private int total_bonus = 3;
    private int total_minus = 3;

    private int rows = 16, columns = 16;

    private Cell[][] cells;
    private Image[] img;
    private Gamer player;
    private Level level;

    private boolean inGame;

    public Board(JLabel statusBar) {
        this.statusBar = statusBar;

        this.img = new Image[NUM_IMAGES];
        for (int i = 0; i < NUM_IMAGES; i++) {
            String path = "img/j" + i + ".gif";
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
        player = new Gamer("Fihser");		//import player_name from BaMC
        level = new Level(1);				//import level from Level
        
        this.inGame = true;

        this.initCells();
        this.statusBar.setText(Integer.toString(this.player.getEnergy()));

        int energy = level.getInitEnergy();
        while (energy >= 0) {
            int randX = random.nextInt(this.rows);
            int randY = random.nextInt(this.columns);

            Cell cell = this.cells[randX][randY];
            if (!cell.isPlayer()) {
                cell.setPlayer(true);
                energy--;
            }
        }

        this.player.setEnergy(energy);
    }

    public void paint(Graphics g) {
        int coveredCells = 0;

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                Cell cell = this.cells[i][j];
                int imageType;
                int xPosition, yPosition;

                if (!cell.isVisible()) {
                    coveredCells++;
                }

                if (inGame) {
                    if (player.getEnergy() == 0) {
                        inGame = false;
                    }
                }

                imageType = this.decideImageType(cell);

                xPosition = (j * CELL_SIZE);
                yPosition = (i * CELL_SIZE);

                g.drawImage(img[imageType], xPosition, yPosition, this);
            }
        }

        if (cell.isGoal() && cell.isPlayer()) {
            inGame = false;
            statusBar.setText("Game Won");
            //calculate and update high score
        } else if (!inGame) {
            statusBar.setText("Game Lost");
        }
    }

    private int decideImageType(Cell cell) {
        int imageType;

        if (!inGame) {
            if (cell.isGoal()) {
                imageType = IMAGE_GOAL;
            } else if (cell.isPlayer()) {
                imageType = IMAGE_PLAYER;
            } else if (cell.isBonus()) {
                imageType = IMAGE_BONUS;
            } else if (cell.isMinus()) {
            	imageType = IMAGE_MINUS;
            } else if (cell.isChineseChef()) {
                imageType = IMAGE_CHINESE_CHEF;
            } else if (cell.isItalianChef()) {
            	imageType = IMAGE_ITALIAN_CHEF;
            } else {
                imageType = IMAGE_VISIBLE;
            }
        }
        
        else {
        	if (isAroundPlayer() && cell.is) {
        		imageType = IMAGE_COVER;
        	}
        	else {
        		imageType = IMAGE_COVER;
        	}
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
    
    public void findEmptyCells(int x, int y, int depth) {

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

                if (!(i == 0 || j == 0)) {
                    continue;
                }

                Cell cell = this.cells[xIndex][yIndex];
                if (checkEmpty(cell)) {
                    cell.uncover();
                    cell.checked();

                    uncoverAroundCell(xIndex, yIndex);
                    findEmptyCells(xIndex, yIndex, depth + 1);
                }
            }
        }

        if (depth == 0) {
            this.clearAllCells();
        }
    }

    private void uncoverAroundCell(int x, int y) {
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

                if (i == 0 || j == 0) {
                    continue;
                }

                Cell cell = this.cells[xIndex][yIndex];
                if (cell.isCovered() && !cell.isEmpty()) {
                    cell.uncover();
                }
            }
        }
    }

    private boolean checkEmpty(Cell cell) {
        if (!cell.isChecked()) {
            if (cell.isEmpty()) {
                return true;
            }
        }

        return false;
    }

    private void clearAllCells() {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                this.cells[i][j].clearChecked();
            }
        }
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
            } else {
                if (pressedCell.isVisible()) {
                    //Remove player from the original place
                	pressedCell.setPlayer(true);	//Move player here
                	//Set new visible area
                }
                else {
                	return;
                }
                
                if (pressedCell.isVisible() && pressedCell.isBonus()) {
                	player.setEnergy(player.getEnergy() + 3);
                }
                
                else if (pressedCell.isVisible() && pressedCell.isMinus()) {
                	player.setEnergy(player.getEnergy() - 3);
                }
                
                else if (pressedCell.isVisible() && pressedCell.isItalianChef()) {
                	if (player.getCookingSkills() > level.getItalianChefSkill()) {
                		player.setCookingSkills(player.getCookingSkills() + 1);
                	}
                	else {
                		player.setCookingSkills(player.getCookingSkills() - 1);
                	}
                }
                
                else if (pressedCell.isVisible() && pressedCell.isChineseChef()) {
                	if (player.getChopping() > level.getChineseChefSkill()) {
                		player.setChopping(player.getChopping() + 1);
                	}
                	else {
                		player.setChopping(player.getChopping() - 1);
                	}
                }
                
                else if (pressedCell.isVisible() && pressedCell.isGoal()) {
                	//Player's win, game over!
                	//Calculate score
                	//Update highscore
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