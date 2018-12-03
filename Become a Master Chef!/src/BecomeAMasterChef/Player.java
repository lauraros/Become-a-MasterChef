package BecomeAMasterChef;

public class Player {
	private String playerName;
	private int energy;
	private int cooking_skills;
	private int chopping;
	
	public Player(String playerName, int cookingSkills) {
		this.playerName = playerName;
		this.cooking_skills = cookingSkills;
		this.chopping = 10 - cookingSkills;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public String getPlayerName() {
		return playerName;
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
}