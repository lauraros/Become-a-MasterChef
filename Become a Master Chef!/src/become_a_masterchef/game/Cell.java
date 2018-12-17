package become_a_masterchef.game;

/* A cell object controls one cell of the board. */
public class Cell {
	private boolean player;			// player is at the cell
	private boolean cover;			// cell is invisible
	private boolean empty;			// cell is empty
	private boolean goal;			// MasterChef Title is at the cell
	private boolean bonus;			// bonus is at the cell (increases energy)
	private boolean minus;			// minus is at the cell (decreases energy)
	private boolean italianChef;	// cooking_skills opponent is at the cell
	private boolean chineseChef;	// chopping opponent is at the cell

	// Constructor 
	public Cell() {
		this.player = false;
		this.cover = true;
		this.empty = true;
		this.goal  = false;
		this.bonus  = false;
		this.minus = false;
		this.italianChef  = false;
		this.chineseChef  = false;
	}

	// Setter and getter for each class variable
	public void setPlayer(boolean b) {
		this.player = b;
	}

	public boolean isPlayer() {
		return this.player;
	}

	public boolean isEmpty() {
		if (!this.isPlayer() && !this.isGoal() && !this.isBonus() && !this.isMinus() && 
				!this.isChineseChef() && !this.isItalianChef()) {
			empty = true;
		}
		else {
			empty = false;
		}
		return empty;
	}

	public void setVisible(boolean b) {
		this.cover = !b;
	}

	public boolean isVisible() {
		return !this.cover;
	}

	public void setGoal(boolean b) {
		this.goal = b;
	}

	public boolean isGoal() {
		return this.goal;
	}

	public void setBonus(boolean b) {
		this.bonus = b;
	}

	public boolean isBonus() {
		return this.bonus;
	}

	public void setMinus(boolean b) {
		this.minus = b;
	}

	public boolean isMinus() {
		return this.minus;
	}

	public void setItalianChef(boolean b) {
		this.italianChef = b;
	}

	public boolean isItalianChef() {
		return this.italianChef;
	}

	public void setChineseChef(boolean b) {
		this.chineseChef = b;
	}

	public boolean isChineseChef() {
		return this.chineseChef;
	}
}