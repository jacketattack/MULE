package game.screen;

import core.render.SimpleRender;
import game.Character;
import game.Session;
public class TownScreen extends Screen 
{
	public TownScreen(Session session)
	{
		super(session);
	}
	
	public void update() 
	{
		renderables.clear();
		renderableStrings.clear();

		SimpleRender energyStore = new SimpleRender("assets/images/store/storeEnergy.png");
		energyStore.setX(534);
		energyStore.setY(0);
		renderables.add(energyStore);

		SimpleRender foodStore = new SimpleRender("assets/images/store/storeFood.png");
		foodStore.setX(9);
		foodStore.setY(0);
		renderables.add(foodStore);

		SimpleRender oreStore = new SimpleRender("assets/images/store/storeOre.png");
		oreStore.setX(438);
		oreStore.setY(0);
		renderables.add(oreStore);

		SimpleRender crystite = new SimpleRender("assets/images/store/storeCrystite.png");
		crystite.setX(0);
		crystite.setY(0);
		renderables.add(crystite);
		
		SimpleRender muleStore = new SimpleRender("assets/images/store/storeMule.png");
		muleStore.setX(534);
		muleStore.setY(230);
		renderables.add(muleStore);

		SimpleRender landStore = new SimpleRender("assets/images/store/storeLand.png");
		landStore.setX(96);
		landStore.setY(230);
		renderables.add(landStore);

		SimpleRender pub = new SimpleRender("assets/images/store/storePub.png");
		pub.setX(438);
		pub.setY(230);
		renderables.add(pub);

		SimpleRender assay = new SimpleRender("assets/images/store/storeAssay.png");
		assay.setX(0);
		assay.setY(230);
		renderables.add(assay);
		
	}

	public boolean shouldSwitch(Character character) 
	{
		return false;
	}
}
