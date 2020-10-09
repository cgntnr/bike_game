package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.general.Block;
import ch.epfl.cs107.play.game.actor.general.Fire;
import ch.epfl.cs107.play.game.actor.general.Pic;
import ch.epfl.cs107.play.game.actor.general.Revolute;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Level2 extends Level 
{
	private Block block;
	private Pic boxPic;
	private Fire fire;	
	private Fire fire2;
	private Revolute revolute;

	//arguments of createAllActors in  the following order 
	//bike position, start flag position, finish line position, terrain position 
	@Override
	public void createAllActors()
	{
		super.createAllActors(new Vector(-10.0f, 0.0f),new Vector(-15.0f, 0.0f), new Vector(90.0f, 1.0f)
		
	      , -1000.0f, -1000.0f, -1000.0f, 40.0f, -20f, 40f, -20f, 0f, 0.0f, 0.0f,
				5f, 2f, 10f, 4f, 20f, 6f, 24f, 5f, 27f, 0f, 40f, 
				0f, 40f, -3f, 45.8f, -3f, 45.8f, 1f, 65f,1f,65f,-0.5f, 69f, -0.5f,69f,1f, 100f, 1f, 1000f,
				1f, 1000f, -1000f);
		
		block = new Block(game, false, new Vector(61f, 1f), "metal.broken.5.png", 2.8f, 1.6f);
		boxPic = new Pic(game, new Vector(30f, 0f), "star.bronze.png");
		fire = new Fire(game, true, new Vector(40f, -3f), "flame.png", 5.8f, 2.6f);
		fire2 = new Fire(game, true, new Vector(65f, -0.5f), "flame.png", 4f, 1.3f);
		
	}

	@Override
	public void update(float deltaTime)
	{
		super.update(deltaTime);
		
		//situation of taking the pick, creates passage for the obstacle
		if (boxPic.isTriggered())
		{
			revolute = new Revolute(game, false, new Vector(38f, -1f));
			game.addActor(revolute);
			boxPic.destroy();
		}
		
	}

	//drawing of additional objects
	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		fire.draw(canvas);
		boxPic.draw(canvas);
		block.draw(canvas);
		fire.draw(canvas);
		fire2.draw(canvas);
		
		if (boxPic.isTriggered())
		{
			revolute.draw(canvas);
		}	
	}
	
	// determines the condition of loss , contactSituation==1 meaning it hits a ghost , !super.hasWon means the 
	// finish line is not passed and the boxPic is not triggered , all of them to tell biker to die if only it hits 
	//the fire
	@Override
	public boolean hasLost()
	{
		
		return (getBike().contactSituation() == 1 && !super.hasWon() && !boxPic.isTriggered())
				|| super.hasLost();
	}

}