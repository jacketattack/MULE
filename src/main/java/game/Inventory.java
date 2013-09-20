package game;

/**
 * 
 * @author grant
 * @author
 */
public class Inventory 
{
	private int food;
	private int energy;
	private int ore;
	private int crystite;
	private int money;
	private Plot[] ownedPlots;
        public Inventory() {
            ownedPlots=new Plot[45];
        }
        public void changeFood(int amount){
             food+=amount;
         }
        public void changeEnergy(int amount){
             energy+=amount;
         }
        public void changeOre(int amount){
             ore+=amount;
         }
        public void changeCyrstite(int amount){
             crystite+=amount;
         }
        public void changeMoney(int amount){
            money+=amount;
        }
	public int getFood()
	{
		return food;
	}
       
	public int getEnergy()
	{
		return energy;
	}

	public int getOre()
	{
		return ore;
	}

	public int getCrystite()
	{
		return crystite;
	}

	public int getMoney()
	{
		return money;
	}

	public Plot[] getPlots()
	{
		return ownedPlots;
	}
}
