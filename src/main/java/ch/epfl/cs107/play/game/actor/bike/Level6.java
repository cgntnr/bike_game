package ch.epfl.cs107.play.game.actor.bike;

import com.sun.glass.events.KeyEvent;

import ch.epfl.cs107.play.game.actor.general.GravityField;
import ch.epfl.cs107.play.game.actor.general.Revolute;
import ch.epfl.cs107.play.game.actor.general.Rope;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Level6 extends Level
{
	private Rope rope;
	private Revolute revolute;
	private GravityField gravityField;
	private boolean replay;
	private boolean inputEntered = false;

	//arguments of createAllActors in  the following order 
	//bike position, startflag position, finishline position, terrain position 
	@Override
	public void createAllActors()
	{
		super.createAllActors(new Vector(0.0f, -1.0f), new Vector(-4f, -1.0f) , new Vector(100.0f, 0.0f)
		, -1000f, -1000f, -1000f, 40f, -10f, 40f, -10f, -1f, 0f, -1f, 20.0f,
				-1f, 22.0f, -1f, 28f, -1f, 29f, -100f, 35f, -120f, 40, -100f, 40f, 0.5f, 42f, 0.5f, 44f, -1f, 48f, -3f,
				51f, -5f, 54f, -6f, 74f, -6f, 78f, -6f, 78f, 0f, 100f, 0f, 1000f, 0f, 1000f, -1000f);
		
		gravityField = new GravityField(game, new Vector(74.0f, -6.0f));
		rope = new Rope(game, false, Vector.ZERO, new Vector(55.5f, -0.0f),new Vector(55.5f, -6f));
		revolute = new Revolute(game, false, new Vector(29f, -3f));
	}
	
    
	@Override
	public void update(float deltaTime)
	{
		
		getBike().update(deltaTime);
		getMessage().draw(game.getCanvas());
		
		//this block is entered if the user enters an input namely Y or N
		if (!inputEntered)
		{
			if (hasLost())
			{
				super.setText("GAME OVER");
				game.removeActor(getBike());

			}
			
			// winning situation
			else if (hasWon())
			{
				super.setText("CONGRATS! Wanna play Again?(Y/N)");
				super.setAlpha(1f);
				getBike().setVictoryPosition();		// sets victory position
				getMessage().draw(game.getCanvas());
				game.removeActor(getBike());

				//actions to replay game 
				if (game.getKeyboard().get(KeyEvent.VK_Y).isPressed())
				{
					Level.coinScore = 0 ;
					replay = true;
					inputEntered = true;
					super.setText("Level 1");
				}
				// actions to finish the game
				else if (game.getKeyboard().get(KeyEvent.VK_N).isPressed())
				{
					replay = false;
					inputEntered = true;
					super.setText("Goodbye!");
				}

			}
			// when the gravity field is triggered applying force to bike
			if (gravityField.isTriggered())
			{
					getBike().applyForce(new Vector(0.f, 9.81f * 6));
			}
		}
	}
	
	//drawings
	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		rope.draw(canvas);
		revolute.draw(canvas);
		gravityField.draw(canvas);
	}
	
	// goes back to Level1
	public boolean replay()
	{
		return replay;
	}
}