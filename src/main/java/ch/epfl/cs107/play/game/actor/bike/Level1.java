package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.general.Block;
import ch.epfl.cs107.play.game.actor.general.Coin;
import ch.epfl.cs107.play.game.actor.general.Missile;
import ch.epfl.cs107.play.game.actor.general.Pic;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Level1 extends Level {
	
	private Block block1;
	private Block block2;
	private Pic missilePic;
    private Coin coin;
	private Missile missile1;
	private boolean giveBack = true; //this is explained further below
	
	//arguments of createAllActors in  the following order 
	//bike position, start flag position, finish line position, terrain position 
	@Override
	public void createAllActors()
	{
		super.createAllActors(new Vector(-10.0f, 0.0f), new Vector(-13.0f, 0.0f), new Vector(65.0f, 0.0f)
		     , -1000.0f, -1000.0f, -1000.0f, 10.0f, -20f, 10f, -20f, 0f, 0.0f, 0.0f,
				3.0f, 1.0f, 8.0f, 1.0f, 15.0f, 3.0f, 16.0f, 3.0f, 25.0f, 0.0f, 
				35.0f, -5.0f, 40f, -4f, 40f, -50f, 42f,
				-55f, 44f, -50f, 44f, -4f, 50.0f, -5.0f, 55.0f, -4.0f, 65.0f, 0.0f, 6500.0f, -1000.0f);

		missilePic = new Pic(game, new Vector(30f, -2.5f), "star.silver.png");
		coin = new Coin(game, new Vector(15f, 4f));		
		block1 = new Block(game, true, new Vector(5.0f, 1.0f), "stone.broken.4.png", 1f, 1f);
		block2 = new Block(game, true, new Vector(15f, 3.0f), "stone.broken.4.png", 1f, 1f);
	}

	@Override
	public void update(float deltaTime)
	{
		super.update(deltaTime);
		
		//creates the missiles when the star pick is taken
		if (missilePic.isTriggered())
		{
			missile1 = new Missile(game, new Vector(41.5f, 64f), "missile.blue.3.png", 1f, 1f);
			game.addActor(missile1);
			missile1 = new Missile(game, new Vector(41.5f, 50f), "missile.blue.3.png", 1f, 1f);
			game.addActor(missile1);
			missile1 = new Missile(game, new Vector(41.5f, 36f), "missile.blue.3.png", 1f, 1f);
			game.addActor(missile1);
			missile1 = new Missile(game, new Vector(41.5f, 22f), "missile.blue.3.png", 1f, 1f);
			game.addActor(missile1);
			missile1 = new Missile(game, new Vector(41.5f, 6f), "missile.blue.3.png", 1f, 1f);
			game.addActor(missile1);
			missilePic.destroy();
		}
		
		// giveBack instantiated to true , if biker loses the level, coins decreases by 1 , but for only once,
		//multiple death in the same level won't result in coin loss thanks to boolean giveBack
		if(giveBack && hasLost() && coin.hasTriggered())
		{
			Level.coinScore--;
			giveBack = false;
			coin.destroy();
		}
	}

	//drawing of additional objects
	@Override
	public void draw(Canvas canvas)
	{		
		super.draw(canvas);  //super called from Level
		block1.draw(canvas);
		block2.draw(canvas);
		coin.draw(canvas);
		missilePic.draw(canvas);
	}
}
