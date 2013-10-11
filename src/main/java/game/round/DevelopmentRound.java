package game.round;

import game.Character;
import game.DevelopmentScreen;
import game.Screen;
import game.Session;
import game.TownScreen;

import java.util.ArrayList;

public class DevelopmentRound extends Round {

	private ArrayList<Character> turnOrder;
	private int currentCharacterIndex;
	private int timer = 600; //update based on food later
	private Screen currentScreen;
	private TownScreen townScreen;
	private DevelopmentScreen developmentScreen;
	
	public DevelopmentRound(Session session) {
		super(session);
		//implement correct order later
		for (Character character : session.getCharacters()){
			turnOrder.add(character);
		}
		currentCharacterIndex = 0;
		developmentScreen = new DevelopmentScreen();
		townScreen = new TownScreen();
		currentScreen = developmentScreen;
	}

	@Override
	public void update() {
		timer--;
		if (timer<=0){
			currentCharacterIndex++;
			timer =600;
		}		
		currentScreen.update();
		if (currentScreen.shouldSwitch()){
			switchScreen();
		}
			
		
	}

	@Override
	public void click(int x, int y, boolean leftMouse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDone() {
		if (currentCharacterIndex >= turnOrder.size()){				
			return true;				
		}
		return false;
	}

	@Override
	public void setSession(Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Session getSession() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void switchScreen(){
		if (currentScreen instanceof TownScreen){
			currentScreen = developmentScreen;
		} else {
			currentScreen = townScreen;
		}
	}
	

}
