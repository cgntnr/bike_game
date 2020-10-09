package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.general.Rope;
import ch.epfl.cs107.play.game.actor.general.Slippery;
import ch.epfl.cs107.play.game.actor.general.SpeedBoost;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Level5 extends Level {

	private Slippery slippery;
	private SpeedBoost speedBoost;
	private Rope rope;
	
	//arguments of createAllActors in  the following order 
	//bike position, start flag position, finish line position, terrain position 
	@Override
	public void createAllActors()
	{
        super.createAllActors(new Vector(-20.0f, 0.0f), new Vector(-24f, 0.0f), new Vector(85.0f, 0.0f),
	 -1000f, -1000f, -1000f, 10f, -30.0f, 10f, -30.0f, 0f, -20.0f, 0f, 0f,
				0f, 2f, 1f, 4f, 2f, 9f, 6f, 20f, 1f, 29f, -2f, 35f, -2f, 43f, -1f, 
				46f, 0f, 50f, 1f, 50f, -100f, 52.5f,
				-120f, 55f, -100f, 55f, 1f, 58f, 0f, 70f, 0f, 2000f, 0f, 2000f, -2000f);
        
        speedBoost = new SpeedBoost(game, true, new Vector(15f, 3f), "star.gold.png", 1f, 1f);
		rope = new Rope(game, false, Vector.ZERO, new Vector(52.5f, 7f), new Vector(52f, 2f));
		slippery = new Slippery(game, true, new Vector(29f, -2f), "slime.green.left.3.png", 6f, 0.3f);
	}

	@Override
	public void update(float deltaTime)
	{
		super.update(deltaTime);
		
		// when slippery triggered, applying force to bike so that bike won't be able to pass without the speed boost
		if (slippery.isTriggered())
		{
			super.applyForce(new Vector(-45f, 0f));
		}
		//applying impulse to bike as speed boost if the bike faces right in order to pass the obstacle
		if (speedBoost.isTriggered() && super.getDirection())
		{
			super.applyImpulse(new Vector(2f, -1.5f));

		}

	}// end of update

	//drawings
	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		slippery.draw(canvas);
		speedBoost.draw(canvas);
		rope.draw(canvas);
	}
}