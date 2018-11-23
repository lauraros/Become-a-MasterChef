package BecomeAMasterChef;

import BecomeAMasterChef.Player.Player;

public class SetUp {
	private Player player = new Player();
	private Level newlevel = new Level();
	private int skill1;
	private int skill2;
	
	public SetUp(String playerName, String level, int skill1, int skill2) {
		player.setPlayerName(playerName);
		newlevel.setLevelName(level);
		this.skill1 = skill1;
		this.skill2 = skill2;
	}
	public SetUp(String level, int skill1, int skill2) {
		player.setPlayerName("Player");
		newlevel.setLevelName(level);
		this.skill1 = skill1;
		this.skill2 = skill2;
	}
	

	public void setSkill1(int skill1){
		this.skill1 = skill1;
	}
	
	public int getSkill1(){
		return this.skill1;
	}
	
	public void setSkill2(int skill2){
		this.skill2 = skill2;
	}
	
	public int getSkill2(){
		return this.skill2;
	}
	public Level getNewlevel() {
		return newlevel;
	}
	public void setNewlevel(Level newlevel) {
		this.newlevel = newlevel;
	}
	
}

