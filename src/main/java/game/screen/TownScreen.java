package game.screen;

import game.Character;
import game.Plot;
import game.Session;
import ui.Window;
import core.ImageLoader;
import core.render.SimpleRender;
public class TownScreen extends Screen 
{	
	public TownScreen(Session session)
	{
		super(session);
		
		// tell the image loader to cache a copy of our images
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.load("assets/images/store/storeEnergy.png");
		imageLoader.load("assets/images/store/storeFood.png");
		imageLoader.load("assets/images/store/storeOre.png");
		imageLoader.load("assets/images/store/storeCrystite.png");
		imageLoader.load("assets/images/store/storeMule.png");
		imageLoader.load("assets/images/store/storeAssay.png");
		imageLoader.load("assets/images/store/storePub.png");
		imageLoader.load("assets/images/store/storeLand.png");
	}
	
	public void update() 
	{
		renderables.clear();
		renderableStrings.clear();

		SimpleRender energyStore = new SimpleRender("assets/images/store/storeEnergy.png");
		energyStore.setX(496);
		energyStore.setY(0);
		renderables.add(energyStore);

		SimpleRender foodStore = new SimpleRender("assets/images/store/storeFood.png");
		foodStore.setX(138);
		foodStore.setY(0);
		renderables.add(foodStore);

		SimpleRender oreStore = new SimpleRender("assets/images/store/storeOre.png");
		oreStore.setX(362);
		oreStore.setY(0);
		renderables.add(oreStore);

		SimpleRender crystite = new SimpleRender("assets/images/store/storeCrystite.png");
		crystite.setX(4);
		crystite.setY(0);
		renderables.add(crystite);
		
		SimpleRender muleStore = new SimpleRender("assets/images/store/storeMule.png");
		muleStore.setX(496);
		muleStore.setY(200);
		renderables.add(muleStore);

		SimpleRender landStore = new SimpleRender("assets/images/store/storeLand.png");
		landStore.setX(138);
		landStore.setY(200);
		renderables.add(landStore);

		SimpleRender pub = new SimpleRender("assets/images/store/storePub.png");
		pub.setX(362);
		pub.setY(200);
		renderables.add(pub);

		SimpleRender assay = new SimpleRender("assets/images/store/storeAssay.png");
		assay.setX(4);
		assay.setY(200);
		renderables.add(assay);
	}

	public boolean shouldSwitch(Character character) 
	{
		if (character.getX() < 0)
		{
			character.setX(Plot.SIZE * 4 - Character.WIDTH * 2);
			character.setY(Plot.SIZE * 2 + Plot.SIZE / 2 - Character.HEIGHT / 2);
			return true;
		}
		
		if (character.getX() > Window.WIDTH - Character.WIDTH)
		{
			character.setX(Plot.SIZE * 5 + Character.WIDTH);
			character.setY(Plot.SIZE * 2 + Plot.SIZE / 2 - Character.HEIGHT / 2);
			return true;
		}

		if (character.getY() < 0)
		{
			character.setX(Plot.SIZE * 4 + Plot.SIZE / 2 - Character.WIDTH / 2);
			character.setY(Plot.SIZE * 2 - Character.HEIGHT);
			return true;
		}
		
		if (character.getY() > Window.HEIGHT - 90)
		{
			character.setX(Plot.SIZE * 4 + Plot.SIZE / 2- Character.WIDTH / 2);
			character.setY(Plot.SIZE * 3 + Character.HEIGHT);
			return true;
		}
		
		return false;
	}
}
