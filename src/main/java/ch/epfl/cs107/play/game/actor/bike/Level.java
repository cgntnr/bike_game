package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.actor.bike.Bike;
import ch.epfl.cs107.play.game.actor.bike.FinishLine;
import ch.epfl.cs107.play.game.actor.general.StartFlag;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.math.Node;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public abstract class Level extends Node implements Actor
{
	
	protected ActorGame game;
	
	private TextGraphics  message;
	private TextGraphics coinMessage;
	
	private Bike bike;
	private Terrain terrain;
	private StartFlag startFlag;
	private FinishLine finishLine;
	
	public static int coinScore= 0;
	
	//CONSTRUCTORS
	public abstract void createAllActors();
	
	// creating actors which are common to all levels in order not to repeat them at each level,
	//their position are determined by arguments of this method
	public void createAllActors(Vector bikePosition, Vector startFlagPosition , Vector finishLinePosition,float... points)
	{

		bike = new Bike(game, false, bikePosition);
		startFlag = new StartFlag(game, startFlagPosition);
		finishLine = new FinishLine(game,finishLinePosition);
		terrain = new Terrain(game, Vector.ZERO , points);
		
		message = new TextGraphics("", 0.08f, Color.WHITE, Color.WHITE, 0.02f, true, false, new Vector(0.5f, 0.5f), 1.0f,
				100.0f);
		message.setParent(game.getCanvas());
		message.setRelativeTransform(Transform.I.translated(0.0f, -1.0f));
		
		coinMessage = new TextGraphics("Coins: " + Level.coinScore, 0.1f, Color.CYAN, Color.CYAN, 0.02f, true, false, new Vector(-1.5f, 3.5f), 1.0f,
				100.0f);
		coinMessage.setParent(game.getCanvas());
		coinMessage.setRelativeTransform(Transform.I.translated(-0.5f, -0.8f));
		
		coinMessage.draw(game.getCanvas());		

		game.setViewCandidate(bike);  //to focus camera on bike
	} 
	
	//setting true for hasWon() if biker arrives at finish line
	public boolean hasWon()
	{
		return finishLine.hasTriggered();
	}
	
	// setting true if bike hits an object
	public boolean hasLost()
	{
		return bike.hasCrashed();
	}
	
	// setting true if the game is lost or won
	public boolean isGameOver()
	{
		return hasLost() || hasWon();
	}
	 
	public void removeAllActors()
	{
		game.removeAllActors();
	}
	
	//updates to see if game is lost or not 
	public void update(float deltaTime)
	{

		bike.update(deltaTime);
	
		//case of losing
		if (hasLost())
		{
			message.setText("GAME OVER");
			message.draw(game.getCanvas());
			game.removeActor(bike);

		} 
		//case of winning
		else if (hasWon())
		{
			message.setText("NEXT LEVEL");
			bike.setVictoryPosition();      // sets the victory position
			message.draw(game.getCanvas());
			game.removeActor(bike);
		}
	}
	
	
	//draws the common objects of each level
	public void draw(Canvas canvas)
	{
		coinMessage.setText("Coins: " + Level.coinScore);
		coinMessage.draw(game.getCanvas());	
		terrain.draw(canvas);
		finishLine.draw(canvas);
		bike.draw(canvas);
		startFlag.draw(canvas);
	}

	//Accessors & mutators
	public void setAlpha(float alpha)
	{
		message.setAlpha(alpha);
	}
	//gets the transparency
	public float getAlpha()
	{
		return message.getAlpha();
	}
	
	// calling the methods to work on bike
	protected void applyImpulse(Vector v)
	{
		bike.applyImpulse(v);
	}
	protected void applyForce(Vector v)
	{
		bike.applyForce(v);
	}
	
	//getting the bike's direction from bike
	protected boolean getDirection()
	{
		return bike.getDirection();
	}
	

	public void setGame(ActorGame g)
	{
		game = g;
	}
	
	// sets the wanted text
	protected void setText(String text)
	{
		message.setText(text);
	}
	
	
	protected TextGraphics getMessage()
	{
		return message;
	}
	
	// returns the bike to reach in other levels
	protected Bike getBike()
	{
		return bike;
	}
	
}