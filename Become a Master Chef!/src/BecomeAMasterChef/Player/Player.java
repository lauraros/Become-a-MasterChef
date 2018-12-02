package BecomeAMasterChef.Player;


public class Player {
	/*Player attributes*/
    public String playerName;
    private int energy = 0;
    //private int ingredients = 3;
	private int cookingTalent;
	private int chopping;
    private int totalScore;    
    private boolean isAlive = false;
  
    public Player() {
		setPlayerName("Player");
		setCookingTalent(5);
		setChopping();
		setEnergy(energy);//from level, board or Bamc?
		giveBirth();
    }
    
    public Player(String playerName) {
		setPlayerName(playerName);
		setCookingTalent(5);
		setChopping();
		setEnergy(energy);//from level, board or Bamc?
		giveBirth();
    }
    
    public Player(String playerName, int cookingTalent) {
		setPlayerName(playerName);
		setCookingTalent(cookingTalent);
		setChopping();
		setEnergy(energy);//from level, board or Bamc?
		giveBirth();
    }
    
    public Player(String playerName, int cookingTalent, int energy) {
		setPlayerName(playerName);
		setCookingTalent(cookingTalent);
		setChopping();
		setEnergy(energy);//from level, board or Bamc?
		giveBirth();
    }
    
    public void setPlayerName(String name){
        this.playerName = name;
    }

    public  String getPlayerName(){
        return this.playerName;
    }

	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public void setCookingTalent(int cookingTalent) {
		this.cookingTalent = cookingTalent;
	}
	
	public int getCookingTalent() {
		return cookingTalent;
	}
	
	public void setChopping() {
		this.chopping = 10 - cookingTalent;
	}
	
	public int getChopping() {
		return chopping;
	}	
	
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}	
	
	public int getTotalScore() {
		return totalScore;
	}	
	
    public void loseEnergy(int lostEnergy){
        this.energy -= lostEnergy ;
    }
    
    public void addEnergy(int gainedEnergy){
    	this.energy += gainedEnergy;
    }
    	
    public void loseScore(int lostScore){
    	this.totalScore -= lostScore ;
    }
    
    public void addScore(int gainedScore){
    	this.totalScore += gainedScore;
    }

    public void setIsAlive(boolean life) {
    	this.isAlive = life;
    }

    public void giveBirth() {
    	this.isAlive = true;
    }
    
    public void killPlayer() {
    	this.isAlive = false;
    }
}

