package game;

public class MuleStore extends Store 
{
	public static final int MULECOST = 100;
	public MuleStore()
	{
		imagePath = "assets/images/store/storeMule.png";
	}
	
	public void act() 
	{
		Character character = session.getCurrentCharacter();
		if(character.getFollower()==null && character.getMoney() >= MULECOST){
			Follower mule = new Mule(character);
			character.setFollower(mule);
			character.setMoney(character.getMoney() - MULECOST);
		}else {
			Follower mule = character.getFollower();
			if(mule instanceof Mule){
				character.setFollower(null);
				character.setMoney(character.getMoney() + MULECOST);
			}
			
		}
	}
}