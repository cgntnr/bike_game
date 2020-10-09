package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.general.Trigger;
import ch.epfl.cs107.play.math.Vector;



public class FinishLine extends Trigger
{
	
	//constructor for finish line
	public FinishLine(ActorGame game, Vector position)
	{
		super(game, true, position, "flag.red.png",1f,1f);
	}	
	


}
