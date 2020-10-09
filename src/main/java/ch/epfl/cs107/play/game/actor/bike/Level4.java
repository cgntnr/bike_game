package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.general.Block;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Level4 extends Level {

	private Block block1;
	private Block block2;
	private Block block3;
	private Block block4;
	private Block block5;
	private Block block6;
	
	//arguments of createAllActors in  the following order 
	//bike position, start flag position, finish line position, terrain position 
	@Override
	public void createAllActors()
	{
		super.createAllActors(new Vector(10.0f, 0.0f), new Vector(6.0f, 0.0f) ,new Vector(90.0f, 0.0f)
		, -1000f, -1000f, -1000f, 10f, -20f, 10f, -20f, 0f, 0f, 0f, 120f, 0f,
				1000f, 120f, 2000f, -2000f);

		block1 = new Block(game, false, new Vector(20.0f, 40.0f), "stone.broken.4.png", 1f, 4f);
		block2 = new Block(game, false, new Vector(30.0f, 65.0f), "stone.broken.4.png", 1f, 4f);
		block3 = new Block(game, false, new Vector(40.0f, 90.0f), "stone.broken.4.png", 1f, 4f);
		block4 = new Block(game, false, new Vector(50.0f, 150.0f), "stone.broken.4.png", 1f, 4f);
		block5 = new Block(game, false, new Vector(60.0f, 190.0f), "stone.broken.4.png", 1f, 4f);
		block6 = new Block(game, false, new Vector(70.0f, 270.0f), "stone.broken.4.png", 1f, 4f);
	}

	//drawings
	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		block1.draw(canvas);
		block2.draw(canvas);
		block3.draw(canvas);
		block4.draw(canvas);
		block5.draw(canvas);
		block6.draw(canvas);
	}
}