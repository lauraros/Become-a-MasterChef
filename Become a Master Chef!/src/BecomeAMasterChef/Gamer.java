package BecomeAMasterChef;

public class Gamer {
	private String playername;
	private int energy;
	private int cooking_skills;
	private int chopping = 10 - cooking_skills;
	private int highscore = 0;
	
	public Gamer(String playername) {
		this.playername = playername;
	}
	
	public String getPlayername() {
		return playername;
	}
	
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public void setCookingSkills(int cooking_skills) {
		this.cooking_skills = cooking_skills;
	}
	
	public int getCookingSkills() {
		return cooking_skills;
	}
	
	public void setChopping(int chopping) {
		this.chopping = chopping;
	}
	
	public int getChopping() {
		return chopping;
	}
	
	public void setHighScore(int highscore) {
		this.highscore = highscore;
	}
	
	public int getHighScore() {
		return highscore;
	}
}
