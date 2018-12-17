package become_a_masterchef.game;

/* A player object saves its name, energy value, and skill sets. */
public class Player {
	private String playerName;		// player's name
	private int energy;				// player's energy
	private int cooking_skills;		// player's cooking skills
	private int chopping;			// player's chopping

	// Constructor 
	public Player(String playerName, int cookingSkills) {
		this.playerName = playerName;
		this.cooking_skills = cookingSkills;
		this.chopping = 10 - cookingSkills;
	}

	// Setter and getter for each class variable
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
		// cooking skills should always be positive or zero
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
		// chopping should always be positive or zero
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