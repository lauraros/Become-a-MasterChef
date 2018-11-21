package BecomeAMasterChef;

public class Level {
	private int level;
	private int init_energy;									//initial energy
	private int init_cooking_skills;							//initial cooking_skills
	private int init_chopping = 10 - init_cooking_skills;		//initial chopping
	private int italian_chef_skill;							//Italian chef's cooking_skills
	private int chinese_chef_skill;							//Chinese chef's chopping
	
	public Level(int level) {
		this.level = level;
	
		switch (level) {
		case 1: 
			init_energy = 50;
			italian_chef_skill = 4;
			chinese_chef_skill = 4;
			break;
		
		case 2:
			init_energy = 40;
			italian_chef_skill = 5;
			chinese_chef_skill = 5;
			break;
			
		case 3:
			init_energy = 30;
			italian_chef_skill = 6;
			chinese_chef_skill = 6;
			break;
			
		case 4:
			init_energy = 20;
			italian_chef_skill = 7;
			chinese_chef_skill = 7;
			break;
		}
	}
	
	public void setInitCookingSkills(int value) {
		init_cooking_skills = value;
	}
	
	public int getInitCookingSkills() {
		return init_cooking_skills;
	}
	
	public void setInitChopping(int value) {
		init_chopping = value;
	}
	
	public int getInitEnergy() {
		return init_energy;
	}
	
	public int getInitChopping() {
		return init_chopping;
	}
	
	public int getItalianChefSkill() {
		return italian_chef_skill;
	}
	
	public int getChineseChefSkill() {
		return chinese_chef_skill;
	}
}
