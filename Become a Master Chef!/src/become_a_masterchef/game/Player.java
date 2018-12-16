package become_a_masterchef.game;

/* A player object saves its name, energy value, and skill sets. */
public class Player {
	private String playerName;
	private int energy;
	private int cooking_skills;
	private int chopping;
	
	// Constructor 
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
		if (cooking_skills >= 0) {
		this.cooking_skills = cooking_skills;
		} else {
			this.cooking_skills = 0;
		}
	}
	
	public int getCookingSkills() {
		return cooking_skills;
	}
	
	public void setChopping(int chopping) {
		if (chopping >= 0) {
			this.chopping = chopping;
			} else {
				this.chopping = 0;
			}
	}
	
	public int getChopping() {
		return chopping;
	}
}