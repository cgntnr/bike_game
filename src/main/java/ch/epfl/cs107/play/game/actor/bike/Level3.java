package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.general.Rope;
import ch.epfl.cs107.play.game.actor.general.Trampoline;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Level3 extends Level 
{
	
	private Trampoline trampoline;
	private Rope rope;

	//arguments of createAllActors in  the following order 
	//bike position, start flag position, finish line position, terrain position 
	@Override
	public void createAllActors()
	{
		super.createAllActors(new Vector(-35.0f, 5.0f), new Vector(-40.0f, 5.0f), new Vector(60.0f, 2.0f)
	, -1000f, -1000f, -1000f, 40f, -500.0f, 40f, -50f, 40f, -50f, 5f,
				-17.0f, 5f, -19.0f, 1f, -7f, 1f, -7f, 0.0f, -6.5f, 0.0f, -1.5f, -100f, 
				0.5f, -100f, 0.5f, 0f, 1.0f, 0f,
				1f, 1f, 6f, 1.5f, 9f, 2f, 12f, 4f, 16f, 5f, 20f, 6f, 20f, 
				-100f, 22.5f, -120f, 25f, -100f, 25f, 5.8f,
				45.0f, 2f, 5000f, 2f, 7000f, -7000f);
		rope = new Rope(game, false, Vector.ZERO, new Vector(22.5f, 12.0f), new Vector(22.5f, 7f));
		trampoline = new Trampoline(game, new Vector(-14f, 3f));
	}

	@Override
	public void update(float deltaTime)
	{
		super.update(deltaTime);
		
		// applying impulse to bike
		if (trampoline.isTriggered())
		{
			super.applyImpulse(new Vector(2f, 1f));
		}

	}

	//drawings
	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		trampoline.draw(canvas);
		rope.draw(canvas);
	}
}