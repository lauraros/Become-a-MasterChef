package become_a_masterchef.game;

/*
 * A level object decides the initial settings of player's energy 
 * and competitors' skill sets.
 */
public class Level {
	private String levelName;
	private int initEnergy;								//Initial energy
	private int italianChefSkill;							//Italian chef's cooking_skills
	private int chineseChefSkill;							//Chinese chef's chopping
	private String scoreFilePath;							// Path of score file

	// Constructor 
	public Level(String levelName) {

		this.levelName = levelName;
		switch (levelName) {
		case "Easy": 
			setInitEnergy(50);
			setItalianChefSkill(4);
			setChineseChefSkill(4);
			setScoreFilePath("src/scores/easyScores.txt");
			break;

		case "Medium":
			setInitEnergy(40);
			setItalianChefSkill(5);
			setChineseChefSkill(5);
			setScoreFilePath("src/scores/mediumScores.txt");

			break;

		case "Hard":
			setInitEnergy(30);
			setItalianChefSkill(6);
			setChineseChefSkill(6);
			setScoreFilePath("src/scores/hardScores.txt");

			break;

		case "Hardest":
			setInitEnergy(20);
			setItalianChefSkill(7);
			setChineseChefSkill(7);
			setScoreFilePath("src/scores/hardestScores.txt");

			break;
		}
	}
	// Setter and getter for each class variable
	public void setLevelName(String levelName){
		this.levelName = levelName;
	}

	public String getLevelName(){
		return this.levelName;
	}

	public void setInitEnergy(int initEnergy){
		this.initEnergy = initEnergy;
	}
	
	public int getInitEnergy() {
		return initEnergy;
	}

	public void setItalianChefSkill(int italianChefSkill){
		this.italianChefSkill = italianChefSkill;
	}
	
	public int getItalianChefSkill() {
		return italianChefSkill;
	}
	
	public void setChineseChefSkill(int chineseChefSkill){
		this.chineseChefSkill = chineseChefSkill;
	}

	public int getChineseChefSkill() {
		return chineseChefSkill;
	}

	public void setScoreFilePath(String scoreFilePath) {
		this.scoreFilePath = scoreFilePath;
	}
	
	public String getScoreFilePath() {
		return scoreFilePath;
	}

}
