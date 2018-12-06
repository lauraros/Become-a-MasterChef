package BecomeAMasterChef.Board;

// A cell object controls one cell on the board
public class Cell {
    private boolean player;
    private boolean cover;			//cell is invisible
    private boolean empty;			//cell is empty
    private boolean goal;			//MasterChef Title
    private boolean bonus;			//bonus is an event that increases energy
    private boolean minus;			//bonus is an event that decreases energy
    private boolean italian_chef;	//cooking_skills_competitor
    private boolean chinese_chef;	//chopping_competitor

    public Cell() {
        this.player = false;
        this.cover = true;
        this.empty = true;
        this.goal  = false;
        this.bonus  = false;
        this.minus = false;
        this.italian_chef  = false;
        this.chinese_chef  = false;
    }

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
    	this.italian_chef = b;
    }
    
    public boolean isItalianChef() {
    	return this.italian_chef;
    }
    
    public void setChineseChef(boolean b) {
    	this.chinese_chef = b;
    }
    
    public boolean isChineseChef() {
    	return this.chinese_chef;
    }
}