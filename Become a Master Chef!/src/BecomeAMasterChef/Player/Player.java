package BecomeAMasterChef.Player;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String playerName;
    private int totalScore;
    private int ingredients = 3;
    private boolean isAlive = true;
    private List<Object> objects;
    //Deque<Cell> path = new ArrayDeque<Cell>();
    //Cell currentPosition=null;//= path.peekLast();
    //Cell previousPosition=null;//= path.peekLast();
    
    Player() {
		//Player player2= new Player();
		Scanner input1= new Scanner(System.in);
		System.out.println("What is your name?");
		String player1Name = input1.nextLine();
		if (player1Name.equals("")) {
			setPlayerName("Player");
		}else
		setPlayerName(player1Name);
    }
    public void setPlayerName(String name){
        this.playerName = name;
    }

    public  String getPlayerName(){
        return this.playerName;
    }

    public int getIngredients(){
        return ingredients ;
    }

    public void loseOneLife(){
        ingredients -= 1 ;
    }
    
    public void addOneLife(){
        ingredients += 1;
    }
    	
    public void loseIngredients(int lIngredients){
        ingredients -= lIngredients ;
    }
    
    public void addIngredients(int wIngredients){
        ingredients += wIngredients;
    }
    /*public void setCurrentPosition(Cell cell){
        currentPosition=cell;
    }
    public void setPreviousPosition(Cell cell){
        previousPosition=cell;
    }
    public Cell getCurrentPosition(){
        return currentPosition;
    }
    public Cell getPreviousPosition(){
        return previousPosition;
    }
    public int getTotalScore(){
        return totalScore;
    }*/

    public void addOneScore(){
        totalScore += 1;  	
	}
    public void addScore(int score){
        totalScore += score;
    }
    public List<Object> getObjects(){
        return this.objects;
	}
    /*public void Object(int score){
        totalScore += score;
    }*/

	public void printRecap() {
		String recap="You have "+this.getIngredients()+" ingredients and a score of "+this.getTotalScore()+".";
		System.out.println(recap);
	}


}

