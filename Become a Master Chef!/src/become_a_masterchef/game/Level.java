package become_a_masterchef.game;

//An level object decides the initial settings of player's energy and opponents skill sets
public class Level {
	private String levelName;
	private int init_energy;								//Initial energy
	private int italian_chef_skill;							//Italian chef's cooking_skills
	private int chinese_chef_skill;							//Chinese chef's chopping
	
	public Level(String levelName) {
		
		this.levelName = levelName;
		switch (levelName) {
		case "Easy": 
			init_energy = 50;
			italian_chef_skill = 4;
			chinese_chef_skill = 4;
			break;
		
		case "Medium":
			init_energy = 40;
			italian_chef_skill = 5;
			chinese_chef_skill = 5;
			break;
			
		case "Hard":
			init_energy = 30;
			italian_chef_skill = 6;
			chinese_chef_skill = 6;
			break;
			
		case "Hardest":
			init_energy = 20;
			italian_chef_skill = 7;
			chinese_chef_skill = 7;
			break;
		}
	}
	
	public void setLevelName(String levelName){
		this.levelName = levelName;
	}
	
	public String getLevelName(){
		return this.levelName;
	}
	
	public int getInitEnergy() {
		return init_energy;
	}
	
	public int getItalianChefSkill() {
		return italian_chef_skill;
	}
	
	public int getChineseChefSkill() {
		return chinese_chef_skill;
	}
}
